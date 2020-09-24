package com.spring.camel.integration.controller;

import org.apache.camel.CamelContext;
import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.camel.integration.constant.SpringCamelConstant;
import com.spring.camel.integration.dto.RequestDto;
import com.spring.camel.integration.dto.ResponseDto;
import com.spring.camel.integration.dto.ServiceResponse;

@RestController
public class CamelController {
	
	private static final Logger log = LoggerFactory.getLogger(CamelController.class);
	
	@Autowired
	CamelContext camelContext;
	
	@Autowired
	ProducerTemplate producerTemplate;
	
	@Value("${spring.application.name}") 
	String appName;

	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = {"application/JSON", "application/XML"}, produces = "application/JSON")
	public @ResponseBody ResponseDto startCamel(@RequestBody RequestDto request) {
		
		ResponseDto response = (ResponseDto) producerTemplate.sendBody(SpringCamelConstant.SPRING_CAMEL_ROUTER, ExchangePattern.InOut, request);

		log.info("CamelController End - " + response);
		return response;
	}
	
	@RequestMapping(value = "/sleep", method = RequestMethod.POST, consumes = {"text/plain"}, produces = "application/JSON")
	public @ResponseBody ServiceResponse thread(@RequestBody String request) {
		
		log.info("CamelController thread Start, Request - " + request);
		
		long sleepTime = Long.parseLong (request);
		ServiceResponse response = new ServiceResponse();
		
		try {
			Thread.sleep(sleepTime);

			response.setErrorCode("200");
			response.setErrorMsg("SUCCESS");
			response.setRespCode("200");
			response.setRespMsg("Succesfully halted response for " + request + " milliseconds");
			
		} catch (InterruptedException e) {

			response.setErrorCode("400");
			response.setErrorMsg("FAILURE");
			response.setRespCode("400");
			response.setRespMsg(null);
		}
		
		log.info("CamelController thread End - " + response);
		return response;
	}
}