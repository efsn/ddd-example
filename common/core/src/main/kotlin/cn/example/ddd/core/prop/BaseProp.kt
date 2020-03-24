package cn.example.ddd.core.prop

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "xx")
class BaseProp {
    var retailUnit: String = "cn"
    var language: String = "zh"
    var env: String = "dev"
}