package com.practice.service

import com.practice.domain.User
import com.practice.dto.UserRequest
import com.practice.dto.UserResponse
import com.practice.repository.UserRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

fun dummyUsers(): List<User> {
  return listOf(
    User(1, "이재웅"),
    User(2, "남현기"),
    User(3, "황내권")
  )
}

class UserServiceTest : BehaviorSpec({
  val repository = mockk<UserRepository>(relaxed = true)
  val userService = UserService(repository)

  Given("createUser 메서드 호출"){
    When("정상적인 요청이 들어오면"){
      val request = UserRequest("홍길동")
      val savedUser = User(4, "홍길동")

      every { repository.save(any()) } returns savedUser

      Then("유저가 DB에 저장되어야 한다"){

        val response = userService.createUser(request)

        response.userId shouldBe 4
        response.userName shouldBe "홍길동"

        verify(exactly = 1) {
          repository.save(any())
        }
      }
    }
  }

  Given("findAllUsers 메서드 호출"){
    When("한 명의 유저라도 존재한다면"){
      every { repository.findAll() } returns dummyUsers()

      Then("모두 UserResponse로 변환해 반환해야 한다"){
        val result = userService.findAllUsers()

        result shouldContainExactly listOf(
          UserResponse(1, "이재웅"),
          UserResponse(2, "남현기"),
          UserResponse(3, "황내권"),
        )

        verify(exactly = 1) {repository.findAll()}
      }
    }
  }
})