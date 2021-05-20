package db;

import db.DatabaseService;
import db.Emp;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class RController {

    @Autowired

    DatabaseService db;

    @GetMapping ("/home")

    public String home() { return "hello from the server";}

        @ResponseStatus(HttpStatus.OK)

        @GetMapping("/findAll")

        @ResponseBody Iterable<Emp> findall() {return db.FindAll();}

        @GetMapping("/findByName/{name}")


    List<Emp> findbyname (@PathVariable String name) { return db.findByName(name);}

    @GetMapping("/findById/{id}")

    Emp findbyid(@PathVariable Integer id) { return db.findById(id); }

    @PostMapping("/insert")
    String insert(@RequestBody Emp emp) {
        db.insert(emp);
        return "Inserted";
    }

    @PutMapping("/update/{id}")
    String update(@PathVariable Integer id,@RequestBody Emp emp){
        Boolean stat=db.update(id ,emp);
                if (stat)
                    return "Record updated";
                else
        return null;
    }
    @DeleteMapping("/delete/{id}")
    String delete(@PathVariable Integer id){
        Boolean stat=db.delete(id);
        if(stat)
            return "Record Deleted";
        else
            return null;

    }


}