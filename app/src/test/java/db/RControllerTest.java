package db;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RControllerTest extends AbstractTest {

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    @Test
    public void findAllTest() throws Exception {
        String uri = "/findAll";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Emp[] emplist = super.mapFromJson(content, Emp[].class);
        assertTrue(emplist.length >= 0);
    }

    @Test
    public void insertTest() throws Exception {
        String uri = "/insert";
        Emp emp1=new Emp(2,"AA",20,2);
        String inputJson = super.mapToJson(emp1);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Inserted");
    }

    @Test
    public void deleteTest() throws Exception {
        String uri = "/delete/2";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Record Deleted");
    }

    @Test
    public void deleteTest2() throws Exception {
        String uri = "/delete/20";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.contains("Could not find employee"));
    }

    @Test
    public void updateTest2() throws Exception {
        String uri = "/update/6";
        Emp emp1=new Emp(6,"BB",27,5);
        String inputJson = super.mapToJson(emp1);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.contains("Could not find employee"));
    }

    @Test
    public void updateTest() throws Exception {
        String uri = "/update/3";
        Emp emp1=new Emp(3,"BB",27,5);
        String inputJson = super.mapToJson(emp1);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Inderted");
    }
}

