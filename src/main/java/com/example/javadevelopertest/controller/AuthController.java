package com.example.javadevelopertest.controller;

import com.example.javadevelopertest.model.LoginRequest;
import com.example.javadevelopertest.model.LoginResponse;
import com.example.javadevelopertest.model.RegisterUserRequest;
import com.example.javadevelopertest.security.JwtTokenProvider;
import com.example.javadevelopertest.security.MyUserDetails;
import com.example.javadevelopertest.service.UserAService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserAService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MyUserDetails myUserDetails;

//    private final AuthenticationManager authenticationManager;

//    private final JwtUtil jwtTokenProvider;


    @GetMapping("/")
    public ResponseEntity<?> getAllJob() {
        try {
            return ResponseEntity.ok("OK");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
//        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

//            UserDetails userDetails = myUserDetails.loadUserByUsername(loginRequest.getUsername());

//            if (!passwordEncoder.matches(passwordEncoder.encode(loginRequest.getPassword()), loginRequest.getPassword())) {
//                throw new BadCredentialsException("Invalid password");
//            }

            String token = jwtTokenProvider.createToken(loginRequest.getUsername());
            return ResponseEntity.ok(new LoginResponse(token));
//        } catch (UsernameNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
//        } catch (BadCredentialsException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//        } catch (AuthenticationException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Authentication error");
//        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> login(@RequestBody RegisterUserRequest registerUserRequest) {

        userService.register(registerUserRequest);

        return ResponseEntity.ok("OK");

    }
}
