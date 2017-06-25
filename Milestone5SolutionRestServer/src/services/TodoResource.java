package services;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import testPckg.Employee;
import testPckg.Todo;

@Path("todo")
public class TodoResource {
    @GET
    @Produces({MediaType.TEXT_XML})
    public Todo getString()
    {
    	Todo t=new Todo();
    	t.setDescription("IS VERY GOOD");
    	ArrayList<Employee> newE=new ArrayList<>();
    	Employee s=new Employee();
    	s.setName("EYY ITS A ME");
    	newE.add(s);
    	return t;
    }

}