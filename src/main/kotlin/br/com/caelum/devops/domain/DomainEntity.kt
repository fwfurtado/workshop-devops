package br.com.caelum.devops.domain

import java.io.Serializable
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
class DomainEntity<T: Serializable> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: T? = null
}