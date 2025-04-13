package com.arg.inventory.services;

import com.arg.inventory.dto.WarehouseDto;
import com.arg.inventory.entities.Warehouse;

import java.util.List;

public interface WarehouseService {
    Warehouse saveWarehouse(WarehouseDto dto);
    List<Warehouse> getAllWarehouses();
    Warehouse getWarehouseById(Long id);
    Warehouse updateWarehouse(Long id, WarehouseDto warehouseDetails);
    void deleteWarehouse(Long id);
}
