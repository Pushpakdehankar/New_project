package db;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="Employees")
@Entity
@ApiModel(description="Details about Employees ")
@JsonPropertyOrder({"id","name","age","expyr"})

public @Data
class Emp
{
    @ApiModelProperty(notes="The Unique Id of Employee")
    @JsonProperty(value="Emp_Id")
    private @Id int id;

    @ApiModelProperty(notes="The Unique name of Employee")
    private @Column String name;

    @ApiModelProperty(notes="The age of Employee")
    private @Column int age;

    @ApiModelProperty(notes="The years of experience of Employee")
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
