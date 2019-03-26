package com.thlon.knowledge.services

import java.math.BigInteger
import java.util.Optional
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import com.thlon.knowledge.models.KnwlGroup
import com.thlon.knowledge.util.BasicCrud

@Service
class KnwlGroupService(val knwlGroupDAO: KnwlGroupDAO):BasicCrud<KnwlGroup, BigInteger> {

    override fun getAll(pageable: Pageable): Page<KnwlGroup> = knwlGroupDAO.findAll(pageable)

    override fun getById(id: BigInteger): Optional<KnwlGroup> = knwlGroupDAO.findById(id)

    override fun insert(obj: KnwlGroup): KnwlGroup = knwlGroupDAO.insert(obj)

    @Throws(Exception::class)
    override fun update(obj: KnwlGroup): KnwlGroup{
      return if( (obj.id!=null) &&
                  knwlGroupDAO.existsById(obj.id)){
        knwlGroupDAO.save(obj)
      }else{
        throw object: Exception("Group not found"){}
      }
    }

    override fun deleteById(id: BigInteger): Optional<KnwlGroup> {
      return knwlGroupDAO.findById(id).apply {
        this.ifPresent { knwlGroupDAO.delete(it) }
      }
    }
}
