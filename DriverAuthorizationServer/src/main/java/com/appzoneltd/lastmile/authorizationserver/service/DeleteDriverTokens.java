package com.appzoneltd.lastmile.authorizationserver.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.authorizationserver.dto.UserDTO;
import com.appzoneltd.lastmile.authorizationserver.entity.UserEntity;

@Service
public class DeleteDriverTokens {

	private final TokenStore tokenStore;

	@Autowired
	public DeleteDriverTokens(TokenStore tokenStore) {
		this.tokenStore = tokenStore;
	}

	public void findLoggedInUserAndDeleteTokens(UserDTO user) {
		Collection<OAuth2AccessToken> accessTokens = tokenStore.findTokensByClientIdAndUserName("DRIVER",
				user.getEmail());
		deleteTokens(accessTokens);
		accessTokens = tokenStore.findTokensByClientIdAndUserName("DRIVER", user.getPhone());
		deleteTokens(accessTokens);
	}

	public void findLoggedInUserAndDeleteTokens(UserEntity userEntity) {
		Collection<OAuth2AccessToken> accessTokens = tokenStore.findTokensByClientIdAndUserName("DRIVER",
				userEntity.getEmail());
		deleteTokens(accessTokens);
		accessTokens = tokenStore.findTokensByClientIdAndUserName("DRIVER", userEntity.getPhone());
		deleteTokens(accessTokens);
	}

	private void deleteTokens(Collection<OAuth2AccessToken> accessTokens) {
		if (accessTokens != null && !accessTokens.isEmpty()) {
			for (OAuth2AccessToken oAuth2AccessToken : accessTokens) {
				tokenStore.removeAccessToken(oAuth2AccessToken);
				tokenStore.removeRefreshToken(oAuth2AccessToken.getRefreshToken());
			}
		}
	}
}
