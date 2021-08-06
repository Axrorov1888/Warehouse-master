package pdp.uz.program_41.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.program_41.entity.Measurement;
import pdp.uz.program_41.payload.Result;
import pdp.uz.program_41.service.MeasurementService;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {
@Autowired
    MeasurementService measurementService;

@PostMapping
    public Result add(@RequestBody Measurement measurement){
    return measurementService.add(measurement);
}
@GetMapping
    public Result get(@RequestParam int page){
    return measurementService.get(page);
}
@GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
    return measurementService.getById(id);
}
@PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody Measurement measurement){
    return measurementService.edit(id, measurement);
}
@DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
    return measurementService.delete(id);
}

}
