package gotogether.gotogether.member.domain.util

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

object PasswordUtil {
    private val passwordEncoder = BCryptPasswordEncoder()

    fun encodePassword(password:String): String = passwordEncoder.encode(password)

    fun isMatchPassword(password: String, originalPassword:String): Boolean = passwordEncoder.matches(password, originalPassword)
}