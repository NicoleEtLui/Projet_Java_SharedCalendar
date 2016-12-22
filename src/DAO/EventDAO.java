package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.mysql.jdbc.ResultSetMetaData;

import model.Event;
import model.ShaCalModel;

public class EventDAO {

		protected Connection connect = null;
		
		public EventDAO(Connection x){
			this.connect = x;
		}
		
		public Event find(String eventTitle){
			
			Event ev = null;
			
			try {
				ResultSet rs = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE
				).executeQuery(
					"SELECT * FROM tbEvents WHERE eventTitle = '"+eventTitle+"'"	
				);
				
				if(rs.first()){
					ev = new Event(rs.getString("eventTitle"),
							rs.getString("eventDescription"),
							rs.getString("eventLocation"),
							LocalDate.parse(rs.getString("eventStartDate")),
							LocalDate.parse(rs.getString("eventEndDate")),
							rs.getString("eventStartHour"),
							rs.getString("eventEndHour"),
							rs.getString("eventCreator")
					);
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
			return ev;
		}
		public void findAll(){
						
			try {
				ResultSet rs = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE
				).executeQuery(
					"SELECT * FROM tbEvents"	
				);
				
				ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();
				
				while(rs.next()){
					ShaCalModel.allEvents.putIfAbsent(rs.getString("eventCreator"), new ArrayList<Event>());
					ShaCalModel.allEvents.get(rs.getString("eventCreator")).add(
						new Event(rs.getString("eventTitle"),
							rs.getString("eventDescription"),
							rs.getString("eventLocation"),
							LocalDate.parse(rs.getString("eventStartDate")),
							LocalDate.parse(rs.getString("eventEndDate")),
							rs.getString("eventStartHour"),
							rs.getString("eventEndHour"),
							rs.getString("eventCreator"))
					);
				}
				
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		public Event create(Event x){
			
			try {
				ResultSet rs = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE
				).executeQuery(
					"SELECT eventTitle FROM tbEvents"	
				);
				
				if(rs.first()){
					PreparedStatement ps = this.connect.prepareStatement(
						"INSERT INTO tbEvents "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
					);
					ps.setString(1, x.getTitle());
					ps.setString(2, x.getCreator());
					ps.setString(3, x.getDescription());
					ps.setString(4, x.getLocation());
					ps.setString(5, x.getStartDate().toString());
					ps.setString(6, x.getEndDate().toString());
					ps.setString(7, x.getStartHour().toString());
					ps.setString(8, x.getEndHour().toString());
					
					ps.executeUpdate();
					
					x = this.find(x.getTitle());
				}
				
			} catch(SQLException e){
				e.printStackTrace();
			}
			
			return x;
			
		}
		
		public Event update(Event x){
			
			try{
				this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
				).executeUpdate(
					"UPDATE tbPersons SET "+
						"eventTitle = '"+x.getTitle()+"', "+
						"eventCreator = '"+x.getCreator()+"', "+
						"eventDescription = '"+x.getDescription()+"', "+
						"eventLocation = '"+x.getLocation()+"' "+
						"eventStartDate = '"+x.getStartDate()+"' "+
						"eventEndDate = '"+x.getEndDate()+"' "+
						"eventStartHour = '"+x.getStartHour()+"' "+
						"eventEndHour = '"+x.getEndHour()+"' "+
					"WHERE eventTitle = '"+x.getTitle()+"'"
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
					"delete from tbEvents where eventTitle = '"+x+"'"
				);		
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
		
	}