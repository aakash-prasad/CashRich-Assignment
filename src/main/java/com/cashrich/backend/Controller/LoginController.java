package com.cashrich.backend.Controller;

import com.cashrich.backend.DTO.*;
import com.cashrich.backend.Service.LoginService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@Log4j2
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUpUser(@RequestBody RegistrationRequestDto registrationRequestDto) {
        return new ResponseEntity<>(loginService.createUser(registrationRequestDto), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto) {
        return new ResponseEntity<>(loginService.authenticateUser(loginRequestDto), HttpStatus.OK);
    }

    @PostMapping("/updateDetail")
    public ResponseEntity<UserDto> updateUserDetail(@RequestBody UpdateUserDto updateUserDto) {
        return new ResponseEntity<>(loginService.updateUser(updateUserDto), HttpStatus.OK);
    }

    @GetMapping("/fetch-crypto")
    public CoinMarketResponseDto fetchCryptoData(@RequestParam("symbols") List<String> symbols) {
        log.info("Data fetched and stored successfully");
        return loginService.getResponse(symbols);

    }

}
