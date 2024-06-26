package com.study.devcommunitybackend.common.exception

import com.study.devcommunitybackend.common.data.dto.BaseResponseDto
import com.study.devcommunitybackend.common.status.ResultCode
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.MalformedJwtException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class) // Valid에서 걸린 경우 발생
    protected fun handleValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<BaseResponseDto<Map<String, String>>> {
        val errors = mutableMapOf<String, String>()
        ex.bindingResult.allErrors.forEach { error ->
            val fieldName = (error as FieldError).field
            val errorMessage = error.getDefaultMessage()
            errors[fieldName] = errorMessage ?: "Not Exception Message"
        }
        return ResponseEntity(BaseResponseDto(ResultCode.ERROR.name, errors, ResultCode.ERROR.msg), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class) // 그 외 모든 exception
    protected fun defaultException(ex: Exception): ResponseEntity<BaseResponseDto<Map<String, String>>> {
        val errors = mapOf("미처리 에러" to (ex.message ?: "Not Exception Message"))
        return ResponseEntity(BaseResponseDto(ResultCode.ERROR.name, errors, ResultCode.ERROR.msg), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(InvalidInputException::class) // 사용자 생성 exception
    protected fun invalidInputException(ex: InvalidInputException): ResponseEntity<BaseResponseDto<Map<String, String>>> {
        val errors = mapOf(ex.fieldName to (ex.message ?: "Not Exception Message"))
        return ResponseEntity(BaseResponseDto(ResultCode.ERROR.name, errors, ResultCode.ERROR.msg), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(BadCredentialsException::class)
    protected fun badCredentialsException(ex: BadCredentialsException): ResponseEntity<BaseResponseDto<Map<String, String>>> {
        val errors = mapOf("로그인 실패" to "아이디 혹은 비밀번호를 다시 확인하세요.")
        return ResponseEntity(BaseResponseDto(ResultCode.ERROR.name, errors, ResultCode.ERROR.msg), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(UsernameNotFoundException::class)
    protected fun usernameNotFoundException(ex: UsernameNotFoundException): ResponseEntity<BaseResponseDto<Map<String, String>>> {
        val errors = mapOf("로그인 실패" to "해당하는 유저를 찾을 수 없습니다.")
        return ResponseEntity(BaseResponseDto(ResultCode.ERROR.name, errors, ResultCode.ERROR.msg), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ExpiredJwtException::class)
    protected fun expiredJwtException(ex: ExpiredJwtException): ResponseEntity<BaseResponseDto<Map<String, String>>> {
        val errors = mapOf("토큰 만료" to "토큰이 만료되었습니다. 다시 로그인 하세요.")
        return ResponseEntity(BaseResponseDto(ResultCode.ERROR.name, errors, ResultCode.ERROR.msg), HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(MalformedJwtException::class)
    protected fun malformedJwtException(ex: MalformedJwtException): ResponseEntity<BaseResponseDto<Map<String, String>>> {
        val errors = mapOf("손상된 토큰" to "올바르지 않은 토큰입니다.")
        return ResponseEntity(BaseResponseDto(ResultCode.ERROR.name, errors, ResultCode.ERROR.msg), HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    protected fun illegalArgumentException(ex: IllegalArgumentException): ResponseEntity<BaseResponseDto<Map<String, String>>> {
        val errors = mapOf("잘못된 토큰" to "올바르지 않은 토큰입니다.")
        return ResponseEntity(BaseResponseDto(ResultCode.ERROR.name, errors, ResultCode.ERROR.msg), HttpStatus.UNAUTHORIZED)
    }
}