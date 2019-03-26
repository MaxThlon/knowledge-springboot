package com.thlon.knowledge.config

import java.util.stream.Collectors
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Component
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.keycloak.TokenVerifier
import org.keycloak.common.VerificationException
import org.keycloak.representations.AccessToken

@Component
class KeycloakWebSocketAuthManager():AuthenticationManager {
  private val log = LogFactory.getLog(KeycloakWebSocketAuthManager::class.java)

  @Throws(AuthenticationException::class)
  override fun authenticate(authentication:Authentication):Authentication {
    var token = authentication as JWSAuthenticationToken
    val tokenString = token.getCredentials() as String
    try{
      val accessToken = TokenVerifier.create(tokenString, AccessToken::class.java).getToken()

      /*log.info("accessToken: ${accessToken.getName()}:")
      log.info("accessToken: ${accessToken.getEmail()}:")*/
      //log.info("accessToken: ${accessToken.getRealmAccess().getRoles()}:")

      //val authorities = accessToken.getRealmAccess().getRoles().stream().map(({ SimpleGrantedAuthority() })).collect(Collectors.toList())
      val authorities = accessToken.getRealmAccess().getRoles().stream().map(::SimpleGrantedAuthority).collect(Collectors.toList())
      val user = User(accessToken.getName(),
                      accessToken.getEmail(),
                      accessToken.getPreferredUsername(),
                      accessToken.getRealmAccess().getRoles())
      token = JWSAuthenticationToken(tokenString,
                                     user,
                                     authorities)
      token.setAuthenticated(true)
    }
    catch (e:VerificationException) {
      log.info("Exception authenticating the token {$tokenString}:", e)
      throw BadCredentialsException("Invalid token")
    }
    return token
  }
}
