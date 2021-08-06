package pdp.uz.program_41.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pdp.uz.program_41.entity.Currency;
import pdp.uz.program_41.payload.Result;
import pdp.uz.program_41.repository.CurrencyRepository;

import java.util.Optional;

@Service
public class CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;


    public Result add(Currency currency){
        boolean existsCurrencyByNameAndActive = currencyRepository.existsCurrencyByNameAndActive(currency.getName(), true);
        if(existsCurrencyByNameAndActive){
            return new Result("Such currency already exist!", false);
        }
        Currency currency1 = new Currency();
        currency1.setName(currency.getName());
        currencyRepository.save(currency1);
        return new Result("New currency successfully saved.", true);
    }

    public Result get(int page){
        boolean existsCurrencyByActive = currencyRepository.existsCurrencyByActive(true);
        if(!existsCurrencyByActive){
            return new Result("Currencies not exist yet!", false);
        }
        Pageable pageable = PageRequest.of(page, 10);
        Page<Currency> page1 = currencyRepository.getCurrencyByActive(true, pageable);
        return new Result(page1);
    }

    public Result getById(Integer id){
        boolean existsCurrencyByIdAndActive = currencyRepository.existsCurrencyByIdAndActive(id, true);
    if(!existsCurrencyByIdAndActive){
        return new Result("Such currency id not exist!", false);
    }
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
    return new Result(optionalCurrency.get());
    }


    public Result edit(Integer id, Currency currency){
        boolean existsCurrencyByIdAndActive = currencyRepository.existsCurrencyByIdAndActive(id, true);
        if(!existsCurrencyByIdAndActive){
            return new Result("Such currency id not exist!", false);
        }
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        Currency currency1 = optionalCurrency.get();
        boolean existsCurrencyByNameAndActive = currencyRepository.existsCurrencyByNameAndActive(currency.getName(), true);
        if(existsCurrencyByNameAndActive){
            return new Result("Such currency already exist!", false);
        }
        currency1.setName(currency.getName());
        currencyRepository.save(currency1);
        return new Result("Given currency successfully edited.", true);
    }


    public Result delete(Integer id){
        boolean existsCurrencyByIdAndActive =  currencyRepository.existsCurrencyByIdAndActive(id, true);
        if(!existsCurrencyByIdAndActive){
            return new Result("Such currency id not exist!", false);
        }
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        Currency currency = optionalCurrency.get();
        currency.setActive(false);
        currencyRepository.save(currency);
    return new Result("Given currency successfully deleted.", true);
    }



}
