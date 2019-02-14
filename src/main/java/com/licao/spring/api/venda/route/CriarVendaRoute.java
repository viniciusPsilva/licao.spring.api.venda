package com.licao.spring.api.venda.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http4.HttpMethods;
import org.apache.camel.model.dataformat.JsonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.licao.spring.Entidades.models.Venda;
import com.licao.spring.api.venda.bean.EstoqueBean;

@Component
public class CriarVendaRoute extends RouteBuilder{

	private static final String CADASTRAR_VENDA = "http4://localhost:8080/venda?bridgeEndpoint=true";
	private static final String ATUALIZAR_ESTOQUE_QUATIDADE_PRODUTO = "http4://localhost:8081/estoque/atualizar/quantidade?bridgeEndpoint=true";
	private static final String ATUALIZAR_ESTOQUE_STATUS = "http4://localhost:8081/estoque/atualizar/status?bridgeEndpoint=true";
	private static final String ROUTE_ID = "direct:route_criar_venda";
	
	@Override
	public void configure() throws Exception {
		
		JsonDataFormat json = new JsonDataFormat(JsonLibrary.Jackson);
		  
		  // Set the target class that I want to convert the JSON to
		  json.setUnmarshalType(Venda.class);
		
		from(ROUTE_ID)
			.id(ROUTE_ID)
			.setHeader(Exchange.CONTENT_TYPE, constant(MediaType.APPLICATION_JSON))
			.log("RECEBENDO A REQUISIÇÃO")
		
		.doTry()
			.marshal(json)
			.log("${body}")
			.to("bean-validator:vendaValidator")
			.to(CADASTRAR_VENDA)
			.log("PASSOU PELO SERVIÇO DE VENDA")
			//TODO chamar o serviço de estoque para atualizar quantidade
			.bean(EstoqueBean.class, "atualizarQuantidade")
			.setHeader(Exchange.HTTP_METHOD, HttpMethods.PATCH)
			.to(ATUALIZAR_ESTOQUE_QUATIDADE_PRODUTO)
			.setHeader(Exchange.CONTENT_TYPE, constant(MediaType.APPLICATION_JSON))
			.bean(EstoqueBean.class, "atualizarStatus")
			.to(ATUALIZAR_ESTOQUE_STATUS)
			//TODO chamar o serviço de estoque para atualizar status	
			.setBody(simple("${property.vendaCadastrada}"))
			.unmarshal(json)
			.setHeader(Exchange.HTTP_RESPONSE_CODE, constant("200"))
		.doCatch(Exception.class)
			.setBody(simple("${exception}"))
			.setHeader(Exchange.HTTP_RESPONSE_CODE, constant("500"))
		.endDoTry();
	}
}
