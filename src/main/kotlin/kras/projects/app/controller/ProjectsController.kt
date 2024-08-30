package kras.projects.app.controller

import kras.projects.app.model.Member
import kras.projects.app.model.Project
import kras.projects.app.service.MemberService
import kras.projects.app.service.ProjectService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/projects")
class ProjectController(@Autowired val projectService: ProjectService) {

    @GetMapping
    fun getAllProjects(): List<Project> = projectService.findAll()

    @GetMapping("/{id}")
    fun getProjectById(@PathVariable id: Int): Project? = projectService.findById(id)

    @PostMapping
    fun createProject(@RequestBody project: Project): Project = projectService.save(project)

    @DeleteMapping("/{id}")
    fun deleteProject(@PathVariable id: Int) = projectService.deleteById(id)
}

@RestController
@RequestMapping("/members")
class MemberController(@Autowired val memberService: MemberService) {

    @GetMapping
    fun getAllMembers(): List<Member> = memberService.findAll()

    @GetMapping("/{id}")
    fun getMemberById(@PathVariable id: Int): Member? = memberService.findById(id)

    @PostMapping
    fun createMember(@RequestBody member: Member): Member = memberService.save(member)

    @DeleteMapping("/{id}")
    fun deleteMember(@PathVariable id: Int) = memberService.deleteById(id)
}