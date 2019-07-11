package com.amazon.basics.controller;

import com.amazon.basics.controller.security.crypto.Encryption;
import com.amazon.basics.controller.security.crypto.PredictableRandom;
import com.amazon.basics.controller.general.DuplicateCode;
import com.amazon.basics.controller.general.NullPointer;
import com.amazon.basics.controller.security.improperConfig.ImproperConfiguration;
import com.amazon.basics.controller.security.improperUse.ImproperUse;
import com.amazon.basics.controller.security.injection.Injection;
import com.amazon.basics.controller.security.injection.PathTraversal;
import com.amazon.basics.controller.security.inputSanitization.InputSanitization;
import com.amazon.basics.controller.security.privacy.Privacy;
import com.amazon.basics.controller.security.weakLibraries.WeakLibraries;
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

                //General
                //Duplicate Code
                System.out.println("Invoking duplicate code");
                DuplicateCode dc = new DuplicateCode();
                System.out.println(dc.checkDuplicateCode());
                //Null Pointer check
                System.out.println("Invoking null Pointer check");
                NullPointer np = new NullPointer();
                System.out.println(np.checkNullPointer(optional.get()));

                // Crypto
                // PredictableRandom
                System.out.println("Invoking Predictable Random Number generator check");
                PredictableRandom pr = new PredictableRandom();
                System.out.println(pr.checkWeakRandomGenerator());
                // Encryption
                System.out.println("Invoking encryption checks");
                Encryption ey = new Encryption();
                System.out.println(ey.checkRiskyEncryptionMD5(value));
                System.out.println(ey.checkRiskyEncryptionSHA1(value));
                System.out.println(ey.checkSocketEncryption());
                System.out.println(ey.checkUseOfDESCipher(value));
                System.out.println(ey.checkUseOfDESedeCipher(value));
                System.out.println(ey.checkCipherPadding(value));
                System.out.println(ey.checkCipherMode(value));
                System.out.println(ey.checkCipherIntegrity(value));
                System.out.println(ey.checkSmallBlowfishKey(value));
                System.out.println(ey.checkSmallRSAKeySize(value));

                //ImproperConfig
                //ImproperConfiguration
                System.out.println("Invoking Improper Configuration functions");
                ImproperConfiguration ic = new ImproperConfiguration();
                //Not sure how to use AllHostss
                System.out.println(ic.checkXEEAttack());

                //improperUse
                //ImproperUse
                System.out.println("Invoking Improper Use functions");
                ImproperUse iu = new ImproperUse();
                System.out.println(iu.checkBadHexaDecimalCOncatenation(value));
                System.out.println(iu.checkUnsafeHashEquals(value));

                //Injection
                //PathTraversal
                System.out.println("Invoking Path Traversal Injections");
                PathTraversal pt = new PathTraversal();
                System.out.println(pt.checkFileWrite(value));
                System.out.println(pt.checkFileRead(value));
                //Injection
                System.out.println("Invoking Injections");
                Injection ij = new Injection();
                System.out.println(ij.checkCommandInjection(value));
                System.out.println(ij.checkScriptEngineInjection(value));
                System.out.println(ij.checkSQLQueryInjection(repository, value));


                //inputSanitization
                //InputSanitization
                System.out.println("Invoking Input Sanitization functions");
                InputSanitization is = new InputSanitization();
                System.out.println(is.checkUnsanitizedInputFromBufferedReader());

                //privacy
                //Privacy
                System.out.println("Invoking Privacy functions");
                Privacy py = new Privacy();
                System.out.println(py.checkCookieSecurity(value));

                //weakLibraries
                //WeakLibraries
                System.out.println("Invoking weak library functions");
                WeakLibraries wl = new WeakLibraries();
                System.out.println(wl.checkHardCodedKey(value));
                System.out.println(wl.checkHardCodedPasswordSetting(value));
                System.out.println(wl.checkUseOfNullCipher(value));

            }catch (Exception e){
                return "";
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

    @RequestMapping(value = "/path") //method = RequestMethod.GET - Purposely avoided, method now available
                                     // to all calls POST, GET etc. (Can lead to CSRF attacks)
    String readData() {
        // No state-changing operations performed within this method.
        return "";
    }

    @RequestMapping("/redirect")
    public String redirect(@RequestParam("url") String url) {
        // Redirection without validating user entered URL
        return "redirect:" + url;
    }

}

