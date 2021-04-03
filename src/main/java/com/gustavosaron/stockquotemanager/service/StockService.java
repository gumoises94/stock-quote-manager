package com.gustavosaron.stockquotemanager.service;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gustavosaron.stockquotemanager.entity.QuoteEntity;
import com.gustavosaron.stockquotemanager.entity.StockEntity;
import com.gustavosaron.stockquotemanager.exception.InvalidFormatException;
import com.gustavosaron.stockquotemanager.exception.ResourceNotFoundException;
import com.gustavosaron.stockquotemanager.model.StockQuoteModel;
import com.gustavosaron.stockquotemanager.repository.QuoteRepository;
import com.gustavosaron.stockquotemanager.repository.StockRepository;


@Service
public class StockService {

	@Autowired
	private StockRepository stockRepository;
	@Autowired
	private QuoteRepository quoteRepository;

	public List<StockQuoteModel> findAll() {
		List<StockQuoteModel> stocks = new ArrayList<StockQuoteModel>();
		
		stockRepository.findAll().forEach(stock -> stocks.add(toModel(stock)));

		return stocks;
	}

	public StockQuoteModel findByStockId(String id) {
		StockEntity entity = stockRepository.findByStockId(id)
				.orElseThrow(() 
						-> new ResourceNotFoundException("Could not find resource with id: " + id));

		return toModel(entity);
	}

	@Transactional
	public StockQuoteModel save(StockQuoteModel stockQuote) {
		StockEntity entity = stockRepository.findByStockId(stockQuote.getId())
				.orElse(null);

		if(entity != null) {
			if(entity.getQuotes() == null)
				entity.setQuotes(new ArrayList<QuoteEntity>());
			
			List<QuoteEntity> newQuotes = toEntity(stockQuote).getQuotes();
			
			for(QuoteEntity newQuote : newQuotes) {
				QuoteEntity quote = quoteRepository.findByStockIdAndQuoteDate(entity.getId(), newQuote.getQuoteDate())
						.orElse(null);
				
				if(quote != null) { 
					quote.setQuotation(newQuote.getQuotation());
				}
				else {
					newQuote.setStock(entity);
					entity.getQuotes().add(newQuote);
				}
			}
		}
		else {
			entity = toEntity(stockQuote);
		}

		return toModel(stockRepository.save(entity));
	}

	public StockQuoteModel toModel(StockEntity entity) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		DecimalFormat df = new DecimalFormat("###.##");

		StockQuoteModel model = StockQuoteModel.builder()
				.id(entity.getStockId())
				.build();
		List<QuoteEntity> quotes = entity.getQuotes();

		if(quotes != null) {
			Map<String, String> quotesMap = new LinkedHashMap<String, String>();

			for(QuoteEntity quote : quotes) {
				quotesMap.put(
						sdf.format(quote.getQuoteDate()), df.format(quote.getQuotation()));
			}
			
			model.setQuotes(quotesMap);
		}

		return model;
	}

	public StockEntity toEntity(StockQuoteModel model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		StockEntity entity = StockEntity.builder()
				.stockId(model.getId())
				.build();

		Map<String, String> quotes = model.getQuotes();

		if(quotes != null) {
			List<QuoteEntity> quotesEntity = new ArrayList<QuoteEntity>();

			for(String quoteDate : quotes.keySet()) {
				QuoteEntity quote = new QuoteEntity();
				quote.setStock(entity);

				try {
					quote.setQuoteDate(sdf.parse(quoteDate));
					quote.setQuotation(Double.parseDouble(quotes.get(quoteDate)));
				}catch (ParseException e) {
					throw new InvalidFormatException("Invalid date format: " + quoteDate);
				}catch (NumberFormatException e) {
					throw new InvalidFormatException("Invalid number format: " + quotes.get(quoteDate));
				}

				quotesEntity.add(quote);
			}

			entity.setQuotes(quotesEntity);
		}

		return entity;
	}

}
