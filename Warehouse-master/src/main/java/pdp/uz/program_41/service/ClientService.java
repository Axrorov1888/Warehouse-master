package pdp.uz.program_41.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pdp.uz.program_41.entity.Client;
import pdp.uz.program_41.payload.Result;
import pdp.uz.program_41.repository.ClientRepository;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public Result add(Client client){
        boolean existsClientByPhoneNumberAndActive = clientRepository.existsClientByPhoneNumberAndActive(client.getPhoneNumber(), true);
        if(existsClientByPhoneNumberAndActive){
            return new Result("Such phone number of client already exist!", false);
        }
        Client client1 = new Client();
        client1.setName(client.getName());
        client1.setPhoneNumber(client.getPhoneNumber());
        clientRepository.save(client1);
        return new Result("New Client successfully saved.",true);
    }

    public Result get(int page){
        boolean existsClientByActive = clientRepository.existsClientByActive(true);
        if(existsClientByActive){
            Pageable pageable = PageRequest.of(page, 10);
            Page<Client> page1 = clientRepository.getClientByActive(true, pageable);
            return new Result(page1);
        }
        return new Result("Clients not exists yet!",false);
    }

    public Result getById(Integer id){
        boolean existsClientByIdAndActive = clientRepository.existsClientByIdAndActive(id, true);
    if(!existsClientByIdAndActive){
        return new Result("Such client id not exist!", false);
    }
    Optional<Client> optionalClient = clientRepository.findById(id);
    return new Result(optionalClient.get());
    }

    public Result edit(Integer id, Client client){
        boolean existsClientByIdAndActive = clientRepository.existsClientByIdAndActive(id, true);
        if(!existsClientByIdAndActive){
            return new Result("Such client id not exists!", false);
        }
        Optional<Client> optionalClient = clientRepository.findById(id);
        Client client1 = optionalClient.get();
        boolean existsClientByPhoneNumberAndActive = clientRepository.existsClientByPhoneNumberAndActive(client.getPhoneNumber(), true);
        if(existsClientByPhoneNumberAndActive && !client1.getPhoneNumber().equals(client.getPhoneNumber())){
            return new Result("Such phone number of client already exist!",false);
        }
        client1.setName(client.getName());
        client1.setPhoneNumber(client.getPhoneNumber());
        clientRepository.save(client1);
        return new Result("Given client successfully edited.", true);
    }

    public Result delete(Integer id){
        boolean existsClientByIdAndActive = clientRepository.existsClientByIdAndActive(id, true);
        if(!existsClientByIdAndActive){
            return new Result("Such client id not exist!", false);
        }
        Optional<Client> optionalClient = clientRepository.findById(id);
        Client client = optionalClient.get();
        client.setActive(false);
        clientRepository.save(client);
        return new Result("Given client successfully deleted.", true);
    }

}
