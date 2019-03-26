package com.thlon.knowledge.models

import java.math.BigInteger
//import java.time.LocalDate
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document data class KnwlGroup(@Id val id:BigInteger? = null, val name:String)
