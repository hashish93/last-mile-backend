package com.appzoneltd.lastmile.authorizationserver.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.appzoneltd.lastmile.authorizationserver.dto.AuthorityDTO;
import com.appzoneltd.lastmile.authorizationserver.entity.UserEntity;
import com.appzoneltd.lastmile.authorizationserver.service.UserService;

@Configuration
public class OAuth2RefreshConfig {

	@Bean
	public MyUserDetailsService userDetailsService() {
		return new MyUserDetailsService();
	}

	public static class MyUserDetailsService implements UserDetailsService {

		@Autowired
		private UserService userService;

		public MyUserDetailsService() {

		}

		@Override
		public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

			UserEntity userEntity = userService.findByIdentifier(login);
			if (userEntity == null) {
				throw new UsernameNotFoundException("Username not found");
			}

			Set<AuthorityDTO> authorityDTOs = userService.getUserAuthorities(userEntity.getUserId());
			List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();

			for (AuthorityDTO authorityDTO : authorityDTOs) {
				grantedAuths.add(new SimpleGrantedAuthority("ROLE_" + authorityDTO.getAuthority()));
			}

			return new org.springframework.security.core.userdetails.User(userEntity.getUsername(),
					userEntity.getPassword(), userEntity.isEnabled(), true, true, true, grantedAuths);
		}

	}

}