package pdp.uz.program_41.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.program_41.entity.Client;
import pdp.uz.program_41.payload.Result;
import pdp.uz.program_41.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping
    public Result add(@RequestBody Client client){
        return clientService.add(client);
    }
    @GetMapping
    public Result get(@RequestParam int page){
        return clientService.get(page);
    }
    @GetMapping("/{id}")
public Result getById(@PathVariable Integer id){
        return clientService.getById(id);
    }
    @PutMapping
    public Result edit(@PathVariable Integer id, @RequestBody Client client){
        return clientService.edit(id, client);
    }
    @DeleteMapping
    public Result delete(@PathVariable Integer id){
        return clientService.delete(id);
    }
}
