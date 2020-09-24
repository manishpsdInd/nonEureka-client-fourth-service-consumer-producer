package com.spring.camel.integration.processor;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.spring.camel.integration.common.RestServiceCaller;
import com.spring.camel.integration.constant.SpringCamelConstant;
import com.spring.camel.integration.dto.RequestDto;
import com.spring.camel.integration.dto.ServiceResponse;

public class ThirdServiceCallProcessor {

	private static final Logger log = LoggerFactory.getLogger(ThirdServiceCallProcessor.class);

	@Autowired
	RestServiceCaller restServiceCaller;

	public void process(Exchange exchange) throws Exception {

		log.info("ThirdServiceCallProcessor process() enterying");

		ServiceResponse body = exchange.getIn().getBody(ServiceResponse.class);

		RequestDto serviceRequest = new RequestDto();
		serviceRequest.setKey(body.getRespCode());
		serviceRequest.setValue(body.getRespCode() + "-" + body.getRespCode());

		ServiceResponse serviceResponse = restServiceCaller.callSCIntIII(serviceRequest);

		exchange.getIn().setHeader(SpringCamelConstant.HEADER_ROUTE_THIRD_RESPONSE, serviceResponse);
		exchange.getIn().setBody(serviceResponse);

		log.info("ThirdServiceCallProcessor process() exiting");

	}
}
