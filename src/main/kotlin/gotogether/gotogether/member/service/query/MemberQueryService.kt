package gotogether.gotogether.member.service.query

import gotogether.gotogether.member.dto.response.MemberResponse
import gotogether.gotogether.member.dto.util.MemberMapper
import gotogether.gotogether.member.repository.MemberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberQueryService @Autowired constructor(
    private val memberRepository: MemberRepository
) {

    fun getMemberById(id: Long): MemberResponse = MemberMapper.entityToDto(memberRepository.findOneById(id))

    fun getMemberByIdentity(identity: String): MemberResponse = MemberMapper.entityToDto(memberRepository.findOneByIdentity(identity))
}