package com.gustavosaron.stockquotemanager.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustavosaron.stockquotemanager.entity.QuoteEntity;


public interface QuoteRepository extends JpaRepository<QuoteEntity, Long> {
	
	public Optional<QuoteEntity> findByStockIdAndQuoteDate(Long stockId, Date quoteDate);

}
