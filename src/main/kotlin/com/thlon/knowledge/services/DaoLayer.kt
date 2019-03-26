package com.thlon.knowledge.services

import java.math.BigInteger
import org.springframework.data.mongodb.repository.MongoRepository
import com.thlon.knowledge.models.KnwlGroup

interface KnwlGroupDAO:MongoRepository<KnwlGroup, BigInteger>
