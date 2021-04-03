package com.gustavosaron.stockquotemanager.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gustavosaron.stockquotemanager.service.StockExternalService;

@RestController
public class StockCacheController {
	
	@Autowired
	private StockExternalService service;
	
	@DeleteMapping("/stockcache")
	public void clearStockCache() {
		service.clearCachedStocks();
	}

}
