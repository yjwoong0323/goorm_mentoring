package com.practice.service

import com.practice.domain.User
import com.practice.dto.UserRequest
import com.practice.dto.UserResponse
import com.practice.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class UserService(
  private val userRepository: UserRepository
) {
  fun createUser(request: UserRequest): UserResponse {
    val savedUser = userRepository.save(User(userName = request.userName))

    return UserResponse(savedUser.id, savedUser.userName)
  }

  fun getAll(): List<UserResponse> {
    return userRepository.findAll().map { UserResponse(it.id, it.userName) }
  }

  fun getUserById(id: Int): UserResponse {
    val user = userRepository.findById(id).orElseThrow { IllegalArgumentException("not found") }

    return UserResponse(user.id, user.userName)
  }

  @Transactional
  fun updateUser(id: Int, request: UserRequest): UserResponse {
    val user = userRepository.findById(id).orElseThrow { IllegalArgumentException("not found") }
    user.userName = request.userName

    return UserResponse(user.id, user.userName)
  }

  fun deleteUser(id: Int) {
    if (!userRepository.existsById(id)) throw IllegalArgumentException("not found")
    userRepository.deleteById(id)
  }
}