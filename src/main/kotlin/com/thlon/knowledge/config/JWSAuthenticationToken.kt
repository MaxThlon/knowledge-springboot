package com.thlon.knowledge.config

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.authentication.AbstractAuthenticationToken

class JWSAuthenticationToken @JvmOverloads constructor(private val token: String,
                                                       private val principal: User?=null,
                                                       authorities: Collection<GrantedAuthority>?=null):AbstractAuthenticationToken(authorities), Authentication {

  override fun getCredentials():Any {
    return token;
  }

  override fun getPrincipal():Any? {
    return principal
  }

  companion object {
    private val serialVersionUID = 1L
  }
}
