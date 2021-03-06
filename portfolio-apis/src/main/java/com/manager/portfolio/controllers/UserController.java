package com.manager.portfolio.controllers;

import com.manager.portfolio.dto.BaseResponseDto;
import com.manager.portfolio.dto.LoginRequestDto;
import com.manager.portfolio.dto.UserDto;
import com.manager.portfolio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/portfolio/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/individual_signup", method = RequestMethod.POST)
    public BaseResponseDto signUpIndividualUser(@RequestBody UserDto userDto) {
        BaseResponseDto baseResponseDto = new BaseResponseDto();
        try {
            if(userDto != null) {
                if(!userDto.getPassword().equals(userDto.getConfirmPwd())) {
                    baseResponseDto.setError("Passwords Do Not Match!");
                } else {
                    userDto.setType("individual");
                    System.out.println("User Doc = " + userDto.toDocument());
                    boolean result = userService.addUser(userDto.toDocument());
                    if (result) {
                        baseResponseDto.setData("Success");
                    } else {
                        baseResponseDto.setError("User Already Exists!");
                    }
                }
            }
        } catch (Exception e) {
            baseResponseDto.setError(e);
        }
        return baseResponseDto;
    }

    @RequestMapping(value = "/company_signup", method = RequestMethod.POST)
    public BaseResponseDto signUpCompany(@RequestBody UserDto userDto) {
        BaseResponseDto baseResponseDto = new BaseResponseDto();
        try {
            if(userDto != null) {
                if(!userDto.getPassword().equals(userDto.getConfirmPwd())) {
                    baseResponseDto.setError("Passwords Do Not Match!");
                }else {
                    userDto.setType("company");
                    System.out.println("User Doc = " + userDto.toDocument());
                    boolean result = userService.addUser(userDto.toDocument());
                    if (result) {
                        baseResponseDto.setData("Success");
                    } else {
                        baseResponseDto.setError("User Already Exists!");
                    }
                }
            }
        } catch(Exception e) {
            baseResponseDto.setError(e);
        }
        return baseResponseDto;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
        BaseResponseDto baseResponseDto = new BaseResponseDto();
        try {
            if(loginRequestDto != null) {
                if(userService.validateLogin(loginRequestDto.toDocument())) {
                    baseResponseDto.setData("Success");
                } else {
                    baseResponseDto.setError("Username and Password do not match!");
                }
            }
        } catch(Exception e) {
            baseResponseDto.setError(e);
        }
        return baseResponseDto;
    }

}
