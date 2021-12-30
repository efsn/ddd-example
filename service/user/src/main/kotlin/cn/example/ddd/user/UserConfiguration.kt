package cn.example.ddd.user

import cn.example.ddd.core.configuration.PersistenceConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(PersistenceConfiguration::class)
class UserConfiguration
