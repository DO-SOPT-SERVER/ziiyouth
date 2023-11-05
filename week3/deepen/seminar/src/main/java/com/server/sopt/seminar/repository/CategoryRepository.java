package com.server.sopt.seminar.repository;

import com.server.sopt.seminar.domain.Category;
import com.server.sopt.seminar.domain.CategoryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, CategoryId> {
}
