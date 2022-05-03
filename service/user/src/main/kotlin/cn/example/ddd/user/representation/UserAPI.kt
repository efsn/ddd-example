/*
* Copyright (C) 2022 Arthur Chen (efsn.chan@gmail.com).
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
package cn.example.ddd.user.representation

import cn.example.ddd.user.application.UserApplicationService
import cn.example.ddd.user.representation.request.UserCreateRequest
import cn.example.ddd.user.representation.request.toCommand
import cn.example.ddd.user.representation.response.UserCreateResponse
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/users")
@Api(value = "user api")
class UserAPI(
    val applicationService: UserApplicationService
) {

    @PostMapping
    @ApiOperation(value = "add", nickname = "add-user", notes = "add a user")
    suspend fun add(@RequestBody @Valid request: UserCreateRequest) = UserCreateResponse(applicationService.add(request.toCommand()))
}
