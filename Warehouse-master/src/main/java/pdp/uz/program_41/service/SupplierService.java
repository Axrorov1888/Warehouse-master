package pdp.uz.program_41.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pdp.uz.program_41.entity.Supplier;
import pdp.uz.program_41.payload.Result;
import pdp.uz.program_41.repository.SupplierRepository;

import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    public Result add(Supplier supplier){
        boolean existsSupplierByPhoneNumberAndActive = supplierRepository.existsSupplierByPhoneNumberAndActive(supplier.getPhoneNumber(), true);
  if(existsSupplierByPhoneNumberAndActive){
      return new Result("Such phone number of supplier already exist!", false);
  }
  Supplier supplier1 = new Supplier();
  supplier1.setName(supplier.getName());
  supplier1.setPhoneNumber(supplier.getPhoneNumber());
  supplierRepository.save(supplier);
  return new Result("New supplier successfully saved", true);
    }

    public Result get(int page){
        boolean existsSupplierByActive = supplierRepository.existsSupplierByActive(true);
        if(existsSupplierByActive){
            Pageable pageable = PageRequest.of(page,  10);
            Page<Supplier> page1 = supplierRepository.getSupplierByActive(true, pageable);
            return new Result(page1);
        }
        return new Result("Suppliers not exist yet!", false);
    }

    public Result getById(Integer id){
        boolean existsSupplierByIdAndActive = supplierRepository.existsSupplierByIdAndActive(id, true);
        if(!existsSupplierByIdAndActive){
            return new Result("Such supplier id not exist!", false);
        }
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        return new Result(optionalSupplier.get());
    }

    public Result edit(Integer id, Supplier supplier){
        boolean existsSupplierByIdAndActive = supplierRepository.existsSupplierByIdAndActive(id, true);
        if(!existsSupplierByIdAndActive){
            return new Result("Such supplier id not exist!", false);
        }
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        Supplier supplier1 = optionalSupplier.get();
        boolean existsSupplierByPhoneNumberAndActive = supplierRepository.existsSupplierByPhoneNumberAndActive(supplier.getPhoneNumber(), true);
        if(!existsSupplierByPhoneNumberAndActive || supplier1.getPhoneNumber().equals(supplier.getPhoneNumber())){
            supplier1.setName(supplier.getName());
            supplier1.setPhoneNumber(supplier.getPhoneNumber());
            supplierRepository.save(supplier1);
            return new Result("Given supplier successfully edited.", true);
        }
        return new Result("Such phone number of supplier already exist!", false);
    }

    public Result delete(Integer id){
        boolean existsSupplierByIdAndActive = supplierRepository.existsSupplierByIdAndActive(id, true);
        if(!existsSupplierByIdAndActive){
            return new Result("Such supplier id not exist!", false);
        }
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        Supplier supplier = optionalSupplier.get();
        supplier.setActive(false);
        supplierRepository.save(supplier);
    return new Result("Given supplier successfully deleted.", true);
    }


}
