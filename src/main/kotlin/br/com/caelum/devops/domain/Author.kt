package br.com.caelum.devops.domain

import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "authors")
class Author ( @field:NotNull val name: String ): DomainEntity<Long>()
