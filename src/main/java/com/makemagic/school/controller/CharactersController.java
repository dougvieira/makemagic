package com.makemagic.school.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.makemagic.school.exception.BadRequestException;
import com.makemagic.school.exception.NoContentException;
import com.makemagic.school.model.CharacterEntity;
import com.makemagic.school.services.CharacterService;

@RestController
@RequestMapping("/api/characters")
public class CharactersController {
	
	@Autowired
	private CharacterService service;

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> create(@RequestBody CharacterEntity character) {
		try {
			CharacterEntity createCharacter = service.createCharacter(character);	
			return ResponseEntity.status(HttpStatus.CREATED).body("Personagem criado com sucesso. id: "+ createCharacter.getId());
		} catch (BadRequestException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (NoContentException e ) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
	
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CharacterEntity> update(@RequestBody CharacterEntity character) {
		try {
			CharacterEntity updateCharacter = service.updateCharacter(character);
			return ResponseEntity.status(HttpStatus.OK).body(updateCharacter);
		} catch (BadRequestException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@DeleteMapping
	public ResponseEntity<String> delete(@RequestBody CharacterEntity character) {
		try {
			service.deleteCharacter(character);
			return ResponseEntity.status(HttpStatus.OK).body("delete Character");
		} catch (BadRequestException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
			
	@GetMapping
	public ResponseEntity<List<CharacterEntity>> responseCharacters(@RequestParam(value = "house", required = false) String houseId) {
		try {
			List<CharacterEntity> charactersList = service.getCharacters(houseId);
			return ResponseEntity.status(HttpStatus.OK).body(charactersList);
		}
		catch (BadRequestException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (NoContentException e ) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Optional<CharacterEntity>> responseCharactersByName(@PathVariable Long id) {
		try {
			Optional<CharacterEntity> charactersList = service.getCharactersById(id);
			return ResponseEntity.status(HttpStatus.OK).body(charactersList);
		}
		catch (BadRequestException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (NoContentException e ) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
}