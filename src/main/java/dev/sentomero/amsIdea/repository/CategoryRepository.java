package dev.sentomero.amsIdea.repository;


import dev.sentomero.amsIdea.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
