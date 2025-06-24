package com.market.Dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
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
    
	 @Size(min = 6,max = 20, message = "Şifre en az 6 karakter olmalı")
	private String password;
	 
	 @Pattern(regexp = "^(ADMIN|USER)$", message = "Role sadece ADMIN veya USER olabilir")
	    private String role;
	 
	 	@Pattern(regexp = "^\\+?\\d{10,15}$", message = "Geçerli bir telefon numarası giriniz (10-15 hane, opsiyonel + ile)")
	    private String phone;

	    @Size(max = 255, message = "Adres maksimum 255 karakter olabilir")
	    private String address;

	    @NotBlank(message = "İsim soyisim boş olamaz")
	    @Size(max = 50, message = "İsim soyisim maksimum 50 karakter olabilir")
	    private String fullName;

	    @NotBlank(message = "Ödeme yöntemi boş olamaz")
	    @Size(min = 5,max = 30, message = "Ödeme yöntemi maksimum 30 karakter olabilir")
	    private String paymentMethod;
}
