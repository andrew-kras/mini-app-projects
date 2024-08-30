package kras.projects.app.datasource.mock

import kras.projects.app.model.Member
import kras.projects.app.model.Project
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProjectRepository : JpaRepository<Project, Int>

@Repository
interface MemberRepository : JpaRepository<Member, Int>