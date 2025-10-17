package mayankbijw3.billingSoftware.repository;

import mayankbijw3.billingSoftware.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepo extends JpaRepository<CategoryEntity,Long> {

   Optional<CategoryEntity> findByCategoryId(String categoryId);

}
