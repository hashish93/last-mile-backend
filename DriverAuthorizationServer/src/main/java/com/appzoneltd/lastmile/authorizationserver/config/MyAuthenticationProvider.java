package com.appzoneltd.lastmile.authorizationserver.config;

import com.appzoneltd.lastmile.authorizationserver.dto.AuthorityDTO;
import com.appzoneltd.lastmile.authorizationserver.entity.UserEntity;
import com.appzoneltd.lastmile.authorizationserver.model.MyPrincipal;
import com.appzoneltd.lastmile.authorizationserver.service.DeleteDriverTokens;
import com.appzoneltd.lastmile.authorizationserver.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Configuration
public class MyAuthenticationProvider implements AuthenticationProvider {

	private UserService userService;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	DeleteDriverTokens deleteDriverTokens;

	@Bean
	public UserService userService() {
		return this.userService = new UserService();
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username = authentication.getPrincipal() + "";
		String password = authentication.getCredentials() + "";

		UserEntity userEntity = userService.findByIdentifier(username);

		if (userEntity == null) {
			throw new BadCredentialsException("Bad Credentials");
		}

		Map<String, String> authenticationDetails = (Map<String, String>) authentication.getDetails();
		String clientType = authenticationDetails.get("client_id");
		MyPrincipal myPrincipal = new MyPrincipal();
		myPrincipal.setUserId(userEntity.getUserId());
		myPrincipal.setName(username);
		myPrincipal.setUserType(userEntity.getUserType().getName());

		if ("DRIVER".equalsIgnoreCase(clientType)) {
			if (!"CORPORATE_DRIVER".equalsIgnoreCase(userEntity.getUserType().getName())
					&& !"FREELANCER_DRIVER".equalsIgnoreCase(userEntity.getUserType().getName())) {
				throw new BadCredentialsException("Bad Credentials");
			} else {				
				List<Long> hubs = userService.getUserHubList(userEntity);
				myPrincipal.setHubs(hubs);
			}
		}

		if (!userEntity.isEnabled()) {
			throw new DisabledException("Driver Not Active");
		}
		if (!userEntity.getPassword().equals(password)) {
			throw new BadCredentialsException("Bad Credentials");
		}

		deleteDriverTokens.findLoggedInUserAndDeleteTokens(userEntity);
		
		Set<AuthorityDTO> authorityDTOs = userService.getUserAuthorities(userEntity.getUserId());
		List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
		for (AuthorityDTO authorityDTO : authorityDTOs) {
			grantedAuths.add(new SimpleGrantedAuthority("ROLE_" + authorityDTO.getAuthority()));
		}

		UsernamePasswordAuthenticationToken userNamePasswordAuthenticationToken = null;
		try {
			String jsonPrincipal = objectMapper.writeValueAsString(myPrincipal);
			userNamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(jsonPrincipal, password,
					grantedAuths);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return userNamePasswordAuthenticationToken;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
