package gotogether.gotogether.jwt.constant

object JwtConstant {
    const val ACCESS_TOKEN = "access-token"
    const val REFRESH_TOKEN = "refresh-token"
    const val HEADER = "Authorization"
    const val CLAIM_NAME = "auth"
    const val TWO_HOUR_MS = 7200000
    const val BEARER_TOKEN = "Bearer"
    const val SECRET_KEY_PATH = "\${jwt.secret}"
    const val TOKEN_SUB_INDEX = 7
    const val TOKEN_IS_NULL = "Token Is Null"
    const val INVALID_MESSAGE = "Invalid JWT Token"
    const val EXPIRED_MESSAGE = "Expired JWT Token"
    const val UNSUPPORTED_MESSAGE = "Unsupported JWT Token"
    const val EMPTY_CLAIMS = "JWT claims string is empty."
}