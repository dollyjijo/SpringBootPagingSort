package com.techit.page.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techit.page.entity.InventoryItem;

public interface InventoryRepsitory extends JpaRepository<InventoryItem, Integer>{

}
