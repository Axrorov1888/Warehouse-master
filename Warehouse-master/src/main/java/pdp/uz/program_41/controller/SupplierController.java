package pdp.uz.program_41.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.program_41.entity.Supplier;
import pdp.uz.program_41.payload.Result;
import pdp.uz.program_41.service.SupplierService;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
@Autowired
SupplierService supplierService;

@PostMapping
    public Result add(@RequestBody Supplier supplier){
    return supplierService.add(supplier);
}
@GetMapping
    public Result get(@RequestParam int page){
    return supplierService.get(page);
}
@GetMapping("/{id}")
public Result getById(@PathVariable Integer id){
    return supplierService.getById(id);
}
@PutMapping("/{id}")
public Result edit(@PathVariable Integer id, @RequestBody Supplier supplier){
    return supplierService.edit(id, supplier);
}
@DeleteMapping("/{id}")
public Result delete(@PathVariable Integer id){
    return supplierService.delete(id);
}

}
