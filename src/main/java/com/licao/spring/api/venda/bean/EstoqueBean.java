package com.licao.spring.api.venda.bean;

import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.licao.spring.Entidades.models.Venda;

@Component
public class EstoqueBean{

	public void atualizarQuantidade(Exchange exchange) {	
		
		String body = exchange.getIn().getBody(String.class);
		exchange.setProperty("vendaCadastrada", body); 
		
		Gson gson = new Gson();
		Venda venda = gson.fromJson(body, Venda.class);
		
			
		String itens = gson.toJson(venda.getItens());
		
		System.out.println("==========================================");
		System.out.println("==========================================");
		System.out.println("==========Itens: "+itens+"=================");
		System.out.println("==========================================");
		System.out.println("==========================================");
		
		exchange.getIn().setBody(itens);
		
	}
	
	public void atualizarStatus(Exchange exchange) {	
		
		String body = exchange.getProperty("vendaCadastrada", String.class);
		
		Gson gson = new Gson();
		Venda venda = gson.fromJson(body, Venda.class);
		
		
		String itens = gson.toJson(venda.getItens());
		
		System.out.println("===========ATUALIZAR STATUS ==============");
		System.out.println("==========================================");
		System.out.println("==========Itens: "+itens+"================");
		System.out.println("==========================================");
		System.out.println("==========================================");
		
		exchange.getIn().setBody(itens);
		
	}
}
