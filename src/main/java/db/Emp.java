package db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="Employees")
@Entity
public class Emp
{
    private @Id int id;
    private @Column String name;
    private @Column int age;
    private @Column int expyr;


    public Emp()
    {
    }
    public Emp(int id,String name,int age ,int expyr)
    {   this.id=id;
        this.name=name;
        this.age=age;
        this.expyr=expyr;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getExpyr() {
        return expyr;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setExpyr(int expyr) {
        this.expyr = expyr;
    }

    public void setAge(int age) {
        this.age = age;
    }




}
