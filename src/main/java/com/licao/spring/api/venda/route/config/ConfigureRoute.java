package com.licao.spring.api.venda.route.config;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ConfigureRoute extends RouteBuilder{

	@SuppressWarnings("rawtypes")
	@Bean
	public ServletRegistrationBean camelServletRegistrationBean() {

		@SuppressWarnings("unchecked")
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(
				new CamelHttpTransportServlet(), "/api/vendas/*");
		registrationBean.setName("CamelServlet");

		return registrationBean;
	}
	
	@Override
	public void configure() throws Exception {
		restConfiguration()
		.dataFormatProperty("prettyPrint", "true")
		.component("servlet")
		.bindingMode(RestBindingMode.off)
		.skipBindingOnErrorCode(false)
		.apiContextPath("/api-doc")
			.apiProperty("api.title", "API-Venda")
			.apiProperty("api.version", "1.0.0")
			.apiProperty("cors", "true");
		
	}

}
