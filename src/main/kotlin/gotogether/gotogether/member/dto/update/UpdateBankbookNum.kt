package gotogether.gotogether.member.dto.update

import jakarta.validation.constraints.NotBlank

data class UpdateBankbookNum(
    @field:NotBlank(message = "새 계좌번호를 입력하세요.")
    var bankbookNum: String?
)