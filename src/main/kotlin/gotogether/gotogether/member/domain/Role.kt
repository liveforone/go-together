package gotogether.gotogether.member.domain

enum class Role(val auth:String) {
    MEMBER("ROLE_MEMBER"), DRIVER("ROLE_DRIVER"), ADMIN("ROLE_ADMIN")
}