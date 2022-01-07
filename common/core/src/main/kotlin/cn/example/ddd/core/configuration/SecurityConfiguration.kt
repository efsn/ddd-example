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
package cn.example.ddd.core.configuration

import cn.example.ddd.core.prop.SecurityProp
import com.nimbusds.jose.jwk.RSAKey
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
@EnableReactiveMethodSecurity
@EnableWebFluxSecurity
@EnableConfigurationProperties(SecurityProp::class)
class SecurityConfiguration(
    private val rsaKey: RSAKey
) {

    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        http.httpBasic().and()
            .csrf().disable()
            .authorizeExchange()
            .pathMatchers("/v2/api-docs").hasRole("SWAGGER_ADMIN")
            .anyExchange().permitAll()
            .and().formLogin()
            .and().oauth2ResourceServer().jwt().publicKey(rsaKey.toRSAPublicKey())
        return http.build()
    }
}

@Configuration
class KeyConfiguration(
    private val securityProp: SecurityProp
) {

    @Bean
    fun rsaPublicKey(): RSAKey = RSAKey.parse(securityProp.rsaPubKey)
}
