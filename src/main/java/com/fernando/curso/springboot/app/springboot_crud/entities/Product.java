package com.fernando.curso.springboot.app.springboot_crud.entities;

import com.fernando.curso.springboot.app.springboot_crud.validation.IsExistDb;
import com.fernando.curso.springboot.app.springboot_crud.validation.IsRequired;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @IsRequired
    @IsExistDb
    private String sku;
   
    //@NotEmpty(message = "{NotEmpty.product.name}")
    @IsRequired(message = "{IsRequired.product.name}")
    @Size(min = 3, max = 20)
    private String name;
    
    @NotNull(message = "{NotNull.product.price}")
    @Min(value = 500, message = "{Min.product.price}")
    private Integer price;

    //@NotBlank(message = "{NotBlank.product.description}")
    @IsRequired
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    
}
