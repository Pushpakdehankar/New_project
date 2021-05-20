package db;

import db.Emp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpRepo extends JpaRepository<Emp, Integer> {
    List<Emp> findByName(String name);
}
