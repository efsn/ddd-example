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
