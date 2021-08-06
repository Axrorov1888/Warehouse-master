package pdp.uz.program_41.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pdp.uz.program_41.entity.Warehouse;
import pdp.uz.program_41.payload.Result;
import pdp.uz.program_41.repository.WarehouseRepository;

import java.util.Optional;

@Service
public class WarehouseService {

    @Autowired
    WarehouseRepository warehouseRepository;

    public Result add(Warehouse warehouse){
        boolean existsWarehouseByNameAndActive = warehouseRepository.existsWarehouseByNameAndActive(warehouse.getName(), true);
       if(existsWarehouseByNameAndActive){
           return new Result("Such warehouse already exist!", false);
       }
       Warehouse warehouse1 = new Warehouse();
       warehouse1.setName(warehouse.getName());
       warehouseRepository.save(warehouse1);
       return new Result("New warehouse successfully saved.", true);
    }

    public Result get(int page){
        boolean existsWarehouseByActive = warehouseRepository.existsWarehouseByActive(true);
        if(!existsWarehouseByActive){
            return new Result("Warehouses not exist yet!", false);
        }
        Pageable pageable = PageRequest.of(page,10);
        Page<Warehouse> page1 = warehouseRepository.getWarehousesByActive(true, pageable);
        return new Result(page1);
    }

    public Result getById(Integer id){
        boolean existsWarehouseByIdAndActive = warehouseRepository.existsWarehouseByIdAndActive(id, true);
   if(!existsWarehouseByIdAndActive){
       return new Result("Such warehouse id not exist!", false);
   }
   Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
   return new Result(optionalWarehouse.get());
    }

    public Result edit(Integer id, Warehouse warehouse){
        boolean existsWarehouseByIdAndActive = warehouseRepository.existsWarehouseByIdAndActive(id, true);
        if(!existsWarehouseByIdAndActive){
            return new Result("Such warehouse id not exist!", false);
        }
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        Warehouse warehouse1 = optionalWarehouse.get();
        boolean existsWarehouseByNameAndActive = warehouseRepository.existsWarehouseByNameAndActive(warehouse.getName(), true);
        if(existsWarehouseByNameAndActive){
            return new Result("Such warehouse already exist!",false);
        }
        warehouse1.setName(warehouse.getName());
        warehouseRepository.save(warehouse1);
        return new Result("Given warehouse successfully edited.", true);
    }

    public Result delete(Integer id){
        boolean existsWarehouseByIdAndActive = warehouseRepository.existsWarehouseByIdAndActive(id, true);
                if(!existsWarehouseByIdAndActive){
                    return new Result("Such warehouse id not exist!", false);
                }
                Optional<Warehouse> optionalWarehouse= warehouseRepository.findById(id);
                Warehouse warehouse = optionalWarehouse.get();
                warehouse.setActive(false);
                warehouseRepository.save(warehouse);
                return new Result("Given warehouse successfully deleted.", true);
    }
}
