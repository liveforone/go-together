package gotogether.gotogether.exception.message

enum class JwtMessage(val message: String) {
    TOKEN_IS_NULL("Token Is Null"), INVALID_MESSAGE("Invalid JWT Token"),
    EXPIRED_MESSAGE("Expired JWT Token"), UNSUPPORTED_MESSAGE("Unsupported JWT Token"),
    EMPTY_CLAIMS("JWT claims string is empty.")
}