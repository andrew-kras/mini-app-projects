package kras.projects.app.model

interface Project {
    val projectId: Int
    val projectName: String
    val projectDescription: String
    val members: List<Member>
}

interface Member {
    val id: Int
    val name: String
    val description: String
}