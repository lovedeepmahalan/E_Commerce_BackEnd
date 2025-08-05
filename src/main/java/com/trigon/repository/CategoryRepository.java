package com.trigon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.trigon.entity.Category;
import com.trigon.request.CreateProductRequest;


public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String category);

    @Query("SELECT c FROM Category c WHERE c.name = :name AND c.parentCategory.name = :parentName")
    Category findByNameAndParent(@Param("name") String name, @Param("parentName") String parentName);


}
