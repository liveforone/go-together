package gotogether.gotogether.member.domain

import gotogether.gotogether.member.domain.util.PasswordUtil
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MemberTest {

    @Test
    fun updatePwTest() {
        //given
        val email = "test_update_pw@gmail.com"
        val pw = "1234"
        val bankbookNum = "1234567898765"
        val member = Member.create(email, pw, bankbookNum, Role.MEMBER)

        //when
        val newPw = "1111"
        member.updatePw(newPw, pw)

        //then
        Assertions.assertThat(PasswordUtil.isMatchPassword(newPw, member.pw))
            .isTrue()
    }

    @Test
    fun updateBankbookNum() {
        //given
        val email = "test_update_pw@gmail.com"
        val pw = "1234"
        val bankbookNum = "1234567898765"
        val member = Member.create(email, pw, bankbookNum, Role.MEMBER)

        //when
        val newBankbookNum = "9876543212345"
        member.updateBankbookNum(newBankbookNum)

        //when
        Assertions.assertThat(member.bankbookNum)
            .isEqualTo(newBankbookNum)
    }
}