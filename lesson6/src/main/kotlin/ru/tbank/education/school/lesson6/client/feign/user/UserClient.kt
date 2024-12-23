package ru.tbank.education.school.lesson6.client.feign.user

import feign.Feign
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import ru.tbank.education.school.lesson6.client.dto.ApiResponse
import ru.tbank.education.school.lesson6.client.dto.User
import ru.tbank.education.school.lesson6.client.lessonObjectMapper
import kotlin.random.Random

interface UserApi {
    @RequestLine("POST /v2/user")
    @Headers("Content-Type: application/json")
    fun createUser(user: User): ApiResponse

    @RequestLine("GET /v2/user/{username}")
    @Headers("Content-Type: application/json")
    fun getUser(@Param("username") username: String): User

    @RequestLine("PUT /v2/user/{username}")
    @Headers("Content-Type: application/json")
    fun updateUser(@Param("username") username: String, user: User): ApiResponse

    @RequestLine("DELETE /v2/user/{username}")
    @Headers("Content-Type: application/json")
    fun deleteUser(@Param("username") username: String): ApiResponse
}

class UserClient(url: String) {
    private val feignClient = Feign.builder()
        .encoder(JacksonEncoder(lessonObjectMapper))
        .decoder(JacksonDecoder(lessonObjectMapper))
        .target(UserApi::class.java, url)

    fun createUser(user: User): ApiResponse = feignClient.createUser(user)

    fun getUser(username: String): User = feignClient.getUser(username)

    fun updateUser(username: String, user: User): ApiResponse = feignClient.updateUser(username, user)

    fun deleteUser(username: String): ApiResponse = feignClient.deleteUser(username)
}

fun main() {
    val userClient = UserClient("https://petstore.swagger.io")

    val newUser = User(
        id = Random.nextLong() * 1000,
        username = "corg",
        firstName = "test",
        lastName = "netest",
        email = "testn@pochta.com",
        password = "0000",
        phone = "88005553535",
        userStatus = 1
    )

    userClient.createUser(newUser)
    println("Создан пользователь: ${userClient.getUser(newUser.username)}")

    val updatedUser = newUser.copy(firstName = "Johnny")
    userClient.updateUser(newUser.username, updatedUser)
    println("Обновленный пользователь: ${userClient.getUser(updatedUser.username)}")

    userClient.deleteUser(newUser.username)
    println("Пользователь удален")
}
