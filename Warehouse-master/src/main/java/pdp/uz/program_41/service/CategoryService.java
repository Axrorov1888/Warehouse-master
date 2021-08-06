package pdp.uz.program_41.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pdp.uz.program_41.entity.Category;
import pdp.uz.program_41.payload.CategoryDto;
import pdp.uz.program_41.payload.Result;
import pdp.uz.program_41.repository.CategoryRepository;

import java.util.Optional;

@Service
public class CategoryService {
@Autowired
    CategoryRepository categoryRepository;


public Result add(CategoryDto categoryDto){
    Integer parentCategory = categoryDto.getParentCategoryId();
    if (parentCategory == 0){
boolean existsParentCategory = categoryRepository.existsParentCategory(categoryDto.getName(), true);
if(existsParentCategory){
    return new Result("Such category already exist!", false);
}
    }else {
        boolean existsCategoryByNameAndParentCategoryIdAndActive = categoryRepository.existsCategoryByNameAndParentCategoryIdAndActive(categoryDto.getName(), categoryDto.getParentCategoryId(), true);
        if (existsCategoryByNameAndParentCategoryIdAndActive) {
            return new Result("Such category already exist!", false);
        }
    }
    Category category = new Category();
if(parentCategory == 0){
    category.setParentCategory(null);
}else{
    Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
    category.setParentCategory(optionalCategory.get());
}
category.setName(categoryDto.getName());
categoryRepository.save(category);
return new Result("New category successfully saved.", true);
}

public Result get(int page){
    boolean existsCategoryByActive = categoryRepository.existsCategoryByActive(true);
    if(!existsCategoryByActive){
        return new Result("Categories not exist yet!", false);
    }
    Pageable pageable = PageRequest.of(page, 10);
    Page<Category> page1 = categoryRepository.getCategoryByActive(true,pageable);
    return new Result(page1);
}

public Result getById(Integer id){
boolean existsCategoryByIdAndActive = categoryRepository.existsCategoryByIdAndActive(id, true);
if(!existsCategoryByIdAndActive){
    return new Result("Such category id not exist!", false);
}
Optional<Category> optionalCategory = categoryRepository.findById(id);
return new Result(optionalCategory.get());
}


public Result edit(Integer id, CategoryDto categoryDto){
    boolean existsCategoryByIdAndActive = categoryRepository.existsCategoryByIdAndActive(id, true);
    if(!existsCategoryByIdAndActive){
        return new Result("Such category id not exist!", false);
    }
    Optional<Category> optionalCategory = categoryRepository.findById(id);
    Category category = optionalCategory.get();
    boolean existsCategoryByNameAndParentCategoryIdAndActive = categoryRepository.existsCategoryByNameAndParentCategoryIdAndActive(categoryDto.getName(), categoryDto.getParentCategoryId(), true);
    if(existsCategoryByNameAndParentCategoryIdAndActive){
        return new Result("Such category already exist!", false);
    }
    Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
    category.setName(categoryDto.getName());
    category.setParentCategory(optionalParentCategory.get());
    categoryRepository.save(category);
    return new Result("Given category successfully edited.",true);
    }

    public Result delete(Integer id){
    boolean existsCategoryByIdAndActive = categoryRepository.existsCategoryByIdAndActive(id, true);
    if(!existsCategoryByIdAndActive){
        return new Result("Such category id not exist!", false);
    }
    Optional<Category> optionalCategory = categoryRepository.findById(id);
    Category category = optionalCategory.get();
    category.setActive(false);
    categoryRepository.save(category);
    return new Result("Given category successfully deleted.", true);
    }


}
