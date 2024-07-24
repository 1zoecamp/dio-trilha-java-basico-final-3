package me.ow.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import me.ow.domain.model.Cat;
import me.ow.domain.model.Owner;
import me.ow.domain.repository.CatRepository;
import me.ow.service.CatService;

@Service
public class CatServiceImpl implements CatService {

	private final CatRepository catRepository;

	public CatServiceImpl(CatRepository catRepository) {
		this.catRepository = catRepository;
	}

	@Override
	public List<Cat> findAllCats() {
		return catRepository.findAll();
	}

	@Override
	public Cat findbyId(Long id) {
		return catRepository.findById(id).orElseThrow(NoSuchElementException::new);
	}

	@Override
	public Cat insert(Cat catToInsert) {
		if (catToInsert.getId() != null && catRepository.existsById(catToInsert.getId())) {
			throw new IllegalArgumentException("This cat id already exists.");
		}
		return catRepository.save(catToInsert);
	}

	@Override
	public Cat adopt(Long id, Owner owner) {
		Cat adoptedCat = catRepository.findById(id).orElseThrow(NoSuchElementException::new);

		adoptedCat.setOwner(owner);
		adoptedCat.setStatus("adopted");
		catRepository.save(adoptedCat);

		return adoptedCat;
	}

	@Override
	public List<Cat> filterByCoat(String coat) {
		return catRepository.findAll().stream().filter(cat -> coat.equalsIgnoreCase(cat.getCoat())).collect(Collectors.toList());
	}
	
	@Override
	public List<Cat> filterByGender(String gender) {
		return catRepository.findAll().stream().filter(cat -> gender.equalsIgnoreCase(cat.getGender())).collect(Collectors.toList());
	}

	@Override
	public void removeCat(Long id) {
		catRepository.deleteById(id);
		
	}

}
