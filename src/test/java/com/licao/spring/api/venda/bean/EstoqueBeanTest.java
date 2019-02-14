package com.licao.spring.api.venda.bean;

import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.DefaultExchange;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class EstoqueBeanTest {

	@InjectMocks
	private EstoqueBean estoqueBean;
	
	private DefaultCamelContext context;
	
	private static DefaultExchange exchange;
	
	private String jsonVenda = "{\"itens\": [{\"produto\": {\"id\": 1,\"name\": \"Galaxy j7Pro\",\"descricao\": \"64gb 3gb RAM\",\"valor\": 1200,\"disponivel\": true},\"quantidade\": 1},{\"produto\": {\"id\": 2,\"name\": \"Macbook Pro\",\"descricao\": \"128 ssd 8gb RAM\",\"valor\": 1200,\"disponivel\": true},\"quantidade\": 1}],\"valor\": 0}";
	
	@Test
	public void before() {
		this.context = new DefaultCamelContext();
		exchange = new DefaultExchange(context);
		exchange.getIn().setBody(jsonVenda);
	}
	
	@Test
	public void atualizarQuantidade() {
		estoqueBean.atualizarQuantidade(exchange);
	}
	
	@Test
	public void atualizarStatus() {
		estoqueBean.atualizarStatus(exchange);
	}
	
}
