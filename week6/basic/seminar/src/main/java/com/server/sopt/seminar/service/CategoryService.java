package com.server.sopt.seminar.service;

import com.server.sopt.seminar.domain.Category;
import com.server.sopt.seminar.domain.CategoryId;
import com.server.sopt.seminar.repository.CategoryRepository;
import com.server.sopt.seminar.response.categoty.CategoryResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category getByCategoryId(CategoryId categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(
                () -> new EntityNotFoundException("해당하는 카테고리가 없습니다."));
    }
}
