package com.gustavosaron.stockquotemanager.model;


import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockQuoteModel {
	
	private String id;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String description;
	
	private Map<String, String> quotes;
}
