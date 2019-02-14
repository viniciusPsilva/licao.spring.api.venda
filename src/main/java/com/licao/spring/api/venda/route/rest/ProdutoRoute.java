package com.licao.spring.api.venda.route.rest;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ProdutoRoute extends RouteBuilder {

	private final String URI_REST_ESTOQUE = "/produto";
	private final String DESCRIPTION = "Reliza chamada para o serviço de produto";
	
	@Override
	public void configure() throws Exception {
		
		rest(URI_REST_ESTOQUE)
			.description(DESCRIPTION)
			
			.post("/post")
				.to("direct:cadatrar_produto");
		
	} 

}
