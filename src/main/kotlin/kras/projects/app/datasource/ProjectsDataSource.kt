package kras.projects.app.datasource

import kras.projects.app.model.Member
import kras.projects.app.model.Project

interface ProjectsDataSource {
    fun retrieveProjects(): Collection<Project>
    fun retrieveProjects(id: Int): Project
    fun createProject(project: Project): Project
    fun updateProject(project: Project): Project
    fun deleteProject(id: Int)
    fun retrieveMembers(): Collection<Member>
    fun retrieveMembers(id: Int): Member
    fun createMember(projectId: Int, member: Member): Member
    fun updateMember(member: Member): Member
    fun deleteMember(id: Int)
}