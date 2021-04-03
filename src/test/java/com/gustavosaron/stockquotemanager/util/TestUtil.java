package com.gustavosaron.stockquotemanager.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.gustavosaron.stockquotemanager.entity.QuoteEntity;
import com.gustavosaron.stockquotemanager.entity.StockEntity;
import com.gustavosaron.stockquotemanager.model.StockQuoteModel;

public class TestUtil {

	public static StockQuoteModel buildStockQuoteModel() {
		Map<String, String> quotes = new LinkedHashMap<String, String>();
		
		quotes.put("2019-01-01", "10");
		
		return StockQuoteModel.builder()
			.id("petr3")
			.description("test")
			.quotes(quotes)
			.build();
	}

	
	public static StockEntity buildStockEntity() throws ParseException {
		List<QuoteEntity> quotes = new ArrayList<QuoteEntity>();
		QuoteEntity quote = buildQuoteEntity();
		
		StockEntity stock =  StockEntity.builder()
				.id(new Long(1))
				.stockId("petr3")
				.build();
		
		quote.setStock(stock);
		quotes.add(quote);
		stock.setQuotes(quotes);
		
		return stock;
	}
	
	public static QuoteEntity buildQuoteEntity() throws ParseException {
		return QuoteEntity.builder()
				.id(new Long(1))
				.quotation(10.0)
				.quoteDate(new SimpleDateFormat("yyy-MM-dd").parse("2019-01-01"))
				.build();
	}
}
