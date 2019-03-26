package com.thlon.knowledge.config

import java.util.Optional
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.messaging.Message
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.simp.stomp.StompCommand
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.messaging.support.ChannelInterceptor
import org.springframework.messaging.support.MessageHeaderAccessor
import org.keycloak.TokenVerifier
import org.keycloak.adapters.rotation.AdapterTokenVerifier
import org.keycloak.representations.AccessToken

@Component
class WebSocketChannelInterceptor @Autowired constructor(private val authenticationManager: KeycloakWebSocketAuthManager): ChannelInterceptor {
  private val log = LogFactory.getLog(WebSocketChannelInterceptor::class.java)

  override fun preSend(message: Message<*>, channel: MessageChannel): Message<*>? {
    val headerAccessor: StompHeaderAccessor? = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor::class.java)

    /*log.info("Here: " + message);*/
    if (StompCommand.CONNECT == headerAccessor?.command) {
      headerAccessor.getNativeHeader("Authorization")?.let {
        ah->
          val bearerToken = ah.get(0).replace("Bearer ", "")
          //log.info("Received bearer token {$bearerToken}")
          val token = authenticationManager.authenticate(JWSAuthenticationToken(bearerToken)) as JWSAuthenticationToken
          headerAccessor.setUser(token)
      }
    }

    return message
  }
}
