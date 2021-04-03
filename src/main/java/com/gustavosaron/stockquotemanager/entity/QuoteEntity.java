package com.gustavosaron.stockquotemanager.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "quote")
public class QuoteEntity {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "stock_id", nullable = false)
	private StockEntity stock;
	
	@Column(name = "quote_date", nullable = false)
	private Date quoteDate;
	
	@Column(nullable = false)
	private Double quotation;
	
	
}
