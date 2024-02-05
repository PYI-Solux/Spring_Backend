package com.solux.pyi.pyiplanyouridea.users.service;

import com.solux.pyi.pyiplanyouridea.users.domain.Users;
import com.solux.pyi.pyiplanyouridea.users.domain.UsersRole;
import com.solux.pyi.pyiplanyouridea.users.dto.JoinRequestDto;
import com.solux.pyi.pyiplanyouridea.users.dto.LoginRequestDto;
import com.solux.pyi.pyiplanyouridea.users.dto.LoginResponseDto;
import com.solux.pyi.pyiplanyouridea.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UsersService implements UserDetailsService {

    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Users user = usersRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with userId: " + userId));

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getUserRole().toString()));

        return new org.springframework.security.core.userdetails.User(user.getUserId(), user.getPassword(), authorities);
    }

    //userId 중복체크
    @Transactional
    public boolean checkUserIdDuplicate(String userId) {
        return usersRepository.existsByUserId(userId);
    }

    //이메일 중복체크
    @Transactional
    public boolean checkEmailDuplicate(String userEmail) {
        return usersRepository.existsByUserEmail(userEmail);
    }

    // 회원가입
    @Transactional
    public Users join(JoinRequestDto joinRequestDto) {
        Users newUser = Users.builder()
                .userId(joinRequestDto.getUserId())
                .userEmail(joinRequestDto.getUserEmail())
                .password(encoder.encode(joinRequestDto.getPassword()))
                // 기본 권한은 ROLE_USER로 설정
                .userRole(UsersRole.ROLE_USER)
                .build();

        return usersRepository.save(newUser);
    }

    // 로그인
    @Transactional
//    public Users login(LoginRequestDto loginRequestDto) {
//        Users users = usersRepository.findByUserId(loginRequestDto.getUserId())
//                .orElse(null);
//
//        // userId 존재X or password 일치X
//        if (users == null || !encoder.matches(loginRequestDto.getPassword(), users.getPassword())) {
//            return null;
//        }
//
//        // userId와 password가 일치할 경우
//        return users;
//    }
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Users users = usersRepository.findByUserId(loginRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 users가 없습니다. userId = " + loginRequestDto.getUserId()));

//        // userId 존재X or password 일치X
//        if (users == null || !encoder.matches(loginRequestDto.getPassword(), users.getPassword())) {
//            return null;
//        }

        // userId와 password가 일치할 경우
        return new LoginResponseDto(users);
    }

    // 인증, 인가 - userUuid
    @Transactional
    public Users getLoginUserByUserUuid(Long userUuid) {
        return usersRepository.findById(userUuid)
                .orElse(null);
    }

    // 인증, 인가 - userId
    @Transactional
    public Users getLoginUserByUserId(String userId) {
        return usersRepository.findByUserId(userId)
                .orElse(null);
    }

    // 비밀번호 변경
    @Transactional
    public boolean resetPassword(String userId, String newPassword) {
        // userId 받기
        Users users = usersRepository.findByUserId(userId)
                .orElse(null);

        if (users == null) {
            return false;
        }

        // newPassword 받기
        users.setPassword(encoder.encode(newPassword));
        usersRepository.save(users);

        return true;
    }

    // 이메일을 사용하여 아이디 찾기
    @Transactional
    public String findUserId(String userEmail) {
        Users users = usersRepository.findByUserEmail(userEmail)
                .orElse(null);

        return users != null ? users.getUserId() : null;
    }

    @Transactional
    public Users findByUserUuid(Long userUuid) {
        return usersRepository.findByUserUuid(userUuid);
    }

}
