package mayankbijw3.billingSoftware.service;

import mayankbijw3.billingSoftware.io.CategoryRequest;
import mayankbijw3.billingSoftware.io.CategoryResponse;

import java.util.List;

public interface CategoryService {

   CategoryResponse add(CategoryRequest request);

   List<CategoryResponse> read();

   void delete(String categoryId);

}
