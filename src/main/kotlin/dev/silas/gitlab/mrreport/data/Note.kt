package dev.silas.gitlab.mrreport.data

data class Note(val id: String,
                val resolved: Boolean,
                val author: Author,
                val body: String)