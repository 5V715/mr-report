package dev.silas.gitlab.mrreport.data

import com.fasterxml.jackson.annotation.JsonProperty

data class Note(
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    val id: String,
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    val resolved: Boolean,
    val author: Author,
    val body: String
)