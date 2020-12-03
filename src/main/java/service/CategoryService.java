package service;

import model.Category;

public interface CategoryService {
    Iterable<Category> findAll();

    Category findById(Integer id);
}
