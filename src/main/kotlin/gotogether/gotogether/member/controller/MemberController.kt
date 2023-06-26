package gotogether.gotogether.member.controller

import gotogether.gotogether.jwt.constant.JwtConstant
import gotogether.gotogether.member.controller.constant.MemberControllerLog
import gotogether.gotogether.member.controller.constant.MemberUrl
import gotogether.gotogether.member.controller.response.MemberResponse
import gotogether.gotogether.member.dto.request.LoginRequest
import gotogether.gotogether.validator.ControllerValidator
import gotogether.gotogether.member.dto.request.SignupRequest
import gotogether.gotogether.member.dto.update.UpdateBankbookNum
import gotogether.gotogether.member.dto.update.UpdatePassword
import gotogether.gotogether.member.service.command.MemberCommandService
import gotogether.gotogether.member.service.query.MemberQueryService
import jakarta.servlet.http.HttpServletResponse
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

inline fun <reified T> T.logger() = LoggerFactory.getLogger(T::class.java)!!

@RestController
class MemberController @Autowired constructor(
    private val memberCommandService: MemberCommandService,
    private val memberQueryService: MemberQueryService,
    private val controllerValidator: ControllerValidator
) {

    @PostMapping(MemberUrl.SIGNUP_MEMBER)
    fun signupMember(
        @RequestBody @Valid signupRequest: SignupRequest,
        bindingResult: BindingResult
    ): ResponseEntity<*> {
        controllerValidator.validateBinding(bindingResult)

        memberCommandService.createMember(signupRequest)
        logger().info(MemberControllerLog.SIGNUP_SUCCESS.log)

        return MemberResponse.signupSuccess()
    }

    @PostMapping(MemberUrl.SIGNUP_DRIVER)
    fun signupDriver(
        @RequestBody @Valid signupRequest: SignupRequest,
        bindingResult: BindingResult
    ): ResponseEntity<*> {
        controllerValidator.validateBinding(bindingResult)

        memberCommandService.createDriver(signupRequest)
        logger().info(MemberControllerLog.SIGNUP_SUCCESS.log)

        return MemberResponse.signupSuccess()
    }

    @PostMapping(MemberUrl.LOGIN)
    fun login(
        @RequestBody @Valid loginRequest: LoginRequest,
        bindingResult: BindingResult,
        response: HttpServletResponse
    ): ResponseEntity<*> {
        controllerValidator.validateBinding(bindingResult)

        val tokenInfo = memberCommandService.login(loginRequest)
        response.addHeader(JwtConstant.ACCESS_TOKEN, tokenInfo.accessToken)
        response.addHeader(JwtConstant.REFRESH_TOKEN, tokenInfo.refreshToken)
        logger().info(MemberControllerLog.LOGIN_SUCCESS.log)

        return MemberResponse.loginSuccess()
    }

    @GetMapping(MemberUrl.INFO)
    fun info(principal: Principal): ResponseEntity<*> {
        val member = memberQueryService.getMemberByIdentity(principal.name)
        return MemberResponse.infoSuccess(member)
    }

    @PutMapping(MemberUrl.UPDATE_PASSWORD)
    fun updatePassword(
        @RequestBody @Valid updatePassword: UpdatePassword,
        bindingResult: BindingResult,
        principal: Principal
    ): ResponseEntity<*> {
        controllerValidator.validateBinding(bindingResult)

        memberCommandService.updatePassword(updatePassword, principal.name)
        logger().info(MemberControllerLog.UPDATE_PW_SUCCESS.log)

        return MemberResponse.updatePwSuccess()
    }

    @PutMapping(MemberUrl.UPDATE_BANKBOOK_NUM)
    fun updateBankbookNum(
        @RequestBody @Valid updateBankbookNum: UpdateBankbookNum,
        bindingResult: BindingResult,
        principal: Principal
    ): ResponseEntity<*> {
        controllerValidator.validateBinding(bindingResult)

        memberCommandService.updateBankbookNum(updateBankbookNum, principal.name)
        logger().info(MemberControllerLog.UPDATE_BANKBOOK_NUM_SUCCESS.log)

        return MemberResponse.updateBankbookNumSuccess()
    }
}