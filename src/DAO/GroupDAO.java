package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.mysql.jdbc.ResultSetMetaData;

import model.Group;
import model.ShaCalModel;

public class GroupDAO {

		protected Connection connect = null;
		
		public GroupDAO(Connection x){
			this.connect = x;
		}
		
		public Group find(int GrId){
			
			Group gr = null;
			
			try {
				ResultSet rs = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE
				).executeQuery(
					"SELECT * FROM tbGroups WHERE grId = '"+GrId+"'"	
				);
				
				if(rs.first()){
					gr = new Group(rs.getInt("grId"),
						rs.getString("grName"),
						rs.getBoolean("isPublic"),
						rs.getString("members")
					);
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
			return gr;
		}
		public void findAll(){
						
			try {
				ResultSet rs = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE
				).executeQuery(
					"SELECT * FROM tbGroups"	
				);
				
				ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();
				
				while(rs.next()){
					for(int i=1; i<= md.getColumnCount(); i++){
						ShaCalModel.allGroups.put(rs.getInt("grId"), 
								new Group(rs.getInt("grId"),
										rs.getString("grName"),
										rs.getBoolean("isPublic"),
										rs.getString("members"))
						);
					}
				}
				
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		public Group create(Group x){
			
			try {
				ResultSet rs = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE
				).executeQuery(
					"SELECT grId FROM tbGroups"	
				);
				
				if(rs.first()){
					PreparedStatement ps = this.connect.prepareStatement(
						"INSERT INTO tbPersons "
						+ "VALUES (?, ?, ?, ?)"
					);
					
					ps.setInt(1, x.getGrId());
					ps.setString(2, x.getGrName());
					ps.setBoolean(3, x.getIsPublic());
					ps.setString(4, x.getMembersString(x.getMembers()));
					
					ps.executeUpdate();
					
					x = this.find(x.getUserName());
				}
				
			} catch(SQLException e){
				e.printStackTrace();
			}
			
			return x;
			
		}
		
		public Person update(Person x, String y){
			
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
					"WHERE personUserName = '"+y+"'"
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