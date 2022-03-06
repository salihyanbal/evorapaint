package com.lukodev.evorapaint.business.concretes;

import com.lukodev.evorapaint.business.abstracts.CategoryService;
import com.lukodev.evorapaint.business.constants.Messages;
import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.core.utilities.results.SuccessDataResult;
import com.lukodev.evorapaint.core.utilities.results.SuccessResult;
import com.lukodev.evorapaint.dataAccess.abstracts.CategoryDao;
import com.lukodev.evorapaint.entities.concretes.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryManager implements CategoryService {

    private CategoryDao categoryDao;

    @Autowired
    public CategoryManager(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    @CacheEvict(value = "category.getAll", allEntries = true)
    public Result add(Category category) {
        this.categoryDao.save(category);
        return new SuccessResult(Messages.CATEGORY_ADDED);
    }

    @Override
    @CacheEvict(value = "category.getAll", allEntries = true)
    public Result update(Category category) {
        this.categoryDao.save(category);
        return new SuccessResult(Messages.CATEGORY_UPDATED);
    }

    @Override
    @CacheEvict(value = "category.getAll", allEntries = true)
    public Result delete(Category category) {
        this.categoryDao.delete(category);
        return new SuccessResult(Messages.CATEGORY_DELETED);
    }

    @Override
    @Cacheable("category.getAll")
    public DataResult<List<Category>> getAll() {
        return new SuccessDataResult<>(this.categoryDao.findAll());
    }

    @Override
    public DataResult<List<Category>> getAllActive() {
        return new SuccessDataResult<>(this.categoryDao.getAllByActiveTrue());
    }

    @Override
    public DataResult<Category> getById(int id) {
        return new SuccessDataResult<>(this.categoryDao.findById(id).get());
    }
}
