package gotogether.gotogether.member.dto.util

import gotogether.gotogether.member.domain.Member
import gotogether.gotogether.member.dto.response.MemberResponse

object MemberMapper {

    fun entityToDto(member: Member): MemberResponse = MemberResponse(member.id!!, member.bankbookNum, member.auth)
}