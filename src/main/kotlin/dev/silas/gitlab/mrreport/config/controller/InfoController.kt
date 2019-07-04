package dev.silas.gitlab.mrreport.config.controller

import dev.silas.gitlab.mrreport.data.Discussions
import dev.silas.gitlab.mrreport.data.MergeRequest
import dev.silas.gitlab.mrreport.data.MergeRequestInfo
import dev.silas.gitlab.mrreport.data.Note
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.function.Predicate

@RestController
class InfoController(val client: WebClient, val projects: Flux<String>) {

    @GetMapping("/info")
    fun getInfo() =
        projects.concatMap { projectId ->  client.get().uri { builder ->
            builder.path("api/v4/projects/{id}/merge_requests")
                .queryParam("state","opened")
                .build(projectId)
        }.retrieve()
            .bodyToFlux(MergeRequest::class.java)
            .map { request -> projectId to request}
        }.flatMap { (projectId,mr) ->
            client.get().uri { builder ->
                builder.path("api/v4/projects/{id}/merge_requests/{iid}/discussions")
                    .build(projectId,mr.iid)
            }.retrieve()
                .bodyToFlux(Discussions::class.java)
                .flatMapIterable { discussions -> discussions.notes }
                .filter { note -> !note.resolved }
                .collectList()
                .map{ note -> mr to note }
                .map { (mergeRequest,openDiscussions) ->
                    MergeRequestInfo(mergeRequest,openDiscussions)
                }.filter{ info -> info.openDiscussion.isNotEmpty()}
        }
}