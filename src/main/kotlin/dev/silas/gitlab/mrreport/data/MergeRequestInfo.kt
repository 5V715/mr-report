package dev.silas.gitlab.mrreport.data

data class MergeRequestInfo(
    val mergeRequest: MergeRequest,
    val openDiscussion: List<Note>
)