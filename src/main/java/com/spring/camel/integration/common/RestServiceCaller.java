package com.spring.camel.integration.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.spring.camel.integration.dto.RequestDto;
import com.spring.camel.integration.dto.ServiceResponse;

@Service
public class RestServiceCaller {

	private static final Logger log = LoggerFactory.getLogger(RestServiceCaller.class);
	
	@Autowired
	RestTemplate restTemplate;

	public ServiceResponse callSCIntI(RequestDto request) throws Exception {

		log.info("RestServiceCaller callSCIntI() - entrying");
		
		ServiceResponse response = null;
		String REQUEST_URI = "http://localhost:8082/";
		//String REQUEST_URI = "http://localhost:8082/ust-global/employeesList";
		
		ResponseEntity<ServiceResponse> respEntity = new ResponseEntity<ServiceResponse>(response, HttpStatus.OK);
		try {
			respEntity = restTemplate.postForEntity(REQUEST_URI, request, ServiceResponse.class);
			
			log.info("RestServiceCaller callSCIntI() - Status: " + respEntity.getStatusCode());
			response = respEntity.getBody();
			
			log.info("RestServiceCaller callSCIntI() - exiting");
			return response;
			
		} catch (Exception ex)	{
			log.error("RestServiceCaller callSCIntI() - error: " + ex.getMessage());
			
			String respMsg = null!=ex.getMessage()?ex.getMessage():ex.getCause().getMessage();
			response = new ServiceResponse();
			
			response.setErrorCode("400");
			response.setErrorMsg("FAILURE");
			response.setRespCode("400");
			response.setRespMsg(respMsg);
			
			log.error("RestServiceCaller callSCIntI() - error: returning failure response");
			
			return response;
		}
	}

	public ServiceResponse callSCIntII(RequestDto request) throws Exception {

		log.info("RestServiceCaller callSCIntII() - entrying");
		
		ServiceResponse response = null;
		String REQUEST_URI = "http://localhost:8083/";
		
		ResponseEntity<ServiceResponse> respEntity = new ResponseEntity<ServiceResponse>(response, HttpStatus.OK);
		try {
			respEntity = restTemplate.postForEntity(REQUEST_URI, request, ServiceResponse.class);
			
			log.info("RestServiceCaller callSCIntII() - Status: " + respEntity.getStatusCode());
			response = respEntity.getBody();
			
			log.info("RestServiceCaller callSCIntII() - exiting");
			return response;
			
		} catch (Exception ex)	{
			log.error("RestServiceCaller callSCIntII() - error: " + ex.getMessage());
			
			String respMsg = null!=ex.getMessage()?ex.getMessage():ex.getCause().getMessage();
			response = new ServiceResponse();
			
			response.setErrorCode("400");
			response.setErrorMsg("FAILURE");
			response.setRespCode("400");
			response.setRespMsg(respMsg);
			
			log.error("RestServiceCaller callSCIntII() - error: returning failure response");
			
			return response;
		}
	}

	public ServiceResponse callSCIntIII(RequestDto request) throws Exception {

		log.info("RestServiceCaller callSCIntIII() - entrying");
		
		ServiceResponse response = null;
		String REQUEST_URI = "http://localhost:8081/";
		
		ResponseEntity<ServiceResponse> respEntity = new ResponseEntity<ServiceResponse>(response, HttpStatus.OK);
		try {
			respEntity = restTemplate.postForEntity(REQUEST_URI, request, ServiceResponse.class);
			
			log.info("RestServiceCaller callSCIntIII() - Status: " + respEntity.getStatusCode());
			response = respEntity.getBody();
			
			log.info("RestServiceCaller callSCIntIII() - exiting");
			return response;
			
		} catch (Exception ex)	{
			log.error("RestServiceCaller callSCIntIII() - error: " + ex.getMessage());
			
			String respMsg = null!=ex.getMessage()?ex.getMessage():ex.getCause().getMessage();
			response = new ServiceResponse();
			
			response.setErrorCode("400");
			response.setErrorMsg("FAILURE");
			response.setRespCode("400");
			response.setRespMsg(respMsg);
			
			log.error("RestServiceCaller callSCIntIII() - error: returning failure response");
			
			return response;
		}
	}

}