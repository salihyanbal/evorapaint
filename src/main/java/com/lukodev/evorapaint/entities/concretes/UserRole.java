package com.lukodev.evorapaint.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_roles",uniqueConstraints = {@UniqueConstraint(name = "junction_unique",columnNames = { "role_id", "user_id" })})
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne()
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

}
