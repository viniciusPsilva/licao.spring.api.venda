package com.licao.spring.api.venda.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http4.HttpMethods;
import org.apache.camel.model.dataformat.JsonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.licao.spring.Entidades.models.Produto;

@Component
public class CriarProdutoRoute extends RouteBuilder {

	private final String  ROUTE_DEFINITION = "direct:cadatrar_produto";
	private final String URI_ESTOQUE = "http4://localhost:8082/produto?bridgeEndpoint=true";
	
	@Override
	public void configure() throws Exception {
		
		JsonDataFormat json = new JsonDataFormat(JsonLibrary.Jackson);
		  
		  // Set the target class that I want to convert the JSON to
		  json.setUnmarshalType(Produto.class);
		
		from(ROUTE_DEFINITION)
			.id(ROUTE_DEFINITION)
			
			.setHeader(Exchange.CONTENT_TYPE, constant(MediaType.APPLICATION_JSON))
			.setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.POST))
			.log("RECEBENDO A REQUISIÇÃO")
			
			.doTry()
				.marshal(json)
				.log("${body}")
				.to(URI_ESTOQUE)
				.unmarshal(json)
			.doCatch(Exception.class)
				.setBody(simple("${exception}"))
				.setHeader(Exchange.HTTP_RESPONSE_CODE, constant("500"))
			.endDoTry();		
	}

}
