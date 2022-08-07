package com.techit.page.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.techit.page.to.InventoryItemTO;

public interface InventoryService {

	public InventoryItemTO createItem(InventoryItemTO item);
	
	public InventoryItemTO updateItem(InventoryItemTO item);
	
	public InventoryItemTO getSingleItem(Integer itemId);

	public Page<InventoryItemTO> findAllItems(Pageable pageable);

}
