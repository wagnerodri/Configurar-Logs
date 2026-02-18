package br.com.wagner;

import br.com.wagner.exception.ResourceNotFoundExcepetion;
import br.com.wagner.model.Person;
import br.com.wagner.repositories.PersonRepository;

import org.slf4j.Logger; // Corrigido: usar org.slf4j.Logger
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
// import java.util.logging.Logger; // ERRO: Type mismatch com LoggerFactory

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    @Autowired 
    PersonRepository repository;



    public List<Person> findAll (){
        logger.info("Finding all people!");
        return repository.findAll();
    }


    public Person findById (Long id) {
        logger.info("Finding one person");
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExcepetion("No records found for this ID"));
    }


    public Person create (Person person) {
        logger.info("Creating one people!");
        return repository.save(person);
    }
    
    public Person update (Person person) {
        logger.info("Updating one people!");
        
        // Verifica se a pessoa existe antes de atualizar
        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundExcepetion("Person not found with ID: " + person.getId()));
        
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        
        return repository.save(entity);
    }

    public void delete (Long id) {
        logger.info("Deleting one person!");
        
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExcepetion("No records found for this ID"));
        
        repository.delete(entity);
    }
}
