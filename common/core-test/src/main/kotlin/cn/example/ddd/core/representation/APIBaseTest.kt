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
package cn.example.ddd.core.representation

import cn.example.ddd.core.configuration.LocaleService
import cn.example.ddd.core.configuration.SecurityConfiguration
import cn.example.ddd.core.domain.exception.GlobalErrorAttributes
import cn.example.ddd.core.domain.exception.GlobalErrorWebExceptionHandler
import com.nimbusds.jose.jwk.RSAKey
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.web.WebProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.web.reactive.error.ErrorAttributes
import org.springframework.context.ApplicationContext
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.reactive.server.WebTestClient
import java.util.Date

@WebFluxTest
@ActiveProfiles("test")
@ContextConfiguration(classes = [APITestConfiguration::class, SecurityConfiguration::class])
abstract class BaseAPITest {

    @Autowired
    protected lateinit var webClient: WebTestClient

    @Autowired
    private lateinit var rsaKey: RSAKey

    protected val validToken: String
        get() = Jwts.builder()
            .setId("666666")
            .setSubject("username")
            .setExpiration(Date(System.currentTimeMillis() + 60 * 1000))
            .signWith(rsaKey.toRSAPrivateKey())
            .compact()
}

@EnableConfigurationProperties(WebProperties::class)
class APITestConfiguration {

    @Bean
    fun getLocaleService(messageSource: MessageSource) = LocaleService(messageSource)

    @Bean
    fun getGlobalErrorWebExceptionHandler(
        errorAttributes: ErrorAttributes,
        webProperties: WebProperties,
        applicationContext: ApplicationContext
    ) = GlobalErrorWebExceptionHandler(errorAttributes, webProperties, applicationContext, ServerCodecConfigurer.create())

    @Bean
    fun getGlobalErrorAttributes(localeService: LocaleService) = GlobalErrorAttributes(localeService)

    @Bean
    fun rsaPublicKey(): RSAKey = RSAKeyGenerator(RSAKeyGenerator.MIN_KEY_SIZE_BITS).generate()
}
