package pdp.uz.program_41.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.program_41.payload.OutputDto;
import pdp.uz.program_41.payload.Result;
import pdp.uz.program_41.service.OutputService;

@RestController
@RequestMapping("/output")
public class OutputController {

    @Autowired
    OutputService outputService;

@PostMapping
    public Result add(@RequestBody OutputDto outputDto){
    return outputService.add(outputDto);
}
@GetMapping
    public Result get(@RequestParam int page){
    return outputService.get(page);
}
@GetMapping("/{id}")
public Result getById(@PathVariable Integer id){
    return outputService.getById(id);
}
@PutMapping("/{id}")
public Result edit(@PathVariable Integer id, @RequestBody OutputDto outputDto){
    return outputService.edit(id, outputDto);
}
}
