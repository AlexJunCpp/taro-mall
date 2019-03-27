package org.rxjava.service.goods.repository;

import org.rxjava.service.goods.entity.Category;
import org.rxjava.service.goods.form.CategoryListForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * @author happy 2019-03-23 00:17
 */
@Repository
public interface CategoryRepository extends ReactiveSortingRepository<Category, String>, SpecialCategoryRepository {

}

interface SpecialCategoryRepository {
    Flux<Category> getList(CategoryListForm form);
}

class SpecialCategoryRepositoryImpl implements SpecialCategoryRepository {
    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Flux<Category> getList(CategoryListForm form) {
        return null;
    }
}
