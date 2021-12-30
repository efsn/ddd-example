package cn.example.ddd.core.configuration

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration
import io.r2dbc.postgresql.PostgresqlConnectionFactory
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.containers.PostgreSQLContainer.POSTGRESQL_PORT

@Import(R2dbcConfiguration::class)
class R2dbcRepositoryTestConfiguration {

    @Bean(initMethod = "start", destroyMethod = "stop")
    fun postgresqlContainer(): PostgreSQLContainer<Nothing> = PostgreSQLContainer("postgres:14.1-alpine")

    @Bean
    @FlywayDataSource
    fun datasource(pgContainer: PostgreSQLContainer<Nothing>) = HikariDataSource(
        HikariConfig().apply {
            val mappedPort = pgContainer.getMappedPort(POSTGRESQL_PORT)
            jdbcUrl = "jdbc:postgresql://127.0.0.1:$mappedPort/${pgContainer.databaseName}"
            username = pgContainer.username
            password = pgContainer.password
        }
    )

    @Bean
    fun connectionFactory(pgContainer: PostgreSQLContainer<Nothing>) = PostgresqlConnectionFactory(
        PostgresqlConnectionConfiguration.builder()
            .host("127.0.0.1")
            .port(pgContainer.getMappedPort(5432))
            .database(pgContainer.databaseName)
            .username(pgContainer.username)
            .password(pgContainer.password)
            .build()
    )
}
