package gotogether.gotogether.member.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class SignupRequest(
    @field:NotBlank(message = "이메일을 입력하세요.")
    var email:String?,
    @field:NotBlank(message = "비밀번호를 입력하세요.")
    var pw:String?,
    @field:NotBlank(message = "계좌번호를 입력하세요.")
    @field:Size(min = 13, max = 13)
    var bankbookNum: String?
)