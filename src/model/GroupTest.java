package model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class GroupTest {
	@Test
	public void testGetGrId(){
		Group.resetCurrentId();
		Group group = new Group();
		assertEquals(0,group.getGrId());
	}
	@Test
	public void testGetGrIdString(){
		Group.resetCurrentId();
		Group group = new Group();
		assertEquals(String.valueOf(0),group.getGrIdString());
	}
	@Test
	public void testGetGrName(){
		Group.resetCurrentId();
		Group group = new Group();
		assertEquals("UnknownGroup0",group.getGrName());
	}
	@Test
	public void testSetGrName(){
		Group.resetCurrentId();
		Group group = new Group();
		group.setGrName("KnownGroup0");
		assertEquals("KnownGroup0",group.getGrName());
	}
	@Test
	public void testGetIsPublic(){
		Group.resetCurrentId();
		Group group = new Group();
		assertEquals(true,group.getIsPublic());
	}
	@Test
	public void testSetIsPublic(){
		Group.resetCurrentId();
		Group group = new Group();
		group.setIsPublic(false);
		assertEquals(false,group.getIsPublic());
	}
	@Test
	public void testGetMembers(){
		Group.resetCurrentId();
		Group group = new Group();
		assertEquals(new ArrayList<String>(),group.getMembers());
	}
}
