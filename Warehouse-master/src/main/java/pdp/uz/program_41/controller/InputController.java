package pdp.uz.program_41.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.program_41.payload.InputDto;
import pdp.uz.program_41.payload.Result;
import pdp.uz.program_41.service.InputService;

@RestController
@RequestMapping("/input")
public class InputController {

    @Autowired
    InputService inputService;

@PostMapping
    public Result add(InputDto inputDto){
    return inputService.add(inputDto);
}
@GetMapping
    public Result get(@RequestParam int page){
    return inputService.get(page);
}
@GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
    return inputService.getById(id);
}
@PutMapping("/{id}")
public Result edit(@PathVariable Integer id, @RequestBody InputDto inputDto){
    return inputService.edit(id, inputDto);
}
}
