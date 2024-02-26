package com.mpicallo.repository;

import com.mpicallo.model.entity.PricesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PricesRepository extends JpaRepository<PricesEntity, Long> {

    List<PricesEntity> findAll();
    Optional<PricesEntity> findTopByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
            Integer brandId, Integer productId, LocalDateTime startDate, LocalDateTime endDate);

}
