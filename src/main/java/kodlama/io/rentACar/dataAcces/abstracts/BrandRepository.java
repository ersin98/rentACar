package kodlama.io.rentACar.dataAcces.abstracts;
//dao = Repository

import org.springframework.data.jpa.repository.JpaRepository;

import kodlama.io.rentACar.entities.concretes.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
	boolean existsByName(String name);
	// List<Brand> getAll();
	// zaten jpa bizei şlemleri veriyor
}
