package gotogether.gotogether.exception.message

enum class MemberMessage(val message: String) {
    WRONG_PASSWORD("비밀번호를 틀렸습니다."), MEMBER_IS_NULL("회원이 존재하지 않습니다.")
}