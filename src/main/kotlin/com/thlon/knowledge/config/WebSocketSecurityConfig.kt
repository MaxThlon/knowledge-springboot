package com.thlon.knowledge.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry
import org.springframework.messaging.simp.SimpMessageType

@Configuration
class WebSocketSecurityConfig: AbstractSecurityWebSocketMessageBrokerConfigurer() {
  override protected fun configureInbound(messages:MessageSecurityMetadataSourceRegistry) {
    messages.simpTypeMatchers(SimpMessageType.CONNECT,
                              SimpMessageType.UNSUBSCRIBE,
                              SimpMessageType.DISCONNECT,
                              SimpMessageType.HEARTBEAT).permitAll()
            .simpDestMatchers("/app/**").authenticated()
            .simpSubscribeDestMatchers("/user/**").authenticated()
            .anyMessage().denyAll()
  }

  override protected fun sameOriginDisabled():Boolean {
    return true
  }
}
