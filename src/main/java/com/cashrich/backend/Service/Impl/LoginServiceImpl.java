package com.cashrich.backend.Service.Impl;

import com.cashrich.backend.DAO.UserDao;
import com.cashrich.backend.DTO.*;
import com.cashrich.backend.Entity.RequestResponseLogs;
import com.cashrich.backend.Entity.User;
import com.cashrich.backend.Service.LoginService;
import com.cashrich.backend.mapper.UserMapper;
import com.cashrich.backend.util.EncryptionUtlity;
import com.cashrich.backend.util.JwtTokenUtility;
import external.CoinMarketClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserDao userDao;

    private final CoinMarketClient coinMarketClient;


    @Override
    public UserDto createUser(RegistrationRequestDto registrationRequestDto) {

        Optional<User> userOptional=userDao.findUserByEmail(registrationRequestDto.getEmail());

        if (userOptional.isPresent()){
            throw new IllegalArgumentException("User already exists againtst this email");
        }
        String salt = EncryptionUtlity.generateRandomSalt();
        String encodedPassword = EncryptionUtlity.getEncryptedPassword(registrationRequestDto.getPassword(),salt);

        User user=User.builder()
                .email(registrationRequestDto.getEmail())
                .firstName(registrationRequestDto.getFirstName())
                .lastName(registrationRequestDto.getLastName())
                .userName(registrationRequestDto.getUserName())
                .mobile(registrationRequestDto.getMobile())
                        .password(encodedPassword)
                .hash(salt)
                .build();
        userDao.createUser(user);
        return null;
    }

    @Override
    public Map<String, String> authenticateUser(LoginRequestDto loginRequestDto) {

        Optional<User> userOptional=userDao.findUserByEmail(loginRequestDto.getEmail());

        if (userOptional.isEmpty()){
            throw new IllegalArgumentException("User already exists againtst this email");
        }

        User user = userOptional.get();
        String encryptedPassword = EncryptionUtlity.getEncryptedPassword(loginRequestDto.getPassword(),user.getHash());

        if (!encryptedPassword.equals(loginRequestDto.getPassword())){
            throw new IllegalArgumentException("Password incorrect");
        }

        return JwtTokenUtility.createJwtForTheUser(user);
    }

    @Override
    public UserDto updateUser(UpdateUserDto updateUserDto) {

        Optional<User> userOptional=userDao.findUserByEmail(updateUserDto.getEmail());
        if (userOptional.isEmpty()){
            throw new IllegalArgumentException("User does not exist");
        }
        String salt = EncryptionUtlity.generateRandomSalt();
        String encodedPassword = EncryptionUtlity.getEncryptedPassword(updateUserDto.getPassword(),salt);

        User user = userOptional.get();
        user.setFirstName(updateUserDto.getFirstName());
        user.setLastName(updateUserDto.getLastName());
        user.setMobile(updateUserDto.getMobile());
        user.setPassword(encodedPassword);
        return UserMapper.convertToDto(userDao.updateUser(user));
    }

    @Override
    public CoinMarketResponseDto getResponse(List<String> symbols) {

        Optional<User> userOptional=userDao.findUserByEmail(JwtTokenUtility.getUserForCurrentSession());
        if (userOptional.isEmpty()){
            throw new IllegalArgumentException("User does not exist");
        }


        CoinMarketResponseDto coinMarketResponseDto=coinMarketClient.getCoinsMarketDetails(symbols);


        RequestResponseLogs requestResponseLogs=RequestResponseLogs.builder()
                .userid(userOptional.get().getId())
                .type("Coin Fetch")
                .response(coinMarketResponseDto)
                .timestamp(LocalDateTime.now())
                .build();

        userDao.saveResponse(requestResponseLogs);

        return coinMarketResponseDto;
    }


}
