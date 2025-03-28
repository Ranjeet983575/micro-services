package com.arg.inventory.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;


@Data
public class ProductDto {

    //@NotBlank(message = "Name can not be blank")
    private String name;
    private String sku;
    private String description;
    @NotNull(message = "Category Id can not be Null")
    //@NotBlank(message = "Category Id can not be Null")
    private Long categoryId;
}
