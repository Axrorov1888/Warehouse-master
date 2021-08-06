package pdp.uz.program_41.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pdp.uz.program_41.entity.Client;
import pdp.uz.program_41.entity.Currency;
import pdp.uz.program_41.entity.Output;
import pdp.uz.program_41.entity.Warehouse;
import pdp.uz.program_41.payload.OutputDto;
import pdp.uz.program_41.payload.Result;
import pdp.uz.program_41.repository.ClientRepository;
import pdp.uz.program_41.repository.CurrencyRepository;
import pdp.uz.program_41.repository.OutputRepository;
import pdp.uz.program_41.repository.WarehouseRepository;

import java.util.Optional;

@Service
public class OutputService {

    @Autowired
    OutputRepository outputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CurrencyRepository currencyRepository;

    public Result add(OutputDto outputDto){
        boolean existsOutputByCode = outputRepository.existsOutputByCode(outputDto.getCode());
    if(existsOutputByCode){
        return new Result("Such output code already exists!", false);
    }
    boolean existsOutputByFactureNumber = outputRepository.existsOutputByFactureNumber(outputDto.getFactureNumber());
    if(existsOutputByFactureNumber){
        return new Result("Such facture number already exists!", false);
    }
    Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
    Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
    Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
Output output = new Output();
output.setCode(outputDto.getCode());
output.setFactureNumber(outputDto.getFactureNumber());
output.setWarehouse(optionalWarehouse.get());
output.setClient(optionalClient.get());
output.setCurrency(optionalCurrency.get());
outputRepository.save(output);
return new Result("New output successfully saved.", true);
    }

    public Result get(int page){
boolean existsOutput = outputRepository.existsOutput();
if(existsOutput){
    Pageable pageable = PageRequest.of(page,10);
    Page<Output> page1 = outputRepository.findAll(pageable);
    return new Result(page1);
}
return new Result("Outputs not exist yet!", false);
    }

public Result getById(Integer id){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if(optionalOutput.isPresent()){
            return new Result(optionalOutput.get());
        }
    return new Result("Such output id not exist!", false);
}

public Result edit(Integer id, OutputDto outputDto){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if(!optionalOutput.isPresent()){
            return new Result("Such output id not exist!",false);
        }
        Output output = optionalOutput.get();
        boolean existsOutputByFactureNumber = outputRepository.existsOutputByFactureNumber(outputDto.getFactureNumber());
        if(existsOutputByFactureNumber && !output.getFactureNumber().equals(outputDto.getFactureNumber())){
            return new Result("Such facture number of output already exist!",false);
        }
        boolean existsOutputByCode = outputRepository.existsOutputByCode(outputDto.getCode());
        if(existsOutputByCode && !output.getCode().equals(outputDto.getCode())){
            return new Result("Such code of output already exist!", false);
        }
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        output.setFactureNumber(outputDto.getFactureNumber());
        output.setCode(outputDto.getCode());
        output.setWarehouse(optionalWarehouse.get());
        output.setClient(optionalClient.get());
        output.setCurrency(optionalCurrency.get());
        outputRepository.save(output);
        return new Result("Given output successfully edited.", true);
}
}


