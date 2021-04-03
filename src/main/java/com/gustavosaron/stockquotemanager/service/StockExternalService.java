package com.gustavosaron.stockquotemanager.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.gustavosaron.stockquotemanager.model.StockQuoteModel;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class StockExternalService {
	
	private final String STOCK_MANAGER_URL = "http://localhost:8080/stock";
	//private final String STOCK_MANAGER_URL = "http://stock-manager:8080/stock";

	private Map<String, StockQuoteModel> cachedStocks;
	private RestTemplate rt = new RestTemplate();
	private ObjectMapper mapper = new ObjectMapper(); 
	
	

	public boolean isValidStock(String stockId) {
		if(cachedStocks == null || cachedStocks.isEmpty()) 
			updateCachedStocks();
		
		if(cachedStocks.get(stockId) != null) 
			return true;
		
		return false;
	}

	private void updateCachedStocks() {
		log.info("Updating cached stocks");
		try {
			cachedStocks = new LinkedHashMap<String, StockQuoteModel>();
			
			String stocksString = rt.getForObject(STOCK_MANAGER_URL, String.class);
			
			CollectionType listType = 
				      mapper.getTypeFactory().constructCollectionType(ArrayList.class, StockQuoteModel.class);
			
			List<StockQuoteModel> stocks = mapper.readValue(stocksString, listType);
			
			log.info("stock-manager returned: " + stocks);
			
			stocks.forEach(stock -> cachedStocks.put(stock.getId(), stock));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void clearCachedStocks() {
		log.info("Cleaning cached stocks");
		
		if(cachedStocks != null)
			cachedStocks.clear();
	}
}
