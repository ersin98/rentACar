package kodlama.io.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kodlama.io.rentACar.business.abstracts.BrandService;
import kodlama.io.rentACar.business.requests.CreateBrandRequest;
import kodlama.io.rentACar.business.requests.UpdateBrandRequest;
import kodlama.io.rentACar.business.responses.GetAllBrandsResponse;
import kodlama.io.rentACar.business.responses.GetByIdBrandsResponse;
import kodlama.io.rentACar.core.utilities.mappers.ModelMapperService;
import kodlama.io.rentACar.dataAcces.abstracts.BrandRepository;
import kodlama.io.rentACar.entities.concretes.Brand;
import lombok.AllArgsConstructor;

//Manager = Service / Imple
@Service // Bu sınıf bir business nesnesidir
@AllArgsConstructor
public class BrandManager implements BrandService {
	private BrandRepository brandRepository;// gevşek bağlı sistem

	private ModelMapperService modelMapperService;

	@Override
	public List<GetAllBrandsResponse> getAll() {
		// burada findAll dan GetAll içine aktarma yapıyoruz
		List<Brand> brands = brandRepository.findAll();
		// List<GetAllBrandsResponse> brandsResponse = new
		// ArrayList<GetAllBrandsResponse>();
		// for (Brand brand : brands) {
		// GetAllBrandsResponse responseItem = new GetAllBrandsResponse();
		// responseItem.setId(brand.getId());
		// responseItem.setName(brand.getName());
		// brandsResponse.add(responseItem);
		// }

		// .stream listeyi tek tek dolaşmayı sağlar. Tıpkı foreach gibi
		List<GetAllBrandsResponse> brandsResponse = brands.stream()
				.map(brand -> this.modelMapperService.forResponse().map(brand, GetAllBrandsResponse.class))
				.collect(Collectors.toList());

		return brandsResponse;
	}

	@Override
	public void add(CreateBrandRequest createBrandReques) {
		// Brand brand = new Brand();
		// brand.setName(createBrandReques.getName());
		Brand brand = modelMapperService.forRequest().map(createBrandReques, Brand.class);
		this.brandRepository.save(brand);

	}

	@Override
	public GetByIdBrandsResponse getById(int id) {
		Brand brand = this.brandRepository.findById(id).orElseThrow();
		GetByIdBrandsResponse response = this.modelMapperService.forResponse().map(brand, GetByIdBrandsResponse.class);
		return response;
	}

	@Override
	public void delete(int id) {
		this.brandRepository.deleteById(id);

	}

	@Override
	public void update(UpdateBrandRequest updateBrandRequest) {
		Brand brand = modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		this.brandRepository.save(brand);
	}

}
