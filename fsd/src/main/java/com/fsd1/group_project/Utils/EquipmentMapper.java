package com.fsd1.group_project.Utils;

import com.fsd1.group_project.Entity.Equipment;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EquipmentMapper {

    public EquipmentDTO toDTO(Equipment equipment) {
        if (equipment == null) {
            return null;
        }

        return new EquipmentDTO(
            equipment.getId(),
            equipment.getName(),
            equipment.getEventName(),
            equipment.getDescription(),
            equipment.getCategory(),
            equipment.getQuantity(),
            equipment.getCondition(),
            equipment.getCostPerUnit(),
            equipment.getLocation(),
            equipment.getStatus(),
            equipment.getLastMaintenance(),
            equipment.getNextMaintenance()
        );
    }

    public Equipment toEntity(EquipmentDTO dto) {
        if (dto == null) {
            return null;
        }

        return new Equipment(
            dto.getId(),
            dto.getName(),
            dto.getEventName(),
            dto.getDescription(),
            dto.getCategory(),
            dto.getQuantity(),
            dto.getCondition(),
            dto.getCostPerUnit(),
            dto.getLocation(),
            dto.getStatus(),
            dto.getLastMaintenance(),
            dto.getNextMaintenance()
        );
    }

    public List<EquipmentDTO> toDTOList(List<Equipment> equipmentList) {
        return equipmentList.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    public List<Equipment> toEntityList(List<EquipmentDTO> dtoList) {
        return dtoList.stream()
            .map(this::toEntity)
            .collect(Collectors.toList());
    }
}