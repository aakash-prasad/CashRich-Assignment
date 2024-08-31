package com.cashrich.backend.Service;

import com.cashrich.backend.DTO.*;

import java.util.List;
import java.util.Map;

public interface LoginService {

    UserDto createUser(RegistrationRequestDto registrationRequestDto);

    Map<String,String> authenticateUser(LoginRequestDto loginRequestDto);

    UserDto updateUser(UpdateUserDto updateUserDto);

    CoinMarketResponseDto getResponse(List<String> symbols);
}
