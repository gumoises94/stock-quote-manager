package com.gustavosaron.stockquotemanager.model;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorModel {
	
	private int status;
	private String error;
	private String message;
}
