package com.thlon.knowledge.controllers

import java.security.Principal
import org.apache.juli.logging.Log
import org.apache.juli.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.module.kotlin.*
import com.thlon.knowledge.models.KnwlGroup

//@CrossOrigin(origins = ["*"], maxAge = 3600, allowedHeaders=["x-auth-token", "x-requested-with", "x-xsrf-token"])
//@CrossOrigin(origins = ["http://macmax.local:8080"])
@RestController
@RequestMapping(value=["/api"])
class RestController() {
    private val log = LogFactory.getLog(RestController::class.java)

    //@RequestMapping("/")
    //fun index(): String {
    //    return "index"
    //}
    @GetMapping("login")
    fun login(){
      log.info("login")
    }
}
