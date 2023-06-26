package gotogether.gotogether.validator

import gotogether.gotogether.exception.exception.BindingCustomException
import org.springframework.stereotype.Component
import org.springframework.validation.BindingResult
import java.util.*

@Component
class ControllerValidator {

    fun validateBinding(bindingResult: BindingResult) {
        if (bindingResult.hasErrors()) {
            val errorMessage = Objects.requireNonNull(bindingResult.fieldError)?.defaultMessage
            throw errorMessage?.let { BindingCustomException(it) }!!
        }
    }
}