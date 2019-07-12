package com.errors.basics.controller.security.injection;

import com.errors.basics.repository.UserRepository;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;

public class Injection {

    public String checkCommandInjection(String image){
        new File("resources/images/", image); //Weak point
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
//    SqlUtil.execQuery("select * from UserEntity t where id = " + parameterInput);
//        Session session = factory.openSession();
//        createQuery("select * from User where id = '"+inputId+"'");
        return "Unsanitized user input passed to SQL Query";
    }

}
