package br.com.wagner.controllers;

// import java.util.logging.Logger; // ERRO: Type mismatch com LoggerFactory
import org.slf4j.Logger; // Corrigido: usar org.slf4j.Logger
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wagner.PersonServices;

@RestController
public class TestLogController {

     private Logger logger = LoggerFactory.getLogger(TestLogController.class.getName());



    @GetMapping("/test")
    public String testLog(){

        logger.debug("This is an DEBUG log");
        logger.info("This is an INFO log");
        logger.warn("This is an WARN log");
        // logger.ERROR("This is an ERROR log"); // ERRO: método ERROR não existe
        logger.error("This is an ERROR log"); // Corrigido: usar error() minúsculo
        
        return "Logs generated successfully";
    }
}