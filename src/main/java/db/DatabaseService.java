package db;

import java.util.List;

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

    public Boolean update(Integer id, Emp emp) {
     if (repo.existsById(id) == true) {
        repo.save(emp);
        return true;
    } else
        return false;
  }
    public Boolean delete(Integer id) {
        if (repo.existsById(id) == true) {
            repo.deleteById(id);
            return true;
        } else
            return false;
    }
}