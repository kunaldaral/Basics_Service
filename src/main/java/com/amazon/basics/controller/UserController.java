package com.amazon.basics.controller;

import com.amazon.basics.controller.security.DuplicateCode;
import com.amazon.basics.controller.security.Encryption;
import com.amazon.basics.controller.security.Injection;
import com.amazon.basics.controller.security.NullPointer;
import com.amazon.basics.domain.User;
import com.amazon.basics.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/users/{id}")
    String getUser(@PathVariable Long id, @RequestParam String value) throws Exception{
        System.out.println("id: "+id+", value: "+value);
        Optional<User> optional = repository.findById(id);
        if (optional.isPresent()) {

            try{

                System.out.println("Invoking null Pointer check");
                NullPointer np = new NullPointer();
                System.out.println(np.checkNullPointer(optional.get()));

                System.out.println("Invoking duplicate code");
                DuplicateCode dc = new DuplicateCode();
                System.out.println(dc.checkDuplicateCode());

                System.out.println("Invoking Encryption checks");
                Encryption ey = new Encryption();
                System.out.println(ey.checkRiskyEncryptionMD5(value));
                System.out.println(ey.checkRiskyEncryptionSHA1(value));
                System.out.println(ey.checkSocketEncryption());

                System.out.println("Invoking Injections");
                Injection ij = new Injection();
                System.out.println(ij.checkCommandInjection(value));
                System.out.println(ij.checkScriptEngineInjection(value));



            }catch (Exception e){

            }

        } else {
//            throw new UserNotFoundException(id);
        }


        return "";
    }

    @GetMapping("/users")
    List<User> getUsers() {
        System.out.println("Getting Users");
        return repository.findAll();
    }

    @PostMapping("/users")
    User createUser(@RequestBody User user) {
        System.out.println("Creating User");
        return repository.save(user);
    }

    @PostMapping("/users/search")
    List<User> searchUser(@RequestBody User user) {
        System.out.println("Searching User");

        if(user != null) {
            if(user.getFirstName() != null) {
                return repository.searchByName(user.getFirstName());
            }
        }

        return Collections.emptyList();
    }
}

