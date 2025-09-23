package com.fsd1.group_project.Service;

import com.fsd1.group_project.Entity.Equipment;
import java.util.List;

public interface EquipmentService {
    List<Equipment> getAllEquipment();
    Equipment getEquipmentById(Long id);
    Equipment createEquipment(Equipment equipment);
    Equipment updateEquipment(Long id, Equipment equipment);
    void deleteEquipment(Long id);
    // 🔍 New Method for Search
    List<Equipment> searchEquipmentByName(String name);
    // 🔍 New Method for Search by Event Name
    List<Equipment> searchEquipmentByEventName(String eventName);
}
