package pdp.uz.program_41.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pdp.uz.program_41.entity.Attachment;
import pdp.uz.program_41.entity.Category;
import pdp.uz.program_41.entity.Measurement;
import pdp.uz.program_41.entity.Product;
import pdp.uz.program_41.payload.ProductDto;
import pdp.uz.program_41.payload.Result;
import pdp.uz.program_41.repository.AttachmentRepository;
import pdp.uz.program_41.repository.CategoryRepository;
import pdp.uz.program_41.repository.MeasurementRepository;
import pdp.uz.program_41.repository.ProductRepository;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    MeasurementRepository measurementRepository;
    @Autowired
    AttachmentRepository attachmentRepository;


    public Result add(ProductDto productDto){
boolean existsProductByNameAndCategoryIdAndActive = productRepository.existsProductByNameAndCategoryIdAndActive(productDto.getName(),productDto.getCategoryId(), true);
if(existsProductByNameAndCategoryIdAndActive){
    return new Result("This category already exist such product name!", false);
}
boolean existsProductByCodeAndActive = productRepository.existsProductByCodeAndActive(productDto.getCode(), true);
  if(existsProductByCodeAndActive){
      return new Result("Such code of product already exist!", false);
  }
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
  Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
  Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
  Product product = new Product();
  product.setName(productDto.getName());
  product.setCode(productDto.getCode());
  product.setCategory(optionalCategory.get());
  product.setMeasurement(optionalMeasurement.get());
  product.setPhoto(optionalAttachment.get());
  productRepository.save(product);
  return new Result("New product successfully saved.", true);
    }

    public Result get(int page){
        boolean existsProductByActive = productRepository.existsProductByActive(true);
if(existsProductByActive){
    Pageable pageable = PageRequest.of(page,10);
    Page<Product> page1 =productRepository.getProductByActive(true, pageable);
    return new Result(page1);
}
return new Result("Products not exist yet!", false);
    }

    public Result getById(Integer id){
        boolean existsProductByIdAndActive = productRepository.existsProductByIdAndActive(id, true);
        if(!existsProductByIdAndActive){
            return new Result("Such product id not exist!", false);
        }
        Optional<Product> optionalProduct = productRepository.findById(id);
        return new Result(optionalProduct.get());
    }

    public Result edit(Integer id, ProductDto productDto){
        boolean existsProductByIdAndActive = productRepository.existsProductByIdAndActive(id, true);
        if(!existsProductByIdAndActive){
            return new Result("Such product id not exist!", false);
        }
        Optional<Product> optionalProduct = productRepository.findById(id);
        Product product = optionalProduct.get();

        boolean existsProductByNameAndCategoryIdAndActive = productRepository.existsProductByNameAndCategoryIdAndActive(productDto.getName(),productDto.getCategoryId(),true);
    if(existsProductByNameAndCategoryIdAndActive){
        return new Result("This category already exist such product name!", false);
    }
    boolean existsProductByCodeAndActive = productRepository.existsProductByCodeAndActive(productDto.getCode(), true);
    if(existsProductByCodeAndActive){
        return new Result("Such product code already exist!", false);
    }
    Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
    Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
    Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
    product.setName(productDto.getName());
    product.setCode(productDto.getCode());
    product.setCategory(optionalCategory.get());
    product.setMeasurement(optionalMeasurement.get());
    product.setPhoto(optionalAttachment.get());
    productRepository.save(product);
    return new Result("Given product successfully edited.", true);
    }

    public Result delete(Integer id){
        boolean existsProductByIdAndActive = productRepository.existsProductByIdAndActive(id, true);
        if(!existsProductByIdAndActive){
            return new Result("Such product id not exist!", false);
        }
        Optional<Product> optionalProduct = productRepository.findById(id);
        Product product = optionalProduct.get();
        product.setActive(false);
        productRepository.save(product);
        return new Result("Given product successfully deleted.", true);
    }
}

