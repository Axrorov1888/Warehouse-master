package pdp.uz.program_41.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.program_41.entity.Currency;
import pdp.uz.program_41.payload.Result;
import pdp.uz.program_41.service.CurrencyService;

@RestController
@RequestMapping("/currency")
public class CurrencyController {
@Autowired
CurrencyService currencyService;

@PostMapping
    public Result add(@RequestBody Currency currency){
    return currencyService.add(currency);
}
@GetMapping
    public Result get(@RequestParam int page){
    return currencyService.get(page);
}
@GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
    return currencyService.getById(id);
}
@PutMapping("/{id}")
public Result edit(@PathVariable Integer id, @RequestBody Currency currency){
    return currencyService.edit(id, currency);
}
@DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
    return currencyService.delete(id);
}
}
