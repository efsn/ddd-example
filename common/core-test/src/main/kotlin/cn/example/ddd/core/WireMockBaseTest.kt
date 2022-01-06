// package cn.example.ddd.core
//
// import com.github.tomakehurst.wiremock.WireMockServer
// import com.github.tomakehurst.wiremock.core.WireMockConfiguration.options
// import org.junit.jupiter.api.extension.ExtendWith
// import org.springframework.beans.factory.annotation.Autowired
// import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration
// import org.springframework.context.annotation.Bean
// import org.springframework.test.context.ActiveProfiles
// import org.springframework.test.context.ContextConfiguration
// import org.springframework.test.context.junit.jupiter.SpringExtension
// import org.springframework.web.reactive.function.client.WebClient
//
// @ExtendWith(SpringExtension::class)
// @ActiveProfiles("Test")
// @ContextConfiguration(classes = [WireMockTestConfiguration::class, JacksonAutoConfiguration::class])
// class BaseWireMockTest {
//     @Autowired
//     lateinit var wireMockServer: WireMockServer
// }
//
// class WireMockTestConfiguration {
//     @Bean
//     fun wireMockServer(): WireMockServer {
//         val wireMockServer = WireMockServer(options().dynamicPort())
//         wireMockServer.start()
//         return wireMockServer
//     }
//
//     @Bean
//     fun webClient(wireMockServer: WireMockServer) = WebClient.builder().baseUrl(wireMockServer.baseUrl()).build()
// }
