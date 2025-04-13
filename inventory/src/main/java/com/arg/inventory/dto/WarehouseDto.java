package com.arg.inventory.dto;

import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WarehouseDto {

    @NotNull(message = "Name can not be Null")
    @NotBlank(message = "Category Id can not be Blank")
    private String name;
    private String location;
}
