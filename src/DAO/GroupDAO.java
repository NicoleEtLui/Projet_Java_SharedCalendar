package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
					ShaCalModel.allGroups.put(Integer.valueOf(rs.getInt("grId")), 
							new Group(rs.getInt("grId"),
									rs.getString("grName"),
									rs.getBoolean("isPublic"),
									rs.getString("members"))
					);
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
						"INSERT INTO tbGroups "
						+ "VALUES (?, ?, ?, ?)"
					);
					
					ps.setInt(1, x.getGrId());
					ps.setString(2, x.getGrName());
					ps.setBoolean(3, x.getIsPublic());
					ps.setString(4, x.getMembersString(x.getMembers()));
					
					ps.executeUpdate();
					
					x = this.find(x.getGrId());
				}
				
			} catch(SQLException e){
				e.printStackTrace();
			}
			
			return x;
			
		}
		
		public Group update(Group x){
			
			try{
				this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
				).executeUpdate(
					"UPDATE tbPersons SET "+
						"grName = '"+x.getGrName()+"', "+
						"isPublic= '"+x.getIsPublic()+"', "+
						"members = '"+x.getMembersString(x.getMembers())+"', "+
					"WHERE grId = '"+x.getGrId()+"'"
				);
			} catch(SQLException e){
				e.printStackTrace();
			}
			
			return x;
		}
		
		public void delete(int x){
			try {
				this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE
				).executeUpdate(
					"delete from tbGroups where grId = '"+x+"'"
				);		
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		public void findMax(){
			
			try {
				ResultSet rs = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE
				).executeQuery(
					"SELECT MAX(grId) as grId FROM tbGroups"	
				);
				
				if(rs.first()){
					Group.setCurrentId(rs.getInt("grId")+1);
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		
	}