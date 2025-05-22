package com.arg.user.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cart {
    private int productId;
    private int quantity;
}
