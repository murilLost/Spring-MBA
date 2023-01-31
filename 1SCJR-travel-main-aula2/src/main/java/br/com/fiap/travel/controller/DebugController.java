package br.com.fiap.travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("debug")
@ConditionalOnProperty(value = "server.debug", havingValue = "true") //só cria o Bean, se no application.yml tiver o server.debug como true, senão não cria.
public class DebugController {

    @Autowired
    private Environment env;

    @GetMapping
    public String getEnv(@RequestParam String key){
        return env.getProperty(key);//Busca no contexto, algum atributo com a key
    }

}
