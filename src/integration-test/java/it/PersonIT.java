package it;

import static org.junit.Assert.assertNotEquals;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import DAL.PersonRepository;
import model.Person;

@Category(IntegrationTest.class)
public class PersonIT extends Mockito {

	@Test
	public void PersonServlet_Post_IT() throws ServletException, IOException {

		Person person = new Person();
		person.setName("itTestName");
		person.setEmail("itTestEmail");
		PersonRepository repo = new PersonRepository("lab1TestDB");
		person = repo.createOrUpdate(person);
		assertNotEquals(-1, person.getId());
		
	}
}