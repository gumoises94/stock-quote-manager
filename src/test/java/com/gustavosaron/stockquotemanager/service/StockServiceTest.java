package com.gustavosaron.stockquotemanager.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gustavosaron.stockquotemanager.entity.StockEntity;
import com.gustavosaron.stockquotemanager.exception.ResourceNotFoundException;
import com.gustavosaron.stockquotemanager.model.StockQuoteModel;
import com.gustavosaron.stockquotemanager.repository.QuoteRepository;
import com.gustavosaron.stockquotemanager.repository.StockRepository;
import com.gustavosaron.stockquotemanager.util.TestUtil;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
@SpringBootTest
public class StockServiceTest {

	@Mock
	private StockRepository stockRepository;
	@Mock
	private QuoteRepository quoteRepository;
	@InjectMocks
	private StockService service;
	
	private StockService spyService;
	
	private StockEntity entity;
	private StockQuoteModel model;

	@BeforeEach
	public void setUp() throws ParseException {
		entity = TestUtil.buildStockEntity();
		model = TestUtil.buildStockQuoteModel();
		
		spyService = spy(service);
	}

	@Test
	public void shouldSaveStock_WhenSavingANewStockTest() {
		when(stockRepository.findByStockId(model.getId())).thenReturn(Optional.empty());
		when(stockRepository.save(entity)).thenReturn(entity);
		doReturn(entity).when(spyService).toEntity(model);
		doReturn(model).when(spyService).toModel(entity);

		assertEquals(spyService.save(model), model);

		verify(stockRepository).findByStockId(model.getId());
		verify(stockRepository).save(entity);
		verify(spyService).toEntity(model);
		verify(spyService).toModel(entity);
		verifyNoMoreInteractions(stockRepository);
	}
	
	@Test
	public void shouldReturnAllStocksTest() {
		List<StockEntity> entitys = new ArrayList<StockEntity>();
		List<StockQuoteModel> models = new ArrayList<StockQuoteModel>();
		entitys.add(entity);
		models.add(model);
		when(stockRepository.findAll()).thenReturn(entitys);
		doReturn(model).when(spyService).toModel(entity);

		assertEquals(spyService.findAll(), models);

		verify(stockRepository).findAll();
		verify(spyService).toModel(entity);
		verifyNoMoreInteractions(stockRepository);
	}
	
	@Test
	public void shouldReturnStock_WhenFindingStockByStockIdTest() {
		when(stockRepository.findByStockId(model.getId())).thenReturn(Optional.of(entity));
		doReturn(model).when(spyService).toModel(entity);

		assertEquals(spyService.findByStockId(model.getId()), model);

		verify(stockRepository).findByStockId(model.getId());
		verify(spyService).toModel(entity);
		verifyNoMoreInteractions(stockRepository);
	}

	@Test
	public void shouldThrowError_whenStockDoesNotExistTest() {
		when(stockRepository.findByStockId(model.getId())).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> service.findByStockId(model.getId()));
	}

}

