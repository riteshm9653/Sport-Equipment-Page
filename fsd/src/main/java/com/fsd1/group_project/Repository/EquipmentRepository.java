package com.fsd1.group_project.Repository;

import com.fsd1.group_project.Entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    // Search by equipment name (case-insensitive, partial match)
    List<Equipment> findByNameContainingIgnoreCase(String name);

    // Search by event name (case-insensitive, partial match)
    List<Equipment> findByEventNameContainingIgnoreCase(String eventName);
}