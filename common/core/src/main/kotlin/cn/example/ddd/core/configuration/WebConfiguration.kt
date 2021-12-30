package cn.example.ddd.core.configuration

import java.util.Locale
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.WebFluxConfigurer
import org.springframework.web.server.ServerWebExchange

@Configuration
class WebConfiguration

@Component
class LocaleService(
    private val messageSource: MessageSource
) {
    fun getMessage(code: String, exchange: ServerWebExchange): String {
        return messageSource.getMessage(code, null, Locale.ROOT)
    }

    fun getMessage(code: String): String {
        return messageSource.getMessage(code, null, Locale.ROOT)
    }
}

@Configuration
class WebFluxConfig : WebFluxConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("*")
    }
}
