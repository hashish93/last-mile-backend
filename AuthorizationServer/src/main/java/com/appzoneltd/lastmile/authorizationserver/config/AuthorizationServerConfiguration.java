package com.appzoneltd.lastmile.authorizationserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import com.appzoneltd.lastmile.authorizationserver.config.OAuth2RefreshConfig.MyUserDetailsService;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenStore tokenStore;
	
	@Autowired
    private MyUserDetailsService myUserDetailsService;

	@Bean
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer authServerSecurity) throws Exception {
		authServerSecurity.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
				.withClient("WEB")
				.secret("WEB")
				.authorizedGrantTypes("authorization_code", "implicit", "password", "refresh_token")
				.authorities("ROLE_CLIENT").scopes("read", "write").autoApprove(true)
				.and()
				.withClient("ANDROID")
				.secret("ANDROID")
				.authorizedGrantTypes("authorization_code", "implicit", "password", "refresh_token")
				.authorities("ROLE_CLIENT").scopes("read", "write").autoApprove(true);
	}


	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore)
				.authenticationManager(authenticationManager).userDetailsService(myUserDetailsService).approvalStoreDisabled();
	}
	
}
