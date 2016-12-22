package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.mysql.jdbc.ResultSetMetaData;

import model.Person;
import model.ShaCalModel;

public class PersonDAO {

		protected Connection connect = null;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		public PersonDAO(Connection x){
			this.connect = x;
		}
		
		public Person find(String username){
			
			Person pers = null;
			
			try {
				ResultSet rs = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE
				).executeQuery(
					"SELECT * FROM tbPersons WHERE personUserName = '"+username+"'"	
				);
				
				if(rs.first()){
					pers = new Person(rs.getString("personName"),
						rs.getString("personFirstName"),
						rs.getString("personUserName"),
						rs.getString("groups"),
						LocalDate.parse(rs.getString("personBirthday"))
					);
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
			return pers;
		}
		public void findAll(){
						
			try {
				ResultSet rs = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE
				).executeQuery(
					"SELECT * FROM tbPersons"	
				);
				
				ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();
				
				while(rs.next()){
					ShaCalModel.allPersons.put(rs.getString("personUserName"), 
							new Person(rs.getString("personName"),
							rs.getString("personFirstName"),
							rs.getString("personUserName"),
							rs.getString("groups"),
							LocalDate.parse(rs.getString("personBirthday")))
					);
				}
				
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		public Person create(Person x){
			
			try {
				ResultSet rs = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE
				).executeQuery(
					"SELECT personUserName FROM tbPersons"	
				);
				
				if(rs.first()){
					PreparedStatement ps = this.connect.prepareStatement(
						"INSERT INTO tbPersons "
						+ "VALUES (?, ?, ?, ?, ?)"
					);
					
					ps.setString(1, x.getName());
					ps.setString(2, x.getFirstName());
					ps.setString(3, x.getUserName());
					ps.setString(4, x.toStringGroup(x.getGroup()));
					ps.setString(5, x.getbDate());
					
					ps.executeUpdate();
					
					x = this.find(x.getUserName());
				}
				
			} catch(SQLException e){
				e.printStackTrace();
			}
			
			return x;
			
		}
		
		public Person update(Person x){
			
			try{
				this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
				).executeUpdate(
					"UPDATE tbPersons SET "+
						"personName = '"+x.getName()+"', "+
						"personFirstName = '"+x.getFirstName()+"', "+
						"personUserName = '"+x.getUserName()+"', "+
						"personBirthday = '"+x.getbDate()+"' "+
					"WHERE personUserName = '"+x.getUserName()+"'"
				);
			} catch(SQLException e){
				e.printStackTrace();
			}
			
			return x;
		}
		
		public void delete(String x){
			try {
				this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE
				).executeUpdate(
					"delete from tbPersons where personUserName = '"+x+"'"
				);		
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
		
	}