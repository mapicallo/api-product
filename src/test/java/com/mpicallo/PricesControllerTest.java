package com.mpicallo;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import com.mpicallo.controller.PricesController;
import com.mpicallo.model.dto.PricesResponseDto;
import com.mpicallo.service.PriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PricesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PriceService priceService;

    @InjectMocks
    private PricesController pricesController;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testFindAllPrices() throws Exception {
        // Given
        List<PricesResponseDto> prices = new ArrayList<>();

        PricesResponseDto price1 = PricesResponseDto.builder()
                .productId(35455)
                .brandId(1)
                .priceList(1)
                .price(BigDecimal.valueOf(50.25))
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now()).build();
        PricesResponseDto price2 = PricesResponseDto.builder()
                .productId(35455)
                .brandId(1)
                .priceList(2)
                .price(BigDecimal.valueOf(60.25))
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now()).build();

        prices.add(price1);
        prices.add(price2);


        //When , Then
        mockMvc.perform(get("/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].product_id").value(35455))
                .andExpect(jsonPath("$[1].brand_id").value(1));
    }

    @Test
    public void testGetPriceByDate() throws Exception {
        // Given
        Integer brandId = 1;
        Integer productId = 35455;
        LocalDateTime applicationDate = LocalDateTime.of(2020,7,10,10,0,0);

        PricesResponseDto price = PricesResponseDto.builder()
                .brandId(1)
                .priceList(35455)
                .priceList(4)
                .price(BigDecimal.valueOf(38.95))
                .startDate(LocalDateTime.of(2020,6,15,16,0,0))
                .endDate(LocalDateTime.of(2020,12,31,23,59,59)).build();



        //When , Then
        mockMvc.perform(get("/pricing-by-date")
                        .param("brandId", brandId.toString())
                        .param("productId", productId.toString())
                        .param("applicationDate", applicationDate.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.brand_id").value(1));
    }
}

