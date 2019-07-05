package dev.silas.gitlab.mrreport.data

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize

data class MergeRequest(
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    val id : String,
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    val iid: String,
    @JsonAlias("web_url")
    val webUrl: String
)