package com.makemagic.school.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.makemagic.school.model.CharacterEntity;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {

	@Query(value = "SELECT c FROM CharacterEntity c WHERE c.houseId = ?1")
	List<CharacterEntity> findCharacterByHouseId(String houseId);
}
