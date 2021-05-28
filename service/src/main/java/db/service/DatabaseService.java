package db.service;

import java.util.List;

import db.ErrorHandlers.EmployeeNotFoundException;
import db.Emp;
import db.EmpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class DatabaseService
{
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