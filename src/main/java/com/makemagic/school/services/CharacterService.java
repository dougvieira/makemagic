package com.makemagic.school.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.makemagic.school.exception.BadRequestException;
import com.makemagic.school.exception.NoContentException;
import com.makemagic.school.model.CharacterEntity;
import com.makemagic.school.repositories.CharacterRepository;
import com.makemagic.school.validators.CharacterValidator;

@Service
public class CharacterService {

	private static final Logger LOGGER = LogManager.getLogger(CharacterService.class);
	
	@Autowired
	private CharacterRepository repository;
	
	@Autowired
	private CharacterValidator validator;

	public CharacterEntity createCharacter(CharacterEntity character) throws BadRequestException, NoContentException {
		try {
			if (validator.isHouseInvalid(character.getHouseId())) {
				LOGGER.error("Id da casa é invalido");
				throw new BadRequestException("Id da casa é invalido");
			}
			return repository.save(character);
		} catch (PersistenceException e) {
			LOGGER.error("Erro ao criar um novo personagem: ", e);
			throw new BadRequestException("Erro ao criar um novo personagem");
		}
	}

	public CharacterEntity updateCharacter(CharacterEntity character) throws BadRequestException {
		try {
			if (validator.isHouseInvalid(character.getHouseId())) {
				LOGGER.error("Id da casa é invalido");
				throw new BadRequestException("Id da casa é invalido");
			}
			return repository.save(character);
		} catch (PersistenceException e) {
			LOGGER.error("Erro ao atualizar um novo personagem: ", e);
			throw new BadRequestException("Mensagem Customizada");
		}
	}

	public void deleteCharacter(CharacterEntity character) throws BadRequestException {
		try {
			repository.delete(character);
		} catch (PersistenceException e) {
			LOGGER.error("Erro ao excluir um personagem: ", e);
			throw new BadRequestException("Erro ao excluir um personagem");
		}
	}

	public List<CharacterEntity> getCharacters(String houseId) throws BadRequestException, NoContentException {
		try {
			if (houseId != null) {
				List<CharacterEntity> findByHouseId = repository.findCharacterByHouseId(houseId);
				if(findByHouseId.isEmpty()) {
					LOGGER.error("Não existe a HouseId informada");
					throw new NoContentException("Não existe a HouseId informada");
				}
				return findByHouseId;
			} else {
				return repository.findAll(); 
			}			
		} catch (PersistenceException e) {
			LOGGER.error("Erro ao consultar o personagem: ", e);
			throw new BadRequestException("Erro ao consultar o personagem");
		} 
	}

	public Optional<CharacterEntity> getCharactersById(Long id) throws NoContentException, BadRequestException {
		try {
			if (id == null) {
				LOGGER.error("Não existe a HouseId informada");
				throw new NoContentException("Não existe a HouseId informada");
			} else {
				return repository.findById(id); 
			}			
		} catch (PersistenceException e) {
			LOGGER.error("Erro ao consultar o personagem: ", e);
			throw new BadRequestException("Erro ao consultar o personagem");
		} 
	}
}