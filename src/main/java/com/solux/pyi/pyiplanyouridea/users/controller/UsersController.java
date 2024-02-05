package com.solux.pyi.pyiplanyouridea.users.controller;

import com.solux.pyi.pyiplanyouridea.users.domain.Users;
import com.solux.pyi.pyiplanyouridea.users.dto.*;
import com.solux.pyi.pyiplanyouridea.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class UsersController {

    private final UsersService usersService;

    // 회원 가입
    @PostMapping("/register")
    public ResponseEntity<Users> register(@Valid @RequestBody JoinRequestDto joinRequestDto, BindingResult bindingResult) {
        try {
            // 아이디 중복 체크
            if (usersService.checkUserIdDuplicate(joinRequestDto.getUserId())) {
                bindingResult.addError(new FieldError("joinRequestDto", "userId", "중복된 아이디 입니다"));
            }

            // 이메일 중복체크
            if (usersService.checkEmailDuplicate(joinRequestDto.getUserEmail())) {
                bindingResult.addError(new FieldError("joinRequestDto", "email", "중복된 이메일입니다"));
            }

//            // password와 passwordCheck가 같은지 확인
//            if (!joinRequestDto.getPassword().equals(joinRequestDto.getPasswordCheck())) {
//                bindingResult.addError(new FieldError("joinRequestDto", "passwordCheck", "비밀번호가 일치하지 않습니다"));
//            }

            // 유효성 검사 실패시 에러발생
            if (bindingResult.hasErrors()) {

                return ResponseEntity.badRequest().build();
            }

            Users newUser = usersService.join(joinRequestDto);

            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }



    @PostMapping("/register/id/check")
    public ResponseEntity<Boolean> checkUserIdDuplicate(@RequestBody String userId) {

        boolean isDuplicate = usersService.checkUserIdDuplicate(userId);

        return ResponseEntity.ok(isDuplicate);
    }

    @PostMapping("/register/email/check")
    public ResponseEntity<Boolean> checkEmailDuplicate(@RequestBody String userEmail) {

        boolean isDuplicate = usersService.checkEmailDuplicate(userEmail);

        return ResponseEntity.ok(isDuplicate);
    }

    // 아이디 찾기
    @PostMapping("/findId")
    public ResponseEntity<String> findUserId(@RequestBody FindUserIdRequestDto findUserIdRequestDto) {
        String userId = usersService.findUserId(findUserIdRequestDto.getUserEmail());
        if (userId != null) {
            return ResponseEntity.ok(userId);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 비밀번호 재설정
    @PutMapping("/resetPw")
    public ResponseEntity<String> resetPassword(@RequestBody PasswordResetRequestDto passwordResetRequestDto) {
        boolean isReset = usersService.resetPassword(passwordResetRequestDto.getUserId(), passwordResetRequestDto.getNewPassword());
        if (isReset) {
            return ResponseEntity.ok("비밀번호가 재설정되었습니다.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 로그인
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto) {
//
//        String userId = loginRequestDto.getUserId();
//        String password = loginRequestDto.getPassword();
//
//        if (userId == null || password == null || userId.isEmpty() || password.isEmpty()) {
//            return ResponseEntity.badRequest().body("아이디와 비밀번호를 입력하세요.");
//        }
//
//        Users user = usersService.login(loginRequestDto);
//
//        if (user != null) {
//            return ResponseEntity.ok("로그인에 성공하였습니다.");
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인에 실패하였습니다. 아이디 또는 비밀번호를 확인하세요.");
//        }
//    }
    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
    //public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {

//        String userId = loginRequestDto.getUserId();
//        String password = loginRequestDto.getPassword();

//        if (userId == null || password == null || userId.isEmpty() || password.isEmpty()) {
//            return null;
//        }

        return usersService.login(loginRequestDto);

//        if (userId == null || password == null || userId.isEmpty() || password.isEmpty()) {
//            return ResponseEntity.badRequest().body("아이디와 비밀번호를 입력하세요.");
//        }
//
//        Users user = usersService.login(loginRequestDto);
//
//        if (user != null) {
//            return ResponseEntity.ok("로그인에 성공하였습니다.");
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인에 실패하였습니다. 아이디 또는 비밀번호를 확인하세요.");
//        }
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return ResponseEntity.ok("로그아웃되었습니다.");
    }

    // 회원정보 조회
    @GetMapping("/user/{userUuid}")
    public ResponseEntity<Users> findByUserUuid(@Valid @PathVariable("userUuid") Long userUuid){
        try {
            Users userProfile = usersService.findByUserUuid(userUuid);
            return ResponseEntity.ok(userProfile);
        } catch (IllegalArgumentException e) {
            // 잘못된 UUID 문자열인 경우 처리
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
