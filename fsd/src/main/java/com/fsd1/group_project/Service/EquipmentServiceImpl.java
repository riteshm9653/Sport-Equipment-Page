package com.fsd1.group_project.Service;

import com.fsd1.group_project.Entity.Equipment;
import com.fsd1.group_project.Repository.EquipmentRepository;
import com.fsd1.group_project.Utils.EquipmentNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;

    @Autowired
    public EquipmentServiceImpl(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }

    @Override
    public Equipment getEquipmentById(Long id) {
        return equipmentRepository.findById(id)
                .orElseThrow(() -> new EquipmentNotFoundException("Equipment not found with id: " + id));
    }

    @Override
    public Equipment createEquipment(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    @Override
    public Equipment updateEquipment(Long id, Equipment equipment) {
        Equipment existingEquipment = getEquipmentById(id);

        // Update fields
        existingEquipment.setName(equipment.getName());
        existingEquipment.setEventName(equipment.getEventName());
        existingEquipment.setDescription(equipment.getDescription());
        existingEquipment.setCategory(equipment.getCategory());
        existingEquipment.setQuantity(equipment.getQuantity());
        existingEquipment.setCondition(equipment.getCondition());
        existingEquipment.setCostPerUnit(equipment.getCostPerUnit());
        existingEquipment.setLocation(equipment.getLocation());
        existingEquipment.setStatus(equipment.getStatus());
        existingEquipment.setLastMaintenance(equipment.getLastMaintenance());
        existingEquipment.setNextMaintenance(equipment.getNextMaintenance());

        return equipmentRepository.save(existingEquipment);
    }




    // 🔍 Search by Name
    @Override
    public List<Equipment> searchEquipmentByName(String name) {
        return equipmentRepository.findByNameContainingIgnoreCase(name);
    }

    // 🔍 Search by Event Name
    @Override
    public List<Equipment> searchEquipmentByEventName(String eventName) {
        return equipmentRepository.findByEventNameContainingIgnoreCase(eventName);
    }

    @Override
    public void deleteEquipment(Long id) {
        Equipment equipment = getEquipmentById(id);
        equipmentRepository.delete(equipment);
    }
}
