package kodlama.io.rentACar.business.abstracts;

import java.util.List;

import kodlama.io.rentACar.business.requests.CreateBrandRequest;
import kodlama.io.rentACar.business.requests.UpdateBrandRequest;
import kodlama.io.rentACar.business.responses.GetAllBrandsResponse;
import kodlama.io.rentACar.business.responses.GetByIdBrandsResponse;

public interface BrandService {
	List<GetAllBrandsResponse> getAll();

	void add(CreateBrandRequest createBrandReques);

	GetByIdBrandsResponse getById(int id);

	void delete(int id);

	void update(UpdateBrandRequest updateBrandRequest);
}
