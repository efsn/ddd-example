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
package cn.example.ddd.core.configuration

import cn.example.ddd.core.domain.vo.AbstractR2dbcConverter
import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@EnableR2dbcRepositories
class PersistenceConfiguration

@Configuration
class R2dbcConfiguration(
    private val converters: List<AbstractR2dbcConverter<*, *>>
) : AbstractR2dbcConfiguration() {
    override fun connectionFactory(): ConnectionFactory {
        TODO("not implemented")
    }

    @Bean
    override fun r2dbcCustomConversions() = R2dbcCustomConversions(storeConversions, converters)
}
