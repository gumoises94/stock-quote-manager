package com.gustavosaron.stockquotemanager.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gustavosaron.stockquotemanager.model.StockQuoteModel;
import com.gustavosaron.stockquotemanager.service.StockService;

@RestController
public class StockQuoteController {
	
	@Autowired
	private StockService service;
	
	@GetMapping("/stock-quote")
	public List<StockQuoteModel> findAll() {
		return service.findAll();
	}
	

	@GetMapping("/stock-quote/{id}")
	public StockQuoteModel findById(@PathVariable String id) {
		return service.findByStockId(id);

	}

	@PostMapping("/stock-quote")
	public StockQuoteModel saveStockQuote(@RequestBody StockQuoteModel stockQuote) {
		return service.save(stockQuote);
	}

}
