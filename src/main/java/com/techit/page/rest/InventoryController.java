package com.techit.page.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techit.page.service.InventoryService;
import com.techit.page.to.InventoryItemTO;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

	@Autowired
	private InventoryService invService;

	@PostMapping
	public ResponseEntity<InventoryItemTO> createItem(@RequestBody InventoryItemTO itemTO) {
		return new ResponseEntity<InventoryItemTO>(invService.createItem(itemTO), HttpStatus.CREATED);
	}

	@PutMapping("/{itemId}")
	public ResponseEntity<InventoryItemTO> updateItem(@PathVariable("itemId") Integer itemId,
			@RequestBody InventoryItemTO itemTO) {
		itemTO.setItemId(itemId);
		return new ResponseEntity<InventoryItemTO>(invService.updateItem(itemTO), HttpStatus.OK);
	}

	@GetMapping("/{itemId}")
	public ResponseEntity<InventoryItemTO> getSingleItem(@PathVariable("itemId") Integer itemId) {
		return new ResponseEntity<InventoryItemTO>(invService.getSingleItem(itemId), HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<Page<InventoryItemTO>> findAllItems(
			@RequestParam(name = "pageNumber", required = true) int pageNumber,
			@RequestParam(name = "pageSize", required = true) int pageSize,
			@RequestParam(name = "sortParam", required = true) String[] sortParam) {
		List<Order> orders = new ArrayList<>();
		for (String element : sortParam) {
			String[] param = element.split(":");
			orders.add(new Order(Direction.valueOf(param[1]), param[0]));
		}
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(orders));
		Page<InventoryItemTO> page = invService.findAllItems(pageable);
		return new ResponseEntity<Page<InventoryItemTO>>(page, HttpStatus.OK);
	}

}
