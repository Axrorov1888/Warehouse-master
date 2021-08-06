package pdp.uz.program_41.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pdp.uz.program_41.entity.User;
import pdp.uz.program_41.entity.Warehouse;
import pdp.uz.program_41.payload.Result;
import pdp.uz.program_41.payload.UserDto;
import pdp.uz.program_41.repository.UserRepository;
import pdp.uz.program_41.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
@Autowired
    WarehouseRepository warehouseRepository;

    public Result add(UserDto userDto){
boolean existsUserByPhoneNumberAndActive = userRepository.existsUserByPhoneNumberAndActive(userDto.getPhoneNumber(), true);
    if(existsUserByPhoneNumberAndActive){
        return new Result("Such phoneNumber of user already exist!", false);
    }
    boolean existsUserByCodeAndActive = userRepository.existsUserByCodeAndActive(userDto.getCode(), true);
    if(existsUserByCodeAndActive){
        return new Result("Such code of user already exist!",false);
    }
        List<Warehouse> warehouseList = warehouseRepository.findAllById(userDto.getWarehousesId());
    User user = new User();
    user.setName(userDto.getName());
    user.setPhoneNumber(userDto.getPhoneNumber());
    user.setCode(userDto.getCode());
    user.setPassword(userDto.getPassword());
    user.setWarehouse(warehouseList);
    userRepository.save(user);
    return new Result("New user successfully saved.", true);
    }

    public Result get(int page){
        boolean existsUserByActive = userRepository.existsUserByActive(true);
    if(existsUserByActive){
        Pageable pageable = PageRequest.of(page, 10);
        Page<User> page1 = userRepository.getUserByActive(true, pageable);
        return new Result(page1);
    }
    return new Result("Users not exist yet!", false);

    }

    public Result getById(Integer id){
        boolean existsUserByIdAndActive = userRepository.existsUserByIdAndActive(id, true);
        if(!existsUserByIdAndActive){
            return new Result("Such user id not exist!", false);
        }
        Optional<User> optionalUser = userRepository.findById(id);
        return new Result(optionalUser.get());
    }

    public Result edit(Integer id, UserDto userDto){
        boolean existsUserByIdAndActive = userRepository.existsUserByIdAndActive(id, true);
        if(!existsUserByIdAndActive){
            return new Result("Such user id not exist!", false);
        }
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.get();
        boolean existsUserByPhoneNumberAndActive = userRepository.existsUserByPhoneNumberAndActive(userDto.getPhoneNumber(), true);
     if(existsUserByPhoneNumberAndActive && !user.getPhoneNumber().equals(userDto.getPhoneNumber())) {
         return new Result("Such phone number of user already exist!", false);
     }
         boolean existsUserByCodeAndActive = userRepository.existsUserByCodeAndActive(userDto.getCode(), true);
if(existsUserByCodeAndActive && !user.getCode().equals(userDto.getCode())) {
    return new Result("Such code of user already exist!", false);
}
List<Warehouse> warehouseList = warehouseRepository.findAllById(userDto.getWarehousesId());
    user.setName(userDto.getName());
    user.setPhoneNumber(userDto.getPhoneNumber());
    user.setCode(userDto.getCode());
    user.setPassword(userDto.getPassword());
    user.setWarehouse(warehouseList);
    userRepository.save(user);
    return new Result("Given user successfully edited.", true);
         }

         public Result delete(Integer id){
        boolean existsUserByIdAndActive = userRepository.existsUserByIdAndActive(id, true);
        if(!existsUserByIdAndActive){
            return new Result("Such user id not exist!", false);
        }
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.get();
        user.setActive(false);
        userRepository.save(user);
        return new Result("Given user successfully deleted.", true);
         }
}
