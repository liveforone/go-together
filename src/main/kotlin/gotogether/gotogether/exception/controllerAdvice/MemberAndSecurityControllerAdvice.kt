package gotogether.gotogether.exception.controllerAdvice

import gotogether.gotogether.exception.exception.JwtCustomException
import gotogether.gotogether.exception.exception.MemberCustomException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class MemberAndSecurityControllerAdvice {

    @ExceptionHandler(BadCredentialsException::class)
    fun loginFailHandle(): ResponseEntity<*> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body("로그인에 실패했습니다.")
    }

    @ExceptionHandler(MemberCustomException::class)
    fun memberCustomExceptionHandle(memberCustomException: MemberCustomException): ResponseEntity<*> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(memberCustomException.message)
    }

    @ExceptionHandler(JwtCustomException::class)
    fun jwtCustomException(jwtCustomException: JwtCustomException): ResponseEntity<*> {
        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(jwtCustomException.message)
    }
}