package com.sample.tcp.server.handler.method;

public class BasicCalculatorBusinessLogicService {

	public Double add(Double a, Double b) {
		return a+b;
	}
	
	public Double sub(Double a, Double b) {
		return a-b;
	}
	
	public Double divide(Double a, Double b) {
		return a/b;
	}
	
	public Double multiply(Double a, Double b) {
		return a*b;
	}
}
