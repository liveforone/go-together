package gotogether.gotogether.member.service.command

import gotogether.gotogether.member.domain.Role
import gotogether.gotogether.member.dto.request.LoginRequest
import gotogether.gotogether.member.dto.request.SignupRequest
import gotogether.gotogether.member.dto.update.UpdateBankbookNum
import gotogether.gotogether.member.dto.update.UpdatePassword
import gotogether.gotogether.member.service.query.MemberQueryService
import jakarta.persistence.EntityManager
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
class MemberCommandServiceTest @Autowired constructor(
    private val entityManager: EntityManager,
    private val memberCommandService: MemberCommandService,
    private val memberQueryService: MemberQueryService
) {

    @Test
    @Transactional
    fun createMemberTest() {
        //given
        val email = "create_test@gmail.com"
        val password = "1234"
        val bankbookNum = "1234567898765"
        val request = SignupRequest(email, password, bankbookNum)

        //when
        val identity: String = memberCommandService.createMember(request)
        entityManager.flush()
        entityManager.clear()

        //then
        Assertions.assertThat(memberQueryService.getMemberByIdentity(identity).bankbookNum)
            .isEqualTo(bankbookNum)
    }

    @Test
    @Transactional
    fun createDriverTest() {
        //given
        val email = "create_driver_test@gmail.com"
        val password = "1234"
        val bankbookNum = "1234567898765"
        val request = SignupRequest(email, password, bankbookNum)

        //when
        val identity: String = memberCommandService.createDriver(request)
        entityManager.flush()
        entityManager.clear()

        //then
        Assertions.assertThat(memberQueryService.getMemberByIdentity(identity).auth)
            .isEqualTo(Role.DRIVER)
    }

    @Test
    @Transactional
    fun updatePasswordTest() {
        //given
        val email = "updatePassword@gmail.com"
        val password = "1234"
        val bankbookNum = "1234567898765"
        val request = SignupRequest(email, password, bankbookNum)
        val identity: String = memberCommandService.createMember(request)
        entityManager.flush()
        entityManager.clear()

        //when
        val newPw = "1111"
        val updateRequest = UpdatePassword(newPw, password)
        memberCommandService.updatePassword(updateRequest, identity)
        entityManager.flush()
        entityManager.clear()

        //when
        val loginRequest = LoginRequest(email, newPw)
        Assertions.assertThat(memberCommandService.login(loginRequest).accessToken)
            .isNotBlank()
    }

    @Test
    @Transactional
    fun updateBankbookNumTest() {
        //given
        val email = "updatePassword@gmail.com"
        val password = "1234"
        val bankbookNum = "1234567898765"
        val request = SignupRequest(email, password, bankbookNum)
        val identity: String = memberCommandService.createMember(request)
        entityManager.flush()
        entityManager.clear()

        //when
        val newBankbookNum = "9876543212345"
        val updateRequest = UpdateBankbookNum(newBankbookNum)
        memberCommandService.updateBankbookNum(updateRequest, identity)
        entityManager.flush()
        entityManager.clear()

        //then
        Assertions.assertThat(memberQueryService.getMemberByIdentity(identity).bankbookNum)
            .isEqualTo(newBankbookNum)
    }
}