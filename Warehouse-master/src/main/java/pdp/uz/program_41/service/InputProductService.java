package pdp.uz.program_41.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pdp.uz.program_41.entity.Input;
import pdp.uz.program_41.entity.InputProduct;
import pdp.uz.program_41.entity.Product;
import pdp.uz.program_41.payload.InputProductDto;
import pdp.uz.program_41.payload.Result;
import pdp.uz.program_41.repository.InputProductRepository;
import pdp.uz.program_41.repository.InputRepository;
import pdp.uz.program_41.repository.ProductRepository;

import java.util.Optional;

@Service
public class InputProductService {

    @Autowired
    InputProductRepository inputProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    InputRepository inputRepository;

    public Result add(InputProductDto inputProductDto){
boolean existsInputProductByProductIdAndInputId = inputProductRepository.existsInputProductByProductIdAndInputId(inputProductDto.getProductId(), inputProductDto.getInputId());
if(existsInputProductByProductIdAndInputId){
    return new Result("This product already added to this input!",false);
}
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        InputProduct inputProduct = new InputProduct();
inputProduct.setAmount(inputProductDto.getAmount());
inputProduct.setPrice(inputProductDto.getPrice());
inputProduct.setExpireDate(inputProductDto.getExpireDate());
inputProduct.setProduct(optionalProduct.get());
inputProduct.setInput(optionalInput.get());
inputProductRepository.save(inputProduct);
return new Result("New input product successfully saved.", true);
    }

    public Result get(int page){
        boolean existsInputProduct = inputProductRepository.existsInputProduct();
        if(existsInputProduct){
            Pageable pageable = PageRequest.of(page,10);
            Page<InputProduct> page1 = inputProductRepository.findAll(pageable);
            return new Result(page1);
        }
        return new Result("Input products not exists yet!",false);
    }

    public Result getById(Integer id){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if(optionalInputProduct.isPresent()){
            return new Result(optionalInputProduct.get());
        }
        return new Result("Such input product id not exist!", false);
    }



    public Result getByInputId(Integer inputId, int page){
        boolean existsInputProductByInputId = inputProductRepository.existsInputProductByInputId(inputId);
        if(!existsInputProductByInputId){
            return new Result("This input does not have any product!",false);
        }
        Pageable pageable = PageRequest.of(page, 10);
        Page<InputProduct> page1 = inputProductRepository.getInputProductByInputId(inputId, pageable);
        return new Result(page1);
    }

    public Result edit(Integer id, InputProductDto inputProductDto){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if(!optionalInputProduct.isPresent()){
            return new Result("Such input product id not exists!", false);
        }
        InputProduct inputProduct = optionalInputProduct.get();
        boolean existsInputProductByProductIdAndInputId = inputProductRepository.existsInputProductByProductIdAndInputId(inputProductDto.getProductId(), inputProductDto.getInputId());
        if(existsInputProductByProductIdAndInputId && !(inputProduct.getProduct().getId().equals(inputProductDto.getProductId())  && inputProduct.getInput().getId().equals(inputProductDto.getInputId()))){
return new Result("Such product already exists in this input!",false);
        }
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
    inputProduct.setAmount(inputProductDto.getAmount());
    inputProduct.setPrice(inputProductDto.getPrice());
    inputProduct.setExpireDate(inputProductDto.getExpireDate());
    inputProduct.setProduct(optionalProduct.get());
    inputProduct.setInput(optionalInput.get());
    inputProductRepository.save(inputProduct);
    return new Result("Given input product successfully edited.",true);
    }

}
