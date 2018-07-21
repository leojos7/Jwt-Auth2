package com.siemens.mindsphere.apps.common.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.siemens.mindsphere.apps.common.email.EmailService;
import com.siemens.mindsphere.apps.common.exception.MailNotSentException;
import com.siemens.mindsphere.apps.common.exception.TokenExpiredException;
import com.siemens.mindsphere.apps.exceptionHandler.ErrorMappings;
import com.siemens.mindsphere.apps.exceptionHandler.ParseException;
import com.siemens.mindsphere.apps.modules.login.authority.entity.Authority;
import com.siemens.mindsphere.apps.modules.login.user.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.Key;
import java.util.*;
import java.util.function.BiFunction;

import static com.siemens.mindsphere.apps.common.utils.Constants.bearer_;
import static com.siemens.mindsphere.apps.exceptionHandler.ErrorMappings.*;
import static com.siemens.mindsphere.apps.exceptionHandler.ErrorMappings.MAIL_NOT_SENT_CODE;
import static com.siemens.mindsphere.apps.exceptionHandler.ErrorMappings.MAIL_NOT_SENT_MASSAGE;

@Component
public class CommonUtils {

    @Autowired
    private EmailService emailService;

    @Value("${jwt.token.expiry}")
    private String tokenExpiry;

    @Value("${jwt.token.key}")
    private String tokenKey;

    public Set<Authority> getAuthoritiesList(String authorityName) {
        Set<Authority> authorities = new HashSet<>();
        Authority authority = new Authority();
        authority.setAuthorityId(1);
        authority.setName(authorityName);
        authorities.add(authority);
        return authorities;
    }

    public String getUsernameFromAccessToken(String authorization) throws ParseException {
        String username = null;
        try {
            if (!StringUtils.isEmpty(authorization)) {
                String[] jwtToken = authorization.split(bearer_);
                if (jwtToken[1] != null) {
                    String[] split_string = jwtToken[1].split("\\.");
                    Base64 base64Url = new Base64(true);
                    if (split_string[1] != null) {
                        String jsonBody = new String(base64Url.decode(split_string[1]));
                        username = getUsernameFromJsonBody(jsonBody);
                    }
                }
            }
        } catch (Exception e) {
            throw new ParseException(ALREADY_EXISTING_USER_CODE, ALREADY_EXISTING_USER_MESSAGE);
        }
        return username;
    }

    public String getUsernameFromJsonBody(String json) throws IOException {
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        JsonNode rootNode = mapper.readTree(json);
        return rootNode.get("user_name").textValue();
    }

    public String generateOTP() {
        int randomPin   =(int)(Math.random()*9000)+1000;
        String otp = String.valueOf(randomPin);
        return otp;
    }

    public String replaceString(String line, String paramToReplace, String paramToBeReplaced) {
        if(line.contains(paramToReplace)) {
            line = line.replace(paramToReplace, paramToBeReplaced);
        }
        return line;
    }

    public String createJWT(String subject) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Date now = new Date();
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(tokenKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder()
                .setIssuedAt(now)
                .setSubject(subject)
                .signWith(signatureAlgorithm, signingKey);
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        c.add(Calendar.DATE, Integer.parseInt(tokenExpiry));
        Date exp = c.getTime();
        builder.setExpiration(exp);
        return builder.compact();
    }

    public String parseJWT(String jwt) throws TokenExpiredException {
        Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(tokenKey))
                    .parseClaimsJws(jwt).getBody();
        if(claims.getExpiration().before(new Date())) {
            throw new TokenExpiredException(ErrorMappings.TOKEN_EXPIRED_CODE, ErrorMappings.TOKEN_EXPIRED_MASSAGE);
        }
        return claims.getSubject();
    }


    BiFunction<Map<String, String>, String, String> detailReplacingFunction = (paramsToReplace, line) -> {
        for (Map.Entry<String, String> entry : paramsToReplace.entrySet()) {
            line = replaceString(line, entry.getKey(), entry.getValue());
        }
        return line;
    };

    public String createMailBodyForPasswordResetMail(User user) throws IOException {
        Map<String, String> paramsToReplace = new HashMap<>();

        String passwordResetFileLocation = "classpath:html/password_reset.html";

        String urlToSetPassword = "http://localhost:4200/setPassword?code=" + createJWT(user.getUsername());
        String fullNameParamToBeReplaced = "{{name}}";
        String urlParamToBeReplaced = "{{action_url}}";

        paramsToReplace.put(urlParamToBeReplaced, urlToSetPassword);
        paramsToReplace.put(fullNameParamToBeReplaced, user.getFullName());

        return replaceDetailsInHTMLPage(paramsToReplace, passwordResetFileLocation);
    }

    public String createMailBodyForAccountActivationMail(User user) throws IOException {
        Map<String, String> paramsToReplace = new HashMap<>();
        String accountActivationFileLocation = "classpath:html/welcome.html";

        String urlToSetPassword = "";
        String fullNameParamToBeReplaced = "{{name}}";
        String urlParamToBeReplaced = "{{action_url}}";
        String usernameParamToBeReplaced = "{{username}}";

        paramsToReplace.put(urlParamToBeReplaced, urlToSetPassword);
        paramsToReplace.put(fullNameParamToBeReplaced, user.getFullName());
        paramsToReplace.put(usernameParamToBeReplaced, user.getUsername());

        return replaceDetailsInHTMLPage(paramsToReplace, accountActivationFileLocation);
    }

    public String replaceDetailsInHTMLPage(Map<String, String> paramsToReplace, String passwordResetFileLocation) throws IOException {
        StringBuilder mailBody = new StringBuilder();
        File file = ResourceUtils.getFile(passwordResetFileLocation);
        Files.lines(file.toPath())
                .map(line -> detailReplacingFunction.apply(paramsToReplace, line))
                .forEach(line -> mailBody.append(line));
        return mailBody.toString();
    }

    public void sendPasswordSettingNotificationEmail(User user) throws MailNotSentException {
        try {
            String mailSubject = "Simoq Password Reset";
            emailService.sendMail("leojos007@gmail.com", mailSubject, createMailBodyForPasswordResetMail(user));
        } catch (Exception e) {
            throw new MailNotSentException(MAIL_NOT_SENT_CODE, MAIL_NOT_SENT_MASSAGE);
        }
    }

    public void sendActivationEmail(User user) throws MailNotSentException {
        try {
            String mailSubject = "Simoq Account Activation";
            emailService.sendMail("leojos007@gmail.com", mailSubject, createMailBodyForAccountActivationMail(user));
        } catch (Exception e) {
            throw new MailNotSentException(MAIL_NOT_SENT_CODE, MAIL_NOT_SENT_MASSAGE);
        }
    }
}
