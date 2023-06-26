package gotogether.gotogether.member.service.command

import gotogether.gotogether.jwt.JwtTokenProvider
import gotogether.gotogether.jwt.TokenInfo
import gotogether.gotogether.member.domain.Member
import gotogether.gotogether.member.domain.Role
import gotogether.gotogether.member.dto.request.LoginRequest
import gotogether.gotogether.member.dto.request.SignupRequest
import gotogether.gotogether.member.dto.update.UpdateBankbookNum
import gotogether.gotogether.member.dto.update.UpdatePassword
import gotogether.gotogether.member.repository.MemberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemberCommandService @Autowired constructor(
    private val memberRepository: MemberRepository,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
    private val jwtTokenProvider: JwtTokenProvider
) {

    fun createMember(signupRequest: SignupRequest): String {
        return Member.create(signupRequest.email!!, signupRequest.pw!!, signupRequest.bankbookNum!!, Role.MEMBER)
            .also {
                memberRepository.save(it)
            }.identity
    }

    fun createDriver(signupRequest: SignupRequest): String {
        return Member.create(signupRequest.email!!, signupRequest.pw!!, signupRequest.bankbookNum!!, Role.DRIVER)
            .also {
                memberRepository.save(it)
            }.identity
    }

    fun login(loginRequest: LoginRequest): TokenInfo {
        val member = memberRepository.findOneByEmail(loginRequest.email!!)

        authenticationManagerBuilder.also {
            it.`object`.authenticate(
                UsernamePasswordAuthenticationToken(loginRequest.email, loginRequest.pw)
            )
        }

        return jwtTokenProvider.generateToken(member)
    }

    fun updatePassword(updatePassword: UpdatePassword, identity: String) {
        memberRepository.findOneByIdentity(identity)
            .also {
                it.updatePw(updatePassword.password!!, updatePassword.oldPassword!!)
            }
    }

    fun updateBankbookNum(updateBankbookNum: UpdateBankbookNum, identity: String) {
        memberRepository.findOneByIdentity(identity)
            .also {
                it.updateBankbookNum(updateBankbookNum.bankbookNum!!)
            }
    }
}