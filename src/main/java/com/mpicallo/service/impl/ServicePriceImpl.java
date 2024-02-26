package com.mpicallo.service.impl;

import com.mpicallo.model.dto.PricesResponseDto;
import com.mpicallo.model.entity.PricesEntity;
import com.mpicallo.model.mapper.PricesMapper;
import com.mpicallo.repository.PricesRepository;
import com.mpicallo.service.PriceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServicePriceImpl implements PriceService {

    @Autowired
    private PricesRepository priceRepository;
    @Autowired
    private final PricesMapper mapper;


    @Override
    public List<PricesResponseDto> findAll() {
        var list = priceRepository.findAll();
        log.info("LISTADO ALL: "+ new ArrayList<>(list).toString());
        log.info("prices geted");

        return list.stream().map(mapper::entityToResponse).collect(Collectors.toList());
    }


    @Override
    public Optional<PricesResponseDto> findPriceByDateAndProductAndBrand(Integer brandId, Integer productId, LocalDateTime applicationDate) {
        log.info("Getting price for brandId={}, productId={}, and applicationDate={}", brandId, productId, applicationDate);
        Optional<PricesEntity> priceEntity = priceRepository.findTopByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(brandId, productId, applicationDate, applicationDate);
        return priceEntity.map(mapper::entityToResponse);
    }


}
