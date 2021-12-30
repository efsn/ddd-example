package cn.example.ddd.user.representation.request

import cn.example.ddd.user.application.command.UserCreateCommand
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotBlank

@ApiModel(description = "inbound order create request")
data class UserCreateRequest(
    @field:ApiModelProperty(notes = "username", required = true)
    @field:NotBlank
    val username: String,

    @field:ApiModelProperty(notes = "password", required = true)
    @field:NotBlank
    val password: String
)

fun UserCreateRequest.toCommand() = UserCreateCommand(username, password)
