package vn.com.devmaster.service.managematerial.mapper;

import org.springframework.stereotype.Component;
import vn.com.devmaster.service.managematerial.dommain.Category;
import vn.com.devmaster.service.managematerial.dto.CategoryDto;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class CategoryMapper implements EntityMapper<Category, CategoryDto>{
    @Override
    public Category toEntity(CategoryDto dto) {
        Category category = Category.builder()
                .id(dto.getId())
                .idparent(dto.getIdparent())
                .name(dto.getName())
                .notes(dto.getNotes())
                .icon(dto.getIcon())
                .createdDate(dto.getCreatedDate())
                .updatedDate(dto.getUpdatedDate())
                .createdBy(dto.getCreatedBy())
                .updatedBy(dto.getUpdatedBy())
                .isactive(dto.getIsactive())
                .build();
        return null;
    }

    @Override
    public List<Category> toEntity(List<CategoryDto> d) {
        List<Category> list = new ArrayList<>();
        d.forEach(dto -> {
            list.add(toEntity(dto));
        });
        return list;
    }

    @Override
    public CategoryDto toDto(Category category) {
        CategoryDto categoryDto = CategoryDto.builder()
                .id(category.getId())
                .idparent(category.getIdparent())
                .name(category.getName())
                .notes(category.getNotes())
                .icon(category.getIcon())
                .createdDate((category.getCreatedDate()))
                .updatedDate(category.getUpdatedDate())
                .createdBy(category.getCreatedBy())
                .updatedBy(category.getUpdatedBy())
                .isactive(category.getIsactive())
                .build();
        return categoryDto;
    }

    @Override
    public List<CategoryDto> toDto(List<Category> e) {
        List<CategoryDto> list = new ArrayList<>();
        e.forEach(category -> {
            list.add(toDto(category));
        });
        return list;
    }
}
