package com.gustavosaron.stockquotemanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustavosaron.stockquotemanager.entity.StockEntity;


public interface StockRepository extends JpaRepository<StockEntity, Long> {
	
	public Optional<StockEntity> findByStockId(String stockId);

}
