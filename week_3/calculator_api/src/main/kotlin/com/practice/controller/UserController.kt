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
    // = return ResponseEntity.status(HttpStatus.OK).body(userService.createUser(request))
  }

  @GetMapping
  fun getAllUsers(): ResponseEntity<List<UserResponse>> {
    return ResponseEntity.ok(userService.findAllUsers())
  }

  @GetMapping("/{id}")
  fun getOneUser(@PathVariable id: Int): ResponseEntity<UserResponse> {
    return ResponseEntity.ok(userService.findUserById(id))
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