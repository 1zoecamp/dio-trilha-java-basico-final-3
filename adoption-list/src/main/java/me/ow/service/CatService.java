package me.ow.service;

import java.util.List;

import me.ow.domain.model.Cat;
import me.ow.domain.model.Owner;

public interface CatService {

	List<Cat> findAllCats();

	Cat findbyId(Long id);

	Cat insert(Cat catToInsert);

	Cat adopt(Long id, Owner owner);

	List<Cat> filterByCoat(String coat);
	
	List<Cat> filterByGender(String gender);
	
	public void removeCat(Long id);
}
