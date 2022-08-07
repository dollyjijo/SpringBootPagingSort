package com.techit.page.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techit.page.entity.InventoryItem;
import com.techit.page.repository.InventoryRepsitory;
import com.techit.page.to.InventoryItemTO;

@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private InventoryRepsitory invRepository;

	@Override
	public InventoryItemTO createItem(InventoryItemTO item) {
		InventoryItem entity = new InventoryItem();
		BeanUtils.copyProperties(item, entity);
		BeanUtils.copyProperties(invRepository.save(entity), item);
		return item;
	}

	@Override
	public Page<InventoryItemTO> findAllItems(Pageable pageable) {
		Page<InventoryItem> pageItems = invRepository.findAll(pageable);
		return pageItems.map(this::convertTO);
	}

	@Override
	public InventoryItemTO updateItem(InventoryItemTO item) {
		InventoryItemTO updtItemto = null;
		Optional<InventoryItem> optEntity = invRepository.findById(item.getItemId());
		if (null != optEntity && optEntity.isPresent()) {
			InventoryItem entity = optEntity.get();
			updtItemto = new InventoryItemTO();
			InventoryItem itemEnty = optEntity.get();
			itemEnty.setItemName(entity.getItemName());
			itemEnty.setItemCost(entity.getItemCost());
			itemEnty.setItemQuantity(entity.getItemQuantity());
			updtItemto = new InventoryItemTO();
			BeanUtils.copyProperties(itemEnty, updtItemto);
		}
		return updtItemto;
	}

	@Override
	public InventoryItemTO getSingleItem(Integer itemId) {
		InventoryItemTO itemTO = null;
		Optional<InventoryItem> optEntity = invRepository.findById(itemId);
		if (null != optEntity && optEntity.isPresent()) {
			itemTO = new InventoryItemTO();
			BeanUtils.copyProperties(optEntity.get(), itemTO);
		}
		return itemTO;
	}

	private InventoryItemTO convertTO(InventoryItem singleItem) {
		InventoryItemTO itm = new InventoryItemTO();
		BeanUtils.copyProperties(singleItem, itm);
		return itm;
	}

}
