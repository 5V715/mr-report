package dev.silas.gitlab.mrreport.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.validation.annotation.Validated
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.toFlux
import javax.validation.constraints.NotEmpty

@ConfigurationProperties("gitlab")
@Configuration
@Validated
class GitLabClient{

    @NotEmpty lateinit var apiKey: String

    @NotEmpty lateinit var uri : String

    @NotEmpty lateinit var projectIds : List<@NotEmpty String>

    @Bean
    fun webClient() = WebClient
        .builder()
        .baseUrl(uri)
        .defaultHeader("Private-Token",apiKey)
        .build()

    @Bean
    fun projects() : Flux<String> = projectIds.toFlux()

}