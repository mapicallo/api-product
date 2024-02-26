package com.mpicallo.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.mpicallo.model.dto.*;
import com.mpicallo.service.PriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "product", description = "products ecommerce")
@RestController
public class PricesController {

	PriceService service;

	public PricesController(PriceService service2) {
		this.service = service2;
	}


	@GetMapping("/all")
	@Operation(
			summary = "get all prices product",
			description = "get all prices product",
			tags = { "product" })
	public ResponseEntity<List<PricesResponseDto>> findAll(){
		var result = service.findAll();
		if(result.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(result);
	}


	@GetMapping("/pricing-by-date")
	@Operation(
			summary = "get price by date, brandId, and productId",
			description = "get price by date, brandId, and productId",
			tags = {"product"})
	public ResponseEntity<PricesResponseDto> getPriceByDate(
			@RequestParam Integer brandId,
			@RequestParam Integer productId,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate) {
		Optional<PricesResponseDto> result = service.findPriceByDateAndProductAndBrand(brandId, productId, applicationDate);
		return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
	}


}
