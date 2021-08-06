package pdp.uz.program_41.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.program_41.payload.OutputProductDto;
import pdp.uz.program_41.payload.Result;
import pdp.uz.program_41.service.OutputProductService;

@RestController
@RequestMapping("/outputProduct")
public class OutputProductController {

    @Autowired
    OutputProductService outputProductService;

    @PostMapping
    public Result add(@RequestBody OutputProductDto outputProductDto) {
        return outputProductService.add(outputProductDto);
    }

    @GetMapping
    public Result get(@RequestParam int page) {
        return outputProductService.get(page);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        return outputProductService.getById(id);
    }

    @GetMapping("/{outputId}")
    public Result getByOutputId(@PathVariable Integer outputId, @RequestParam int page) {
        return outputProductService.getByOutputId(outputId, page);
    }

    @PutMapping("/{Id}")
    public Result edit(@PathVariable Integer id, @RequestBody OutputProductDto outputProductDto) {
        return outputProductService.edit(id, outputProductDto);
    }


}
