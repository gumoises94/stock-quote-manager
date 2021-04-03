package com.gustavosaron.stockquotemanager.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "stock")
public class StockEntity {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "stock_id", nullable = false)
	private String stockId;
	
	@OneToMany(mappedBy = "stock")
	@Cascade(CascadeType.ALL)
	private List<QuoteEntity> quotes;
}
