package com.my.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "account")
@Data
public class Account extends AbstractAuditingEntity implements java.io.Serializable {

	private static final String NOT_BLANK_MESSAGE = "不能为空！";
	private static final String USERNAME_MESSAGE = "长度不能小于8个字符！";

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true)
	@NotBlank(message = Account.NOT_BLANK_MESSAGE)
	@Length(min = 8,message = Account.USERNAME_MESSAGE)
	private String userName;
	
	@JsonIgnore
	@NotBlank(message = Account.NOT_BLANK_MESSAGE)
	private String password;

	private String role;

    public Account() {

	}
	
	public Account(String userName, String password, String role) {
		this.userName = userName;
		this.password = password;
		this.role = role;
	}
}
