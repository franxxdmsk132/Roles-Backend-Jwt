package com.tutorial.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
@Data
public class ProductoDto {

    @NotBlank
    private String nombre;
    @NotBlank
    private String categoria;
    @NotBlank
    private String descripcion;
    @Min(0)
    private Integer talla;
    @NotBlank
    private Boolean estado;
    @Min(0)
    private Double precio;

    public ProductoDto() {
    }

    public ProductoDto(String nombre, String categoria, String descripcion, Integer talla, Boolean estado, Double precio) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.talla = talla;
        this.estado = estado;
        this.precio = precio;
    }
}
