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
package cn.example.ddd.user.infrastructure.repository.po

import cn.example.ddd.core.domain.vo.toLocal
import cn.example.ddd.user.domain.model.entity.User
import io.r2dbc.spi.Row
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.OffsetDateTime

@Table("user")
data class UserPO(
    @field:Id
    val id: Long? = null,
    val username: String,
    val password: String,
    val createDatetime: OffsetDateTime,
    val updateDatetime: OffsetDateTime
) {

    fun toEntity() = User(id, username, password, createDatetime.toLocal(), updateDatetime.toLocal())
}

fun Row.toUserPO() = UserPO(
    id = get("id") as Long,
    username = get("username") as String,
    password = get("password") as String,
    createDatetime = get("create_datetime") as OffsetDateTime,
    updateDatetime = get("update_datetime") as OffsetDateTime
)
