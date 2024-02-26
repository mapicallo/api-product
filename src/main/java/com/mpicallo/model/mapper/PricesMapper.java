package com.mpicallo.model.mapper;

import com.mpicallo.model.dto.PricesRequestDto;
import com.mpicallo.model.dto.PricesResponseDto;
import com.mpicallo.model.entity.PricesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PricesMapper {

	PricesResponseDto entityToResponse(PricesEntity entity);

	PricesEntity requestToEntity(PricesRequestDto request);

}