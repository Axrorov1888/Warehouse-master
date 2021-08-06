package pdp.uz.program_41.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pdp.uz.program_41.entity.Currency;
import pdp.uz.program_41.entity.Input;
import pdp.uz.program_41.entity.Supplier;
import pdp.uz.program_41.entity.Warehouse;
import pdp.uz.program_41.payload.InputDto;
import pdp.uz.program_41.payload.Result;
import pdp.uz.program_41.repository.CurrencyRepository;
import pdp.uz.program_41.repository.InputRepository;
import pdp.uz.program_41.repository.SupplierRepository;
import pdp.uz.program_41.repository.WarehouseRepository;

import java.util.Optional;

@Service
public class InputService {

    @Autowired
    InputRepository inputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    CurrencyRepository currencyRepository;

    public Result add(InputDto inputDto) {
        boolean existsInputByFactureNumber = inputRepository.existsInputByFactureNumber(inputDto.getFactureNumber());
        if (existsInputByFactureNumber) {
            return new Result("Such facture number of input already exist!", false);
        }
        boolean existsInputByCode = inputRepository.existsInputByCode(inputDto.getCode());
        if (existsInputByCode) {
            return new Result("Such code of input already exist!", false);
        }
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        Input input = new Input();
        input.setFactureNumber(inputDto.getFactureNumber());
        input.setCode(inputDto.getCode());
        input.setWarehouse(optionalWarehouse.get());
        input.setSupplier(optionalSupplier.get());
        input.setCurrency(optionalCurrency.get());
        inputRepository.save(input);
        return new Result("New input successfully saved.", true);
    }


    public Result get(int page) {
        boolean existsInput = inputRepository.existsInput();
        if (existsInput) {
            Pageable pageable = PageRequest.of(page,10);
            Page<Input> page1 = inputRepository.findAll(pageable);
            return new Result(page1);
        }
        return new Result("Inputs not exist yet!", false);
    }

    public Result getById(Integer id) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (optionalInput.isPresent()) {
            return new Result(optionalInput.get());
        }
        return new Result("Such input id not exist!", false);
    }

    public Result edit(Integer id, InputDto inputDto) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (optionalInput.isPresent()) {
            Input input = optionalInput.get();
            boolean existsInputByFactureNumber = inputRepository.existsInputByFactureNumber(inputDto.getFactureNumber());
            if (existsInputByFactureNumber && !input.getFactureNumber().equals(inputDto.getFactureNumber())) {
                return new Result("Such facture number of input already exist!", false);
            }
            boolean existsInputByCode = inputRepository.existsInputByCode(inputDto.getCode());
            if (existsInputByCode && input.getCode().equals(inputDto.getCode())) {
                return new Result("Such code of input already exists!", false);
            }
            Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
            Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
            Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
            input.setCode(inputDto.getCode());
            input.setFactureNumber(inputDto.getFactureNumber());
            input.setWarehouse(optionalWarehouse.get());
            input.setSupplier(optionalSupplier.get());
            input.setCurrency(optionalCurrency.get());
            inputRepository.save(input);
            return new Result("Given input successfully edited.", true);
        }
        return new Result("Such input id not exist!", false);
    }
}


