package com.mpicallo.service;

import com.mpicallo.model.dto.PricesResponseDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PriceService {

    List<PricesResponseDto> findAll();

    Optional<PricesResponseDto> findPriceByDateAndProductAndBrand(Integer brandId, Integer productId, LocalDateTime applicationDate);

}
