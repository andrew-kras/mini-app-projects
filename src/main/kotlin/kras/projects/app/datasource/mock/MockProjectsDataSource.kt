package kras.projects.app.datasource.mock

import kras.projects.app.datasource.ProjectsDataSource
import kras.projects.app.model.Member
import kras.projects.app.model.Project
import org.springframework.stereotype.Repository

@Repository
class MockProjectsDataSource : ProjectsDataSource {
    val projects = mutableListOf(
        Project(
            1,
            "Managers",
            "First project",
            mutableListOf(
                Member(1, "Muu Iban", "Project Manager"),
                Member(2, "Jack Warrior", "Project Manager"),
                Member(3, "Bruce Lee ", "Team Manager")
            )
        ),
        Project(
            2,
            "Developers",
            "Second project",
            mutableListOf(
                Member(4, "JSON Statham", "Software Engineer"),
                Member(5, "Emily Emily", "QA Engineer"),
                Member(6, "Like Human", "Frontend Developer")
            )
        )
    )

    override fun retrieveProjects(): Collection<Project> = projects

    override fun retrieveProjects(id: Int): Project =
        projects.firstOrNull { it.projectId == id }
            ?: throw NoSuchElementException("Could bot find a project with id $id")

    override fun createProject(project: Project): Project {
        if (projects.any { it.projectId == project.projectId}) {
            throw IllegalArgumentException("Project with id number ${project.projectId} already exist")
        }

        if (project.members.any { member -> projects.any { it!= project && it.members.any { m -> m.id == member.id } } }) {
            throw IllegalArgumentException("User with id ${project.members.first { member -> projects.any { it!= project && it.members.any { m -> m.id == member.id } } }.id} already exists in another project")
        }

        projects.add(project)

        return project
    }

    override fun updateProject(project: Project): Project {
        val currentProject = projects.firstOrNull { it.projectId == project.projectId}
            ?: throw NoSuchElementException("Could bot find a project with id number ${project.projectId}")

        if (project.members.any { member -> projects.any { it!= currentProject && it.members.any { m -> m.id == member.id } } }) {
            throw IllegalArgumentException("User with id ${project.members.first { member -> projects.any { it!= currentProject && it.members.any { m -> m.id == member.id } } }.id} already exists in another project")
        }

        projects.remove(currentProject)
        projects.add(project)

        return project
    }

    override fun deleteProject(id: Int) {
        val currentProject = projects.firstOrNull { it.projectId == id }
            ?: throw NoSuchElementException("Could bot find a project with id number $id")

        projects.remove(currentProject)
    }

    override fun retrieveMembers(): Collection<Member> = projects.flatMap { it.members }

    override fun retrieveMembers(id: Int): Member =
        projects.flatMap { it.members }.firstOrNull { it.id == id }
            ?: throw NoSuchElementException("Could not find a member with id $id")

    override fun createMember(projectId: Int, member: Member): Member {
        val project = retrieveProjects(projectId)

        if (project.members.any { it.id == member.id }) {
            throw IllegalArgumentException("Member with id ${member.id} already exists in project $projectId")
        }

        if (retrieveMembers().any { it.id == member.id }) {
            throw IllegalArgumentException("Member with id ${member.id} already exists in another project")
        }

        (project.members as MutableList<Member>).add(member)

        return member
    }

    override fun updateMember(member: Member): Member {
        val currentMember = projects.flatMap { it.members }.firstOrNull { it.id == member.id }
            ?: throw NoSuchElementException("Could not find a member with id ${member.id}")

        val project = projects.firstOrNull { it.members.contains(currentMember) }

        (project?.members as MutableList<Member>).remove(currentMember)
        (project.members as MutableList<Member>).add(member)

        return member
    }

    override fun deleteMember(id: Int) {
        val member = projects.flatMap { it.members }.firstOrNull { it.id == id }
            ?: throw NoSuchElementException("Could not find a member with id $id")

        val project = projects.firstOrNull { it.members.contains(member) }

        (project?.members as MutableList<Member>).remove(member)
    }
}
