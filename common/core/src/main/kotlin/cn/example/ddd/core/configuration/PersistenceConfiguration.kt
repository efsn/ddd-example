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
