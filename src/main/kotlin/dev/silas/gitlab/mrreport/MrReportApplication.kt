package dev.silas.gitlab.mrreport

import dev.silas.gitlab.mrreport.config.GitLabClient
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(GitLabClient::class)
class MrReportApplication

fun main(args: Array<String>) {
    runApplication<MrReportApplication>(*args)
}
