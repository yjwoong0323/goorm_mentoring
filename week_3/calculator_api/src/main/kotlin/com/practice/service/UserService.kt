package com.practice.service

import com.practice.domain.User
import com.practice.dto.UserRequest
import com.practice.dto.UserResponse
import com.practice.exception.ErrorCode
import com.practice.exception.UserException
import com.practice.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class UserService(
  private val userRepository: UserRepository
) {
  fun createUser(request: UserRequest): UserResponse = with(request){
    val savedUser = userRepository.save(User(userName = userName))

    UserResponse(savedUser.id, savedUser.userName)
  }

  fun findAllUsers(): List<UserResponse> {
    return userRepository.findAll().map { UserResponse(it.id, it.userName) }
  }

  fun findUserById(id: Int): UserResponse {
    val user = userRepository.findById(id).orElseThrow { UserException(HttpStatus.BAD_REQUEST, ErrorCode.USER_NOT_FOUND) }

    return UserResponse(user.id, user.userName)
  }

  @Transactional
  fun updateUser(id: Int, request: UserRequest): UserResponse {
    val user = userRepository.findById(id).orElseThrow { UserException(HttpStatus.BAD_REQUEST, ErrorCode.USER_NOT_FOUND) }
    user.userName = request.userName

    return UserResponse(user.id, user.userName)
  }

  fun deleteUser(id: Int) {
    if (!userRepository.existsById(id)) throw UserException(HttpStatus.BAD_REQUEST, ErrorCode.USER_NOT_FOUND)
    userRepository.deleteById(id)
  }
}