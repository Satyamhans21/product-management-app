package com.gspann.auth_service.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class AuthModel {
	private String username;
	private String password;

}
