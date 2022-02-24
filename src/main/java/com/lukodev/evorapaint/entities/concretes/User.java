package com.lukodev.evorapaint.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "email")
    @NotNull(message = "Email adresi boş bırakılamaz.")
    @NotBlank(message = "Email adresi sadece boşluktan oluşamaz.")
    @Email(message = "Email adresi doğru formatta değil.")
    private String email;

    @Column(name = "password")
    @NotNull(message = "Şifre boş bırakılamaz.")
    private String password;

    @Column(name = "first_name")
    @Min(value = 2, message = "İsim alanı en az 2 harften oluşmalıdır.")
    @Max(value = 40, message = "İsim alanı en fazla 40 harften oluşmalıdır.")
    @NotNull(message = "İsim boş bırakılamaz.")
    @NotBlank(message = "İsim sadece boşluktan oluşamaz.")
    private String firstName;

    @Column(name = "last_name")
    @Min(value = 2, message = "Soyisim alanı en az 2 harften oluşmalıdır.")
    @Max(value = 40, message = "Soyisim alanı en fazla 40 harften oluşmalıdır.")
    @NotNull(message = "Soyisim boş bırakılamaz.")
    @NotBlank(message = "Soyisim sadece boşluktan oluşamaz.")
    private String lastName;

    @Column(name = "active")
    private boolean active;

    @OneToMany(mappedBy = "user")
    @JsonIgnore()
    private List<UserRole> userRoles;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private UserVerificationByMail userVerificationByMail;
}
