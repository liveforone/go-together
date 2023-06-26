package gotogether.gotogether.member.controller.response

import gotogether.gotogether.member.dto.response.MemberResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

object MemberResponse {

    private const val SIGNUP_SUCCESS = "회원가입에 성공하였습니다.\n반갑습니다."
    private const val LOGIN_SUCCESS = "로그인에 성공하였습니다.\n환영합니다."
    private const val UPDATE_PW_SUCCESS = "비밀번호를 성공적으로 변경하였습니다."
    private const val UPDATE_BANKBOOK_NUM_SUCCESS = "계좌번호를 성공적으로 변경하였습니다."

    fun signupSuccess(): ResponseEntity<*> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(SIGNUP_SUCCESS)
    }

    fun loginSuccess(): ResponseEntity<*> = ResponseEntity.ok(LOGIN_SUCCESS)

    fun infoSuccess(member: MemberResponse): ResponseEntity<*> = ResponseEntity.ok(member)

    fun updatePwSuccess(): ResponseEntity<*> = ResponseEntity.ok(UPDATE_PW_SUCCESS)

    fun updateBankbookNumSuccess(): ResponseEntity<*> = ResponseEntity.ok(UPDATE_BANKBOOK_NUM_SUCCESS)
}