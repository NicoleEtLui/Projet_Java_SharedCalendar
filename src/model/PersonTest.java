package model;

import static org.junit.Assert.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

public class PersonTest {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	@Test
	public void testGetName(){
		Person person = new Person("name","firstName","userName",LocalDate.now());
		assertEquals("name",person.getName());
	}
	@Test
	public void testSetName(){
		Person person = new Person("name","firstName","userName",LocalDate.now());
		person.setName("new name");
		assertEquals("new name",person.getName());
	}
	@Test
	public void testGetFirstName(){
		Person person = new Person("name","firstName","userName",LocalDate.now());
		assertEquals("firstName",person.getFirstName());
	}
	@Test
	public void testSetFirstName(){
		Person person = new Person("name","firstName","userName",LocalDate.now());
		person.setFirstName("new firstName");
		assertEquals("new firstName",person.getFirstName());
	}
	@Test
	public void testGetUserName(){
		Person person = new Person("name","firstName","userName",LocalDate.now());
		assertEquals("userName",person.getUserName());
	}
	@Test
	public void testGetBDate(){
		Person person = new Person("name","firstName","userName",LocalDate.now());
		assertEquals(LocalDate.now().format(formatter),person.getbDate());
	}
	@Test
	public void testSetBDate(){
		Person person = new Person("name","firstName","userName",LocalDate.now());
		person.setbDate(LocalDate.now().plusDays(1));
		assertEquals(LocalDate.now().plusDays(1).format(formatter),person.getbDate());
	}
}
