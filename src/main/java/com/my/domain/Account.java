package com.my.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@SuppressWarnings("serial")
@Entity
@Table(name = "account")
@Data
public class Account extends AbstractAuditingEntity implements java.io.Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true)
	private String userName;
	
	@JsonIgnore
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
