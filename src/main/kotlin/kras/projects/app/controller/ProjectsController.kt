package kras.projects.app.controller

import kras.projects.app.model.Member
import kras.projects.app.model.Project
import kras.projects.app.service.ProjectsService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/projects")
class ProjectsController(private val service: ProjectsService) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handlerNotFound(e: NoSuchElementException): ResponseEntity<String> {
        return ResponseEntity(e.message, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handlerBadRequest(e: IllegalArgumentException): ResponseEntity<String> {
        return ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
    }

    @GetMapping()
    fun getProjects(): Collection<Project> = service.getProjects()

    @GetMapping("/{id}")
    fun getProject(@PathVariable id: Int) = service.getProject(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addProject(@RequestBody project: Project): Project = service.addProject(project)

    @PatchMapping
    fun updateProject(@RequestBody project: Project): Project = service.updateProject(project)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteProject(@PathVariable id: Int): Unit = service.deleteProjects(id)
}

@RestController
@RequestMapping("/api/members")
class MembersController(private val service: ProjectsService) {
    @ExceptionHandler(NoSuchElementException::class)
    fun handlerNotFound(e: NoSuchElementException): ResponseEntity<String> {
        return ResponseEntity(e.message, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handlerBadRequest(e: IllegalArgumentException): ResponseEntity<String> {
        return ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
    }

    @GetMapping()
    fun getMembers(): Collection<Member> = service.getMembers()

    @GetMapping("/{id}")
    fun getMembers(@PathVariable id: Int) = service.getMembers(id)

    @PostMapping("/project/{projectId}")
    @ResponseStatus(HttpStatus.CREATED)
    fun addMember(@RequestBody member: Member, @PathVariable projectId: Int): Member = service.addMember(projectId, member)

    @PatchMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun updateMember(@RequestBody member: Member): Member = service.updateMember(member)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteMember(@PathVariable id: Int): Unit = service.deleteMember(id)
}