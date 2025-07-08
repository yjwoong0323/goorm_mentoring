package com.practice.controller

import com.practice.dto.UserRequest
import com.practice.dto.UserResponse
import com.practice.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController(
  private val userService: UserService
) {

  @PostMapping
  fun createUser(@RequestBody request: UserRequest): ResponseEntity<UserResponse> {
    return ResponseEntity.ok(userService.createUser(request))
  }

  @GetMapping
  fun getAll(): ResponseEntity<List<UserResponse>> {
    return ResponseEntity.ok(userService.getAll())
  }

  @GetMapping("/{id}")
  fun getOneUser(@PathVariable id: Int): ResponseEntity<UserResponse> {
    return ResponseEntity.ok(userService.getUserById(id))
  }

  @PutMapping("/{id}")
  fun updateUser(@PathVariable id: Int, @RequestBody request: UserRequest): ResponseEntity<UserResponse> {
    return ResponseEntity.ok(userService.updateUser(id, request))
  }

  @DeleteMapping("/{id}")
  fun deleteUser(@PathVariable id: Int): ResponseEntity<Unit> {
    userService.deleteUser(id)
    return ResponseEntity.noContent().build()
  }
}