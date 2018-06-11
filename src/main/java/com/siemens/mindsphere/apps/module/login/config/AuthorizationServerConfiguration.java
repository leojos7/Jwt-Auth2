package com.siemens.mindsphere.apps.module.login.config;

import com.siemens.mindsphere.apps.module.login.utils.Authorities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;

import static com.siemens.mindsphere.apps.module.login.utils.Constants.*;
import static org.springframework.security.oauth2.common.OAuth2AccessToken.REFRESH_TOKEN;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Value("${authentication.oauth.clientid}")
    private String clientId;

    @Value("${authentication.oauth.secret}")
    private String secret;

    @Value("${authentication.oauth.accessTokenValidityInSeconds}")
    private int accessTokenValidityInSeconds;

    @Value("${authentication.oauth.refreshTokenValidityInSeconds}")
    private int refreshTokenValidityInSeconds;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccessTokenConverter accessTokenConverter;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints)
            throws Exception {
        endpoints
                .tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .accessTokenConverter(accessTokenConverter);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                .withClient(clientId)
                .scopes(READ, WRITE)
                .authorities(Authorities.ROLE_ADMIN.name(), Authorities.ROLE_USER.name())
                .authorizedGrantTypes(PASSWORD_GRANT_TYPE, REFRESH_TOKEN)
                .secret(passwordEncoder.encode(secret))
                .accessTokenValiditySeconds(accessTokenValidityInSeconds)
                .refreshTokenValiditySeconds(refreshTokenValidityInSeconds);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.allowFormAuthenticationForClients();
    }
}