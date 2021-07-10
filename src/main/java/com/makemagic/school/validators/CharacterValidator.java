package com.makemagic.school.validators;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.cache2k.Cache;
import org.cache2k.Cache2kBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.makemagic.school.model.HouseEntity;
import com.makemagic.school.services.HouseService;

@Component
public class CharacterValidator {
	
	private static final long EXPIRE_TIMEOUT = 2;
	private static final Logger LOGGER = LogManager.getLogger(CharacterValidator.class);
	private static final Cache<String, List<HouseEntity>> houseCache = new Cache2kBuilder<String, List<HouseEntity>>(){}
																			.name("houseCache")
																			.expireAfterWrite(EXPIRE_TIMEOUT, TimeUnit.MINUTES)
																			.build();
    
	@Autowired
	private HouseService service;
	
	public boolean isHouseInvalid(String houseId) {
		boolean contaisHouseIdParam = true;
		List<HouseEntity> houses = houseCache.get("houses");
		if (houses == null) {
			houses = service.getHouses();
			if (houses != null) {
				LOGGER.info("Criando novo Cache: " + houses);
				houseCache.put("houses", houses);
			}
		}
		contaisHouseIdParam = !houses.stream().filter(house -> houseId.equals(house.getId())).findAny().isPresent();
		return contaisHouseIdParam;
	}
}
