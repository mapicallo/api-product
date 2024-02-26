package com.mpicallo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "prices")
public class PricesEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer brandId;

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	private Integer priceList;

	private Integer productId;

	private Integer priority;

	private BigDecimal price;

	private String curr;

}
