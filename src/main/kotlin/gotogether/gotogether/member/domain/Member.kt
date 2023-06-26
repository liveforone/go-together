package gotogether.gotogether.member.domain

import gotogether.gotogether.converter.RoleConverter
import gotogether.gotogether.exception.exception.MemberCustomException
import gotogether.gotogether.exception.message.MemberMessage
import gotogether.gotogether.member.domain.util.PasswordUtil
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

@Entity
class Member private constructor(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long?,
    @Column(unique = true) val identity: String,
    @Column(unique = true) val email: String,
    var pw: String,
    var bankbookNum: String,
    @Convert(converter = RoleConverter::class) val auth: Role
) : UserDetails {

    companion object {
        private fun createIdentity(): String = UUID.randomUUID().toString()

        fun create(email: String, pw: String, bankbookNum: String, auth: Role): Member {
            val adminEmail = "admin_go_together@gmail.com"

            return Member(
                null,
                createIdentity(),
                email,
                PasswordUtil.encodePassword(pw),
                bankbookNum,
                if (email == adminEmail) Role.ADMIN else auth
            )
        }
    }

    fun updatePw(newPassword: String, oldPassword: String) {
        if (!PasswordUtil.isMatchPassword(oldPassword, this.pw)) throw MemberCustomException(MemberMessage.WRONG_PASSWORD.message)
        this.pw = PasswordUtil.encodePassword(newPassword)
    }

    fun updateBankbookNum(bankbookNum: String) {
        this.bankbookNum = bankbookNum
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authList = arrayListOf<GrantedAuthority>()
        authList.add(SimpleGrantedAuthority(auth.auth))
        return authList
    }

    override fun getPassword(): String {
        return pw
    }

    override fun getUsername(): String {
        return identity
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}