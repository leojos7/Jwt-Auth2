package com.siemens.mindsphere.apps.modules.login.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.siemens.mindsphere.apps.modules.login.entity.Authority;
import com.siemens.mindsphere.apps.modules.login.exception.ParseException;
import org.apache.commons.codec.binary.Base64;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CommonUtils {

    public static Set<Authority> getAuthoritiesList(String authorityName) {
        Set<Authority> authorities = new HashSet<>();
        Authority authority = new Authority();
        authority.setId(1);
        authority.setName(authorityName);
        authorities.add(authority);
        return authorities;
    }

    public static String getUsernameFromAccessToken(String authorization) throws ParseException {
        String username = null;
        try {
            if (!StringUtils.isEmpty(authorization)) {
                String[] jwtToken = authorization.split("Bearer ");
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
            throw new ParseException("Excpetion while parsing access token to get the username");
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

}
