package com.thlon.knowledge.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.Configuration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper
import org.springframework.security.core.session.SessionRegistryImpl
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.keycloak.adapters.KeycloakConfigResolver
import org.keycloak.adapters.springsecurity.KeycloakConfiguration
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter
import org.keycloak.adapters.springsecurity.management.HttpSessionManager
import org.keycloak.adapters.springsecurity.filter.KeycloakAuthenticationProcessingFilter
import org.keycloak.adapters.springsecurity.filter.KeycloakPreAuthActionsFilter
import org.keycloak.adapters.springsecurity.client.KeycloakClientRequestFactory
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate
import com.thlon.knowledge.ApplicationThlonKnowledge

@KeycloakConfiguration
class KeycloakSecurityConfig: KeycloakWebSecurityConfigurerAdapter() {
  @Autowired
  lateinit var keycloakClientRequestFactory: KeycloakClientRequestFactory

  @Bean
  @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
  fun keycloakRestTemplate(): KeycloakRestTemplate {
    return KeycloakRestTemplate(keycloakClientRequestFactory)
  }

  @Autowired
  @Throws(Exception::class)
  fun configureGlobal(auth: AuthenticationManagerBuilder) {
    auth.authenticationProvider(keycloakAuthenticationProvider().also {
      it.setGrantedAuthoritiesMapper(SimpleAuthorityMapper().also {
        it.setConvertToUpperCase(true)
      })
    })
/*
    val keycloakAuthenticationProvider = keycloakAuthenticationProvider()
    keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(SimpleAuthorityMapper())
    auth.authenticationProvider(keycloakAuthenticationProvider)*/
  }
/*
  /Bean
  fun KeycloakConfigResolver(): KeycloakConfigResolver {
    return KeycloakSpringBootConfigResolver()
  }
*/
  @Throws(Exception::class)
  override fun configure(http: HttpSecurity) {
    super.configure(http)

    http//.csrf().disable()
        /*.cors()
          .and()*/
        //.authenticationProvider(keycloakAuthenticationProvider())
        //.sessionManagement()
          //.sessionAuthenticationStrategy(sessionAuthenticationStrategy())
          //.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
          //.and()
        .authorizeRequests()
          .antMatchers("/stomp/**").permitAll()
//          .antMatchers("/api*").authenticated()
//          .anyRequest().permitAll()
//        .authorizeRequests()
          //.anyRequest().authenticated()
          //.antMatchers("/**")

    //http.requiresChannel().anyRequest().requiresSecure()

  }

  @Bean
  override fun sessionAuthenticationStrategy(): SessionAuthenticationStrategy = NullAuthenticatedSessionStrategy ()
                                                                                //RegisterSessionAuthenticationStrategy(SessionRegistryImpl())

/*  @Bean
  fun keycloakAuthenticationProcessingFilterRegistrationBean(filter: KeycloakAuthenticationProcessingFilter): FilterRegistrationBean<KeycloakAuthenticationProcessingFilter> {
    val registrationBean = FilterRegistrationBean(filter)
    registrationBean.isEnabled = false
    return registrationBean
  }

  @Bean
  fun keycloakPreAuthActionsFilterRegistrationBean(filter: KeycloakPreAuthActionsFilter): FilterRegistrationBean<KeycloakPreAuthActionsFilter> {
    val registrationBean = FilterRegistrationBean(filter)
    registrationBean.isEnabled = false
    return registrationBean
  }
*/
  @Bean
  @ConditionalOnMissingBean(HttpSessionManager::class)
  override protected fun httpSessionManager():HttpSessionManager {
    return HttpSessionManager()
  }
}
