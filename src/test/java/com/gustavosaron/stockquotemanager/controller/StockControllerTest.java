package com.gustavosaron.stockquotemanager.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.gustavosaron.stockquotemanager.model.StockQuoteModel;
import com.gustavosaron.stockquotemanager.service.StockService;
import com.gustavosaron.stockquotemanager.util.TestUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class StockControllerTest {

	@MockBean
	private  StockService service;

	@Autowired
	private MockMvc mockMvc;
	private StockQuoteModel model;

	@BeforeEach
	public void setUp() {
		model = TestUtil.buildStockQuoteModel();
	}

	@Test
	public void shouldReturAllStockQuotesTest() throws Exception {
		List<StockQuoteModel> models = new ArrayList<StockQuoteModel>();
		models.add(model);
		
		when(service.findAll()).thenReturn(models);

		 mockMvc.perform(get("/stock-quote"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$[0].id").value(model.getId()));

		verify(service).findAll();
		verifyNoMoreInteractions(service);
	}
}