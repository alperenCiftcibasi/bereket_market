package com.market.Dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    
	@NotEmpty(message = "username alanı boş bırakılamaz")
	@Size(min=3,max = 30,message = "username en az 3 en fazla 30 karakterden oluşmalı")
	private String username;
    
	@Email(message = "Geçerli bir e-posta adresi giriniz !")
    @NotBlank(message = "E-posta boş olamaz")
	private String email;
    
	 @Size(min = 6, message = "Şifre en az 6 karakter olmalı")
	private String password;
}
