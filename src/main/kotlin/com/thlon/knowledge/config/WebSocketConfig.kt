package com.thlon.knowledge.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer
import org.springframework.messaging.Message
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.messaging.simp.config.ChannelRegistration
import org.springframework.messaging.support.ChannelInterceptor
import org.springframework.messaging.support.MessageHeaderAccessor
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.messaging.simp.stomp.StompCommand

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig @Autowired constructor(private val webSocketChannelInterceptor: WebSocketChannelInterceptor): WebSocketMessageBrokerConfigurer {
    override fun configureMessageBroker(config: MessageBrokerRegistry) {
        config.enableSimpleBroker("/queue")
        config.setApplicationDestinationPrefixes("/app")
    }

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/stomp").setAllowedOrigins("*").withSockJS();
    }

    override fun configureClientInboundChannel(registration: ChannelRegistration) {
        registration.interceptors(webSocketChannelInterceptor)
    }
}
