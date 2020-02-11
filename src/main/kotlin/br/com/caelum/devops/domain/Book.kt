package br.com.caelum.devops.domain

import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "books")
class Book (
        @field:NotNull
        var title: String,

        var releaseDate: LocalDate?,

        @ManyToOne
        @JoinColumn(name = "author_id")
        var author: Author
) : DomainEntity<Long>()