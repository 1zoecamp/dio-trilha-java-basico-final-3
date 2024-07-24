package me.ow.controller;

import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import me.ow.domain.model.Cat;
import me.ow.domain.model.Owner;
import me.ow.service.CatService;

@RestController
@RequestMapping("cats")
public class CatController {

	private final CatService catService;

	public CatController(CatService catService) {
		this.catService = catService;
	}

	@GetMapping
	public List<Cat> findAllCats() {
		return catService.findAllCats();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cat> findById(@PathVariable("id") Long id) {
		var user = catService.findbyId(id);
		return ResponseEntity.ok(user);
	}

	@PostMapping
	public ResponseEntity<Cat> insert(@RequestBody Cat catToInsert) {
		var catInserted = catService.insert(catToInsert);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(catInserted.getId()).toUri();
		return ResponseEntity.created(location).body(catInserted);
	}

	@PutMapping("/{id}/adopt")
	public ResponseEntity<Cat> adopt(@PathVariable("id") Long id, @RequestBody Owner owner) {
		catService.adopt(id, owner);
		return ResponseEntity.ok(catService.findbyId(id));
	}

	@GetMapping("/coat/{coat}")
	public List<Cat> filterByCoat(@PathVariable("coat") String coat) {
		return catService.filterByCoat(coat);
	}

	@GetMapping("/gender/{gender}")
	public List<Cat> filterByGender(@PathVariable("gender") String gender) {
		return catService.filterByGender(gender);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remove(@PathVariable("id") Long id) {
		catService.removeCat(id);
		return ResponseEntity.ok().build();
	}

}
