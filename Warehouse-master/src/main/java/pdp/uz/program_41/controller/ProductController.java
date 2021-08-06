package pdp.uz.program_41.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.program_41.payload.ProductDto;
import pdp.uz.program_41.payload.Result;
import pdp.uz.program_41.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
@Autowired
ProductService productService;

@PostMapping
    public Result add(@RequestBody ProductDto productDto){
    return productService.add(productDto);
}
@GetMapping
    public Result get(@RequestParam int page){
    return productService.get(page);
}
@GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
    return productService.getById(id);
}
@PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody ProductDto productDto){
    return productService.edit(id, productDto);
}
@DeleteMapping("/{id}")
public Result delete(@PathVariable Integer id){
    return productService.delete(id);
}
}
