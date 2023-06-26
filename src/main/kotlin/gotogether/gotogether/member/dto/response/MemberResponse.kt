package gotogether.gotogether.member.dto.response

import gotogether.gotogether.member.domain.Role

data class MemberResponse(
    val id: Long,
    val bankbookNum: String,
    val auth: Role
)
