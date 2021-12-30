package cn.example.ddd.core.repository

import cn.example.ddd.core.configuration.R2dbcRepositoryTestConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.EnableTransactionManagement

@ActiveProfiles("test")
@SpringBootTest(classes = [R2dbcRepositoryTestApplication::class])
abstract class R2dbcRepositoryBaseTest

@Import(R2dbcRepositoryTestConfiguration::class)
@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = ["cn.example.ddd.**.repository"])
class R2dbcRepositoryTestApplication
