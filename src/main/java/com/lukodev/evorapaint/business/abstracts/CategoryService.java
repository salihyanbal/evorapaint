package com.lukodev.evorapaint.business.abstracts;

import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.entities.concretes.Category;

import java.util.List;

public interface CategoryService {

    Result add(Category category);
    Result update(Category category);
    Result delete(Category category);

    DataResult<List<Category>> getAll();
    DataResult<Category> getById(int id);
}
