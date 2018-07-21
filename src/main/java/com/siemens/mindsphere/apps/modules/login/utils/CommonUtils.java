package com.siemens.mindsphere.apps.modules.login.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.siemens.mindsphere.apps.exception.ParseException;
import com.siemens.mindsphere.apps.modules.email.EmailService;
import com.siemens.mindsphere.apps.modules.login.authority.entity.Authority;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static com.siemens.mindsphere.apps.exception.ErrorMappings.ALREADY_EXISTING_USER_CODE;
import static com.siemens.mindsphere.apps.exception.ErrorMappings.ALREADY_EXISTING_USER_MESSAGE;
import static com.siemens.mindsphere.apps.modules.login.utils.Constants.bearer_;

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

}
