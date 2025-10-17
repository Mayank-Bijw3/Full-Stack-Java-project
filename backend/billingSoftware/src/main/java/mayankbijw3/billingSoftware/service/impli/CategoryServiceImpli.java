package mayankbijw3.billingSoftware.service.impli;

import lombok.RequiredArgsConstructor;
import mayankbijw3.billingSoftware.entity.CategoryEntity;
import mayankbijw3.billingSoftware.io.CategoryRequest;
import mayankbijw3.billingSoftware.io.CategoryResponse;
import mayankbijw3.billingSoftware.repository.CategoryRepo;
import mayankbijw3.billingSoftware.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpli implements CategoryService {

    private final CategoryRepo categoryRepo;


    @Override
    public CategoryResponse add(CategoryRequest request) {
       CategoryEntity newCategory = converToEntity(request);
       newCategory= categoryRepo.save(newCategory);
       return convertToResponse(newCategory);
    }

    @Override
    public List<CategoryResponse> read() {
       return categoryRepo.findAll()
                .stream()
                .map(categoryEntity -> convertToResponse(categoryEntity))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String categoryId) {
       CategoryEntity existingCategory = categoryRepo.findByCategoryId(categoryId)
                .orElseThrow(()-> new RuntimeException("Category not Found " + categoryId));

       categoryRepo.delete(existingCategory);
    }

    private CategoryResponse convertToResponse(CategoryEntity newCategory) {
       return CategoryResponse.builder()
                .categoryId(newCategory.getCategoryId())
                .name(newCategory.getName())
                .description(newCategory.getDescription())
                .bgColor(newCategory.getBgColor())
                .imgUrl(newCategory.getImgUrl())
                .createdAt(newCategory.getCreatedAt())
                .updatedAt(newCategory.getUpdatedAt())
                .build();

    }

    private CategoryEntity converToEntity(CategoryRequest request) {
        return CategoryEntity.builder()
                .categoryId(UUID.randomUUID().toString())
                .name(request.getName())
                .description(request.getDescription())
                .bgColor(request.getBgColor())
                .build();
    }
}
