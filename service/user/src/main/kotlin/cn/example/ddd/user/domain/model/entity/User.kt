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
package cn.example.ddd.user.domain.model.entity

import cn.example.ddd.core.domain.vo.toOffset
import cn.example.ddd.user.infrastructure.repository.po.UserPO
import java.time.LocalDateTime

data class User(
    val id: Long? = null,
    val username: String,
    val password: String,
    val createDateTime: LocalDateTime = LocalDateTime.now(),
    val updateDateTime: LocalDateTime = createDateTime
)

fun User.toPO() = UserPO(id, username, password, createDateTime.toOffset(), updateDateTime.toOffset())
