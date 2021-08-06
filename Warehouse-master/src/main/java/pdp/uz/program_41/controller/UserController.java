package pdp.uz.program_41.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.program_41.payload.Result;
import pdp.uz.program_41.payload.UserDto;
import pdp.uz.program_41.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping
    public Result add(@RequestBody UserDto userDto) {
        return userService.add(userDto);
    }

    @GetMapping
    public Result get(@RequestParam int page) {
        return userService.get(page);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody UserDto userDto) {
        return userService.edit(id, userDto);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return userService.delete(id);
    }
}
