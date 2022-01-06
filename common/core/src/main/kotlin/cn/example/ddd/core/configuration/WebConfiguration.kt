package cn.example.ddd.core.configuration

import org.springframework.context.MessageSource
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.WebFluxConfigurer
import java.util.Locale

@Configuration
class WebConfiguration

@Component
class LocaleService(private val messageSource: MessageSource) {
    fun getMessage(code: String, vararg args: String): String {
        return messageSource.getMessage(code, args, Locale.ROOT)
    }
}

@Configuration
class WebFluxConfig : WebFluxConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("*")
    }
}
