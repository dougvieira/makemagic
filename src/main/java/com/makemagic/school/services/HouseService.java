package com.makemagic.school.services;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.makemagic.school.model.HouseEntity;
import com.makemagic.school.model.HousesListEntity;

@Service
public class HouseService {

	private static final String POTTER_HOUSES_URI = "http://us-central1-rh-challenges.cloudfunctions.net/potterApi/houses";
	private static final String API_KEY = "c4c03b73-b5a3-46ec-95b7-e41e846575cf";
	
	public List<HouseEntity> getHouses(){
		try {
		    RestTemplate restTemplate = new RestTemplate();
		    ObjectMapper mapper = new ObjectMapper();
		    HttpHeaders headers = new HttpHeaders();
		    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		    headers.add("apikey", API_KEY);
		    
		    HttpEntity<String> entity = new HttpEntity<>("body", headers);
		    ResponseEntity<String> responseEntity = restTemplate.exchange(POTTER_HOUSES_URI, HttpMethod.GET, entity, String.class);
	    	return mapper.readValue(responseEntity.getBody(), new TypeReference<HousesListEntity>(){}).getHouses();
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return Collections.emptyList();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}  
	}
}