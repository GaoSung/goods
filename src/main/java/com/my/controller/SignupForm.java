package com.my.controller;

import com.my.domain.Account;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class SignupForm {

	private static final String NOT_BLANK_MESSAGE = "不能为空！";
	private static final String USERNAME_MESSAGE = "长度不能小于8个字符！";

    @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
	@Length(min = 8,message = SignupForm.USERNAME_MESSAGE)
	private String userName;

    @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
	private String password;

	public Account createAccount() {
        return new Account(getUserName(), getPassword(), "ROLE_USER");
	}
}
