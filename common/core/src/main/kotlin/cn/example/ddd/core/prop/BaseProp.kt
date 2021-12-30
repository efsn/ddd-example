package cn.example.ddd.core.prop

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@ConfigurationProperties(prefix = "example")
@Component
// TODO
class BaseProp {
    var retailUnit: String = "cn"
    var language: String = "zh"
    var env: String = "dev"
}
