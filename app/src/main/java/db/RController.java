package db;

import db.ErrorHandlers.EmployeeNotFoundException;
//import service.DatabaseService;
//import emp.Emp;
import db.Emp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;
import db.service.DatabaseService;

import java.util.List;

@Api(value = "Endpoints for Employee's Details ",description ="Endpoints for Employee's Details ")
@RestController

public class RController {

    @Autowired
    DatabaseService db;

    @GetMapping("/Circuittester")
    public ResponseEntity<String> circuitTest() {
        return db.circuitTest();
    }

    @GetMapping ("/home")
    public String home() { return "hello from the server";}

        @ResponseStatus(HttpStatus.OK)
        @GetMapping("/findAll")
        @ResponseBody Iterable<Emp> findall() {return db.FindAll();}


    @ApiOperation(value = "Gives details of all Employees by their names",
    notes = "List of Employee with that  name", response = Emp.class,responseContainer ="List")

    @ApiResponses(value = {
            @ApiResponse(code =500, message ="Server error"),
            @ApiResponse (code =200, message= "Successful retrieval")})
        @GetMapping("/findByName/{name}")
        List<Emp> findbyname (@PathVariable String name) { return db.findByName(name);}


    @ApiOperation(value = "Gives details of all Employees by their Unique Id",
            notes = " Employee with that Unique Id", response = Emp.class)

    @ApiResponses (value = {
            @ApiResponse (code =500, message ="Server error"),
            @ApiResponse (code =200, message= "Successful retrieval")})
    @GetMapping("/findById/{id}")
    Emp findbyid(@PathVariable Integer id) { return db.findById(id); }

    @PostMapping("/insert")
    String insert(@RequestBody Emp emp) {
        db.insert(emp);
        return "Inserted";
    }

    @PutMapping("/update/{id}")
    String update(@PathVariable Integer id,@RequestBody Emp emp) throws EmployeeNotFoundException{
       // Boolean stat=db.update(id ,emp);
         //       if (stat)
           //         return "Record updated";
             //   else
               //     throw new EmployeeNotFoundException("Could not find employee{id:"+id+"}so record could not be updated");
              return db.update(id,emp);

    }
    @DeleteMapping("/delete/{id}")
    String delete(@PathVariable Integer id) throws EmployeeNotFoundException{
       // Boolean stat=db.delete(id);
        //if(stat)
          //  return "Record Deleted";
       // else
         //   throw new EmployeeNotFoundException("Could not find employee {id:"+id+"}so record could not be deleted ");
     return db.delete(id);
    }


}