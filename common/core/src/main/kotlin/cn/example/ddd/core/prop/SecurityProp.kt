package cn.example.ddd.core.prop

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "root.security")
class SecurityProp {
    lateinit var rsaPubKey: String
    lateinit var signatureSalt: String
}