package gotogether.gotogether.member.repository

import gotogether.gotogether.member.domain.Member

interface MemberCustomRepository {

    fun findOneById(id: Long): Member
    fun findOneByEmail(email: String): Member
    fun findOneByIdentity(identity: String): Member
}