package cn.example.ddd.core.configuration

import com.fasterxml.classmate.ResolvedType
import com.fasterxml.classmate.TypeResolver
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.schema.AlternateTypeRule
import springfox.documentation.schema.AlternateTypeRules
import springfox.documentation.schema.WildcardType
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@ConditionalOnProperty(name = ["swagger.enable"], havingValue = "true")
@Configuration
@EnableSwagger2
class SwaggerConfiguration(private val resolver: TypeResolver) {
    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .apiInfo(ApiInfoBuilder().description("Service APIs").build())
            .select()
            .apis(RequestHandlerSelectors.withClassAnnotation(RestController::class.java))
            .build()
            .alternateTypeRules(
                RecursiveAlternateTypeRule(
                    resolver,
                    listOf(
                        AlternateTypeRules.newRule(
                            resolver.resolve(Mono::class.java, WildcardType::class.java),
                            resolver.resolve(WildcardType::class.java)
                        ),
                        AlternateTypeRules.newRule(
                            resolver.resolve(ResponseEntity::class.java, WildcardType::class.java),
                            resolver.resolve(WildcardType::class.java)
                        )
                    )
                ),
                RecursiveAlternateTypeRule(
                    resolver,
                    listOf(
                        AlternateTypeRules.newRule(
                            resolver.resolve(Flux::class.java, WildcardType::class.java),
                            resolver.resolve(List::class.java, WildcardType::class.java)
                        ),
                        AlternateTypeRules.newRule(
                            resolver.resolve(ResponseEntity::class.java, WildcardType::class.java),
                            resolver.resolve(WildcardType::class.java)
                        )
                    )
                )
            )
    }

    class RecursiveAlternateTypeRule(
        typeResolver: TypeResolver,
        private val rules: List<AlternateTypeRule>
    ) : AlternateTypeRule(typeResolver.resolve(Any::class.java), typeResolver.resolve(Any::class.java)) {
        override fun alternateFor(type: ResolvedType): ResolvedType {
            val rStream = rules.flatMap { rule -> listOf(rule.alternateFor(type)) }
            val newType = rStream.firstOrNull { alternateType -> alternateType !== type } ?: type

            return if (appliesTo(newType)) {
                alternateFor(newType)
            } else newType
        }

        override fun appliesTo(type: ResolvedType): Boolean = rules.any { it.appliesTo(type) }
    }
}
