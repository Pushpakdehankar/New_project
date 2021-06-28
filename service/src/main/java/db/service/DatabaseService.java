package db.service;

import java.util.List;

import db.ErrorHandlers.EmployeeNotFoundException;
import db.Emp;
import db.EmpRepo;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.*;
@Service

public class DatabaseService
{


    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseService.class);

    private static final String MAIN_SERVICE = "mainService";

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();

    }

    @CircuitBreaker(name = MAIN_SERVICE, fallbackMethod = "fallbackCircuit")
    @RateLimiter(name = MAIN_SERVICE)
    public ResponseEntity<String> circuitTest(){
        LOGGER.info("I'm here in main service to test circuit break");
        String response = restTemplate.getForObject("http://localhost:8082/home", String.class);
        return new ResponseEntity<String>(response, HttpStatus.OK);

    }
    public ResponseEntity<String> fallbackCircuit(Throwable t){
        LOGGER.error("Inside fallback because: "+t.toString());
        return new ResponseEntity<String>("fallback method",HttpStatus.TOO_MANY_REQUESTS);
    }

    @Autowired
        EmpRepo repo;

        public void insert(Emp emp) {repo.save(emp); }

     public Iterable<Emp> FindAll()
     { return repo.findAll(); }

    public List<Emp> findByName(String name) {return repo.findByName(name); }

    public Emp findById(Integer id) {return repo.findById(id).get(); }

    public String update(Integer id, Emp emp) {
     if (repo.existsById(id) == true) {
        repo.save(emp);
         return "Inderted";
    } else
         throw new EmployeeNotFoundException("Could not find employee {id:"+id+"}so record could not be inserted ");

  }
    public String delete(Integer id) {
        if (repo.existsById(id) == true) {
            repo.deleteById(id);
            return "Record Deleted";
        }   else
           throw new EmployeeNotFoundException("Could not find employee {id:"+id+"}so record could not be deleted ");

    }


}