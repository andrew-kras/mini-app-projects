package kras.projects.app.service

import kras.projects.app.model.Project
import kras.projects.app.model.Member
import kras.projects.app.datasource.mock.ProjectRepository
import kras.projects.app.datasource.mock.MemberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProjectService(@Autowired val projectRepository: ProjectRepository) {

    fun findAll(): List<Project> = projectRepository.findAll()

    fun findById(id: Int): Project? = projectRepository.findById(id).orElse(null)

    fun save(project: Project): Project = projectRepository.save(project)

    fun deleteById(id: Int) = projectRepository.deleteById(id)
}

@Service
class MemberService(@Autowired val memberRepository: MemberRepository) {

    fun findAll(): List<Member> = memberRepository.findAll()

    fun findById(id: Int): Member? = memberRepository.findById(id).orElse(null)

    fun save(member: Member): Member = memberRepository.save(member)

    fun deleteById(id: Int) = memberRepository.deleteById(id)
}
