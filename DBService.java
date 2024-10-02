package Com.dbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Com.dto.Employeee;

public class DBService {
	Connection con = null;
	public DBService() {
		try {
			//activate Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			//get connection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jitnajava?user=root&password=root");
		}
		catch(Exception e){
			System.out.println("error in loading Driver and making connection"+e);
		}
		
	}
	
			//add employee
			public int addEmployee(Employeee e1) {
				int x = 0;
				try {
					PreparedStatement ps = con.prepareStatement("insert into employe values(?,?,?)");
					ps.setInt(1, e1.getId());
					ps.setString(2,e1.getName());
					ps.setDouble(3, e1.getSalary());
					
					x = ps.executeUpdate();
				}
				catch(Exception e) {
					System.out.println("error in sql statement "+e);
				}
				return x;
			}
			
			//delete employee
			public int deleteEmployee(Employeee e1) {
				int x = 0;
				try {
					PreparedStatement ps = con.prepareStatement("delete from employe where eid = ?");
					ps.setInt(1, e1.getId());
					
					x = ps.executeUpdate();
				}
				catch(Exception e) {
					System.out.println("error in sql statement "+e);
				}
				return x;
			}
			
			//update employee
			public int updateEmployee(Employeee e1) {
				int x = 0;
				try {
					PreparedStatement ps = con.prepareStatement("update employe set ename = ?, esalary = ? where eid = ?");
					ps.setString(1,e1.getName());
					ps.setDouble(2, e1.getSalary());
					ps.setInt(3, e1.getId());
					
					x = ps.executeUpdate();
				}
				catch(Exception e) {
					System.out.println("error in sql statement "+e);
				}
				return x;
			}
			
			//search employee
			public Employeee searchEmployee(Employeee ee) {
				Employeee e = new Employeee();
				try {
					PreparedStatement ps = con.prepareStatement("select * from employe where eid = ?");
					ps.setInt(1, ee.getId());
					ResultSet rs = ps.executeQuery();
					if(rs.next()==true) {
						e.setId(rs.getInt("eid"));
						e.setName(rs.getString("ename"));
						e.setSalary(rs.getDouble("esalary"));
					}
				}
				catch(Exception ex) {
					System.out.println(ex);
				}
				return e;
			}
		
	}

