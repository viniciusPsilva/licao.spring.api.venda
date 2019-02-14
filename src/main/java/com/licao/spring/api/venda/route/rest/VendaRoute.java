package com.licao.spring.api.venda.route.rest;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class VendaRoute extends RouteBuilder {
	
	private final String ROUTE_ID = "route_venda";
	private final String ROUTE_DESCRIPTON = "Cadastra uma venda no sistema, atualiza a quantidade do estoque de produtos e o status do estoque";
	private final String ROUTE_REST_URI = "/venda";
	
	private final String ROUTE_CRIAR_VENDA = "direct:route_criar_venda";
	
	@Override
	public void configure() throws Exception {
		
		rest(ROUTE_REST_URI)
			.id(ROUTE_ID)
			.description(ROUTE_DESCRIPTON)
			
			.post("/post")
				.to(ROUTE_CRIAR_VENDA);
		
	}

}
