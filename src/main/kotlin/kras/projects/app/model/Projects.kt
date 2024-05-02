package kras.projects.app.model

data class Project(
    val projectId: Int,
    val projectName: String,
    val projectDescription: String,
    val members: List<Member>
)

data class Member(
    val id: Int,
    val name: String,
    val description: String
)