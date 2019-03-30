package com.thlon.knowledge

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

@SpringBootApplication
open class ApplicationThlonKnowledge: SpringBootServletInitializer() {
  override fun configure(springApplicationBuilder: SpringApplicationBuilder): SpringApplicationBuilder =
    springApplicationBuilder.sources(ApplicationThlonKnowledge::class.java)

  fun main(args: Array<String>) {
  	runApplication<ApplicationThlonKnowledge>(*args)
  }
}
