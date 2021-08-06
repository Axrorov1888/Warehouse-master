package pdp.uz.program_41.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.program_41.payload.InputProductDto;
import pdp.uz.program_41.payload.Result;
import pdp.uz.program_41.service.InputProductService;

@RestController
@RequestMapping("/inputProduct")
public class InputProductController {

    @Autowired
    InputProductService inputProductService;

    @PostMapping
    public Result add(@RequestBody InputProductDto inputProductDto) {
        return inputProductService.add(inputProductDto);
    }

    @GetMapping
    public Result get(@RequestParam int page) {
        return inputProductService.get(page);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        return inputProductService.getById(id);
    }

    @GetMapping("/{inputId}")
    public Result getByInputId(@PathVariable Integer inputId, @RequestParam int page) {
        return inputProductService.getByInputId(inputId, page);
    }

    @PutMapping("/{id")
    public Result edit(@PathVariable Integer id, @RequestBody InputProductDto inputProductDto) {
        return inputProductService.edit(id, inputProductDto);
    }

}
