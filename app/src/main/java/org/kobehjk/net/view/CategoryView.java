package org.kobehjk.net.view;

import org.kobehjk.net.entity.CategoryEntity;

/**
 * Created by kobehjk on 15/10/26.
 */
public interface CategoryView {
    void showError();
    void addCategoryInfo(CategoryEntity entity);
    void bindCategoryData();
}
