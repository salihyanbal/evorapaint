package com.lukodev.evorapaint.entities.concretes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_verifications_by_mail")
public class UserVerificationByMail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "verification_code")
    @NotNull
    private int verificationCode;

    @Column(name = "verified")
    private boolean verified;

    @Column(name = "expiration")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm")
    private LocalDateTime expiration;

    @OneToOne()
    @JoinColumn(name = "user_id", unique = true)
    private User user;

}
