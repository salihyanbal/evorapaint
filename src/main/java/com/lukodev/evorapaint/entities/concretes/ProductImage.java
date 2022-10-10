package com.lukodev.evorapaint.entities.concretes;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_images")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "date")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm")
    public LocalDateTime date;

    @OneToOne()
    @JoinColumn(name = "product_id", unique = true)
    private Product product;
}
