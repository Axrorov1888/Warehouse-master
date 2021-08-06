package pdp.uz.program_41.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pdp.uz.program_41.entity.Output;
import pdp.uz.program_41.entity.OutputProduct;
import pdp.uz.program_41.entity.Product;
import pdp.uz.program_41.payload.OutputProductDto;
import pdp.uz.program_41.payload.Result;
import pdp.uz.program_41.repository.OutputProductRepository;
import pdp.uz.program_41.repository.OutputRepository;
import pdp.uz.program_41.repository.ProductRepository;

import java.util.Optional;

@Service
public class OutputProductService {

    @Autowired
    OutputProductRepository outputProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OutputRepository outputRepository;

    public Result add(OutputProductDto outputProductDto){
boolean existsOutputProductByProductIdAndOutputId =outputProductRepository.existsOutputProductByProductIdAndOutputId(outputProductDto.getProductId(), outputProductDto.getOutputId());
if(existsOutputProductByProductIdAndOutputId){
    return new Result("Such product already added to this output!" ,false);
}
Optional<Product> optionalProduct =  productRepository.findById(outputProductDto.getProductId());
    Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
    OutputProduct outputProduct = new OutputProduct();
    outputProduct.setAmount(outputProductDto.getAmount());
    outputProduct.setPrice(outputProductDto.getPrice());
    outputProduct.setProduct(optionalProduct.get());
    outputProduct.setOutput(optionalOutput.get());
outputProductRepository.save(outputProduct);
        return new Result("New output product successfully saved.", true);
    }

    public Result get(int page){
        boolean existsOutputProduct = outputProductRepository.existsOutputProduct();
        if(existsOutputProduct){
            Pageable pageable = PageRequest.of(page, 10);
            Page<OutputProduct>  page1 = outputProductRepository.findAll(pageable);
            return new Result(page1);
        }
        return new Result("Output products not exist yet!", false);
    }

    public Result getByOutputId(Integer outputId, int page) {
        boolean existsOutputProductByOutputId = outputProductRepository.existsOutputProductByOutputId(outputId);
        if (!existsOutputProductByOutputId) {
            return new Result("This output does not any products!", false);
        }
        Pageable pageable = PageRequest.of(page,10);
        Page<OutputProduct> page1 = outputProductRepository.getOutputProductByOutputId(outputId, pageable);
        return new Result(page1);
    }


        public Result getById(Integer id){
            Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
            if(optionalOutputProduct.isPresent()){
                return new Result(optionalOutputProduct.get());
        }
            return new Result("Such output product id not exist!", false);
    }

    public Result edit(Integer id, OutputProductDto outputProductDto){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if(optionalOutputProduct.isPresent()){
            OutputProduct outputProduct = optionalOutputProduct.get();
            boolean existsOutputProductByProductIdAndOutputId = outputProductRepository.existsOutputProductByProductIdAndOutputId(outputProductDto.getProductId(), outputProductDto.getOutputId());
if(existsOutputProductByProductIdAndOutputId && !(outputProduct.getProduct().getId().equals(outputProductDto.getProductId()) && outputProduct.getOutput().getId().equals(outputProductDto.getOutputId()))){
    return new Result("This product already added to this output!", false);
}

Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
outputProduct.setAmount(outputProductDto.getAmount());
outputProduct.setPrice(outputProductDto.getPrice());
outputProduct.setProduct(optionalProduct.get());
outputProduct.setOutput(optionalOutput.get());
outputProductRepository.save(outputProduct);
return new Result("Given output product successfully edited!", true);
        }
        return new Result("Such output product id not exist!", false);
    }




}
