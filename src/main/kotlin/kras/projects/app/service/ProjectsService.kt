package kras.projects.app.service

import kras.projects.app.datasource.ProjectsDataSource
import kras.projects.app.model.Member
import kras.projects.app.model.Project
import org.springframework.stereotype.Service

@Service
class ProjectsService(private val dataSource: ProjectsDataSource) {
    fun getProjects(): Collection<Project> = dataSource.retrieveProjects()
    fun getProject(id: Int): Project = dataSource.retrieveProjects(id)
    fun addProject(project: Project): Project = dataSource.createProject(project)
    fun updateProject(project: Project): Project = dataSource.updateProject(project)
    fun deleteProjects(id: Int) = dataSource.deleteProject(id)
    fun getMembers(): Collection<Member> = dataSource.retrieveMembers()
    fun getMembers(id: Int): Member = dataSource.retrieveMembers(id)
    fun addMember(projectId: Int, member: Member): Member = dataSource.createMember(projectId, member)
    fun updateMember(member: Member): Member = dataSource.updateMember(member)
    fun deleteMember(id: Int): Unit = dataSource.deleteMember(id)
}