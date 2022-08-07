package com.techit.page.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

	@GetMapping("/all")
	public ResponseEntity<String> showMsg() {
		return ResponseEntity.ok("Response From Product Controller");
	}
}
