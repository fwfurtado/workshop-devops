package br.com.caelum.devops.api

import br.com.caelum.devops.infra.ResourceNotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.notFound
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException::class)
    fun notFoundHandler(): ResponseEntity<Any> = notFound().build()
}