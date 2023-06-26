package gotogether.gotogether.exception

import java.lang.RuntimeException

class JwtCustomException(message:String) : RuntimeException(message) {
}