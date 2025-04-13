package com.arg.inventory.controllers;

import com.arg.inventory.constants.AppConstants;
import com.arg.inventory.dto.ApiResponse;
import com.arg.inventory.dto.WarehouseDto;
import com.arg.inventory.entities.Warehouse;
import com.arg.inventory.services.WarehouseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
@AllArgsConstructor
public class WarehouseController {

    private final WarehouseService warehouseService;

    @GetMapping
    public ResponseEntity<ApiResponse<Object>> getAllWarehouses() {
        List<Warehouse> allWarehouses = warehouseService.getAllWarehouses();
        ApiResponse<Object> data = ApiResponse.builder()
                .message(AppConstants.SUCCESS).status("200").data(allWarehouses).build();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> getWarehouseById(@PathVariable Long id) {
        Warehouse warehouse = warehouseService.getWarehouseById(id);
        ApiResponse<Object> data = ApiResponse.builder()
                .message(AppConstants.SUCCESS).status("200").data(warehouse).build();
        return ResponseEntity.ok(data);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Object>> createWarehouse(@Valid @RequestBody WarehouseDto dto) {
        Warehouse newWarehouse = warehouseService.saveWarehouse(dto);
        ApiResponse<Object> data = ApiResponse.builder()
                .message(AppConstants.SUCCESS).status("200").data(newWarehouse).build();
        return ResponseEntity.ok(data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> updateWarehouse(@PathVariable Long id, @RequestBody WarehouseDto dto) {
        Warehouse updatedWarehouse = warehouseService.updateWarehouse(id, dto);
        ApiResponse<Object> data = ApiResponse.builder()
                .message(AppConstants.SUCCESS).status("200").data(updatedWarehouse).build();
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteWarehouse(@PathVariable Long id) {
        warehouseService.deleteWarehouse(id);
        ApiResponse<Object> data = ApiResponse.builder()
                .message(AppConstants.SUCCESS).status("200").data(null).build();
        return ResponseEntity.ok(data);
    }
}
