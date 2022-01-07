/*
* Copyright (C) 2022 Bug.S.C Chen (efsn.chan@gmail.com).
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      https://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
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
