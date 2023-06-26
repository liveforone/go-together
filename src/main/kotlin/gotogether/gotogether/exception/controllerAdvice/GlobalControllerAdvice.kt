package gotogether.gotogether.exception.controllerAdvice

import gotogether.gotogether.exception.exception.BindingCustomException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalControllerAdvice {

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun duplicateEntityValueExceptionHandle(): ResponseEntity<*> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body("해당 값은 반드시 UNIQUE 여야 합니다.")
    }

    @ExceptionHandler(BindingCustomException::class)
    fun bindingErrorHandle(customException: BindingCustomException): ResponseEntity<*> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(customException.message)
    }
}