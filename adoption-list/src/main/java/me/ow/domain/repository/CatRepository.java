package me.ow.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.ow.domain.model.Cat;

public interface CatRepository extends JpaRepository<Cat, Long>{

	Cat findByName(String name);
}
