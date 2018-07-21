package com.siemens.mindsphere.apps.common.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.siemens.mindsphere.apps.common.exception.TokenExpiredException;
import com.siemens.mindsphere.apps.exception.ErrorMappings;
import com.siemens.mindsphere.apps.exception.ParseException;
import com.siemens.mindsphere.apps.modules.login.authority.entity.Authority;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;
import org.springframework.util.StringUtils;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static com.siemens.mindsphere.apps.exception.ErrorMappings.ALREADY_EXISTING_USER_CODE;
import static com.siemens.mindsphere.apps.exception.ErrorMappings.ALREADY_EXISTING_USER_MESSAGE;
import static com.siemens.mindsphere.apps.common.utils.Constants.bearer_;

public class CommonUtils {

    public static Set<Authority> getAuthoritiesList(String authorityName) {
        Set<Authority> authorities = new HashSet<>();
        Authority authority = new Authority();
        authority.setAuthorityId(1);
        authority.setName(authorityName);
        authorities.add(authority);
        return authorities;
    }

    public static String getUsernameFromAccessToken(String authorization) throws ParseException {
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

    public static String getUsernameFromJsonBody(String json) throws IOException {
        String username = null;
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        JsonNode rootNode = mapper.readTree(json);
        username = rootNode.get("user_name").textValue();
        return username;
    }

    public static String generateOTP() {
        int randomPin   =(int)(Math.random()*9000)+1000;
        String otp = String.valueOf(randomPin);
        return otp;
    }

    public static String replaceString(String line, String paramToReplace, String paramToBeReplaced) {
        if(line.contains(paramToReplace)) {
            line = line.replace(paramToReplace, paramToBeReplaced);
        }
        return line;
    }


    public static String createJWT(String subject) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Date now = new Date();
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("SECRET");
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder()
                .setIssuedAt(now)
                .setSubject(subject)
                .signWith(signatureAlgorithm, signingKey);
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        c.add(Calendar.DATE, 1);
        Date exp = c.getTime();
        builder.setExpiration(exp);
        return builder.compact();
    }


    public static String parseJWT(String jwt) throws TokenExpiredException {
        Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary("SECRET"))
                    .parseClaimsJws(jwt).getBody();

        if(claims.getExpiration().before(new Date())) {
            throw new TokenExpiredException(ErrorMappings.TOKEN_EXPIRED_CODE, ErrorMappings.TOKEN_EXPIRED_MASSAGE);
        }

        return claims.getSubject();
    }

}
