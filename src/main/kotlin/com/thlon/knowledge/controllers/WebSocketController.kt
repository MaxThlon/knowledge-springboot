package com.thlon.knowledge.controllers

import java.security.Principal;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.module.kotlin.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Controller
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.simp.annotation.SendToUser
import com.thlon.knowledge.models.KnwlGroup
import com.thlon.knowledge.services.KnwlGroupService

@Controller
class WebSockerController (private val knwlGroupService: KnwlGroupService) {
  private val log = LogFactory.getLog(WebSockerController::class.java)
  private val jacksonMapper = jacksonObjectMapper()

  // Groups
  @MessageMapping("/groups")
  @SendToUser("/queue/knwlgroups.updates")
  //fun createGroup(@Payload message: String, principal: Principal): Page<KnwlGroup> {
  fun createGroup(principal: Principal): Page<KnwlGroup> {

    /*val rootNode = jacksonMapper.readTree(message)
    val eGroupsCommands = jacksonMapper.readValue<EGroupsCommands>(rootNode["eGroupsCommand"].toString())

    when(eGroupsCommands) {
      EGroupsCommands.QUERY -> log.info("EGroupsCommands.QUERY")
      EGroupsCommands.CREATE -> {
        //var parentId: Int?
        val knwlGroup: KnwlGroup = KnwlGroup (name=rootNode["name"].toString())

        log.info("group create: name: ${knwlGroup.name}")
        knwlGroupService.insert(knwlGroup)
      }
      EGroupsCommands.DELETE -> log.info("EGroupsCommands.DELETE")
      EGroupsCommands.UPDATE -> log.info("EGroupsCommands.UPDATE")
    }*/

    //commandGateway.send(CreateCustomerCommand(PersonName(request.firstName, request.lastName), Money(request.orderLimit), auditEntry), LoggingCallback.INSTANCE)
    //ResponseEntity.ok(true)

    //val groupList: MutableList<KnwlGroup> = mutableListOf(KnwlGroup(1, "group1"), KnwlGroup(2, "group2"), KnwlGroup(3, "group3"))

    //val response: String = jacksonMapper.writeValueAsString(groupList)
    //log.info(response)
    //return jacksonMapper.writeValueAsString(knwlGroupService.getAll(PageRequest.of(0,100)))
    return knwlGroupService.getAll(PageRequest.of(0,100))
  }


  /*@RequestMapping("/groups")
  fun allGroups(): Iterable<GroupEntity> = groupRepository.findAll()

  @RequestMapping("/goups/{id}")
  fun getGroup(@PathVariable Id: String): ResponseEntity<String> {
      return if (person.isPresent) {
          kafkaTemplate.send(TOPIC,"GET /goups/id OK > $Id")
          ResponseEntity.ok(person.get())
      } else {
          kafkaTemplate.send(TOPIC, "GET /groups/id BadRequest > $id")
          ResponseEntity.badRequest().body(null)
      }
  }*/
}

enum class EGroupsCommands {
  @JsonProperty("QUERY")
  QUERY,
  @JsonProperty("CREATE")
  CREATE,
  @JsonProperty("DELETE")
  DELETE,
  @JsonProperty("UPDATE")
  UPDATE
}
