package com.amazon.basics.controller.security;

import com.amazon.basics.repository.UserRepository;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;

public class Injection {

    public String checkCommandInjection(String image){
        File file = new File("resources/images/", image); //Weak point
        return("Unchecked command executed");
    }

    public String checkScriptEngineInjection(String script) throws ScriptException {
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("JavaScript");
        engine.eval(script); //Bad things can happen here.
        return("User entered and unsanitized script evaluated!");
    }

    public String checkSQLQueryInjection(UserRepository repository, String firstName) {
        repository.searchByName(firstName);
        return "Unsanitized user input passed to SQL Query";
    }

}
