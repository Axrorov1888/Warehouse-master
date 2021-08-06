package pdp.uz.program_41.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.program_41.entity.Warehouse;
import pdp.uz.program_41.payload.Result;
import pdp.uz.program_41.service.WarehouseService;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
@Autowired
WarehouseService warehouseService;

@PostMapping
    public Result add(@RequestBody Warehouse warehouse){
    return warehouseService.add(warehouse);
}
@GetMapping
    public Result get(@RequestParam int page){
    return warehouseService.get(page);
}
@GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
    return warehouseService.getById(id);
}
@PutMapping("/{id}")
public Result edit(@PathVariable Integer id, @RequestBody Warehouse warehouse){
    return warehouseService.edit(id, warehouse);
}
@DeleteMapping("/{id}")
public Result delete(@PathVariable Integer id){
    return warehouseService.delete(id);
}
}

