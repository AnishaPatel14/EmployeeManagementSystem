package Com.Main;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import Com.dbs.DBService;
import Com.dto.Employeee;

public class EmployeeForm extends JFrame implements ActionListener{
	
	JLabel l1,l2,l3,lbl;
	JTextField t1,t2,t3;
	JMenuBar mbar;
	JMenu m1,m2;
	JMenuItem b1,b2,b3,b4,b5,b6,b7;
	
	EmployeeForm(){
		l1 = new JLabel("Employee ID          ");
		l2 = new JLabel("Employee Name          ");
		l3 = new JLabel("Employee Salary          ");
		lbl = new JLabel();
		t1 = new JTextField(20);
		t2 = new JTextField(20);
		t3 = new JTextField(20);
		
		mbar = new JMenuBar();
		setJMenuBar(mbar);
		
		m1 = new JMenu("Operations");
		m2 = new JMenu("Options");
		
		mbar.add(m1);
		mbar.add(m2);
		
		b1 = new JMenuItem("Add Employee");
		b2 = new JMenuItem("Delete Employee");
		b3 = new JMenuItem("Update Employee");
		b4 = new JMenuItem("Search Employee");
		m1.add(b1);
		m1.add(b2);
		m1.add(b3);
		m1.add(b4);
		
		b5 = new JMenuItem("Reset");
		b6 = new JMenuItem("Close");
		b7 = new JMenuItem("Display All Employee");
		
		m2.add(b5);
		m2.add(b6);
		m2.add(b7);
		
		add(l1);
		add(t1);
		add(l2);
		add(t2);
		add(l3);
		add(t3);
		add(lbl);
		
		setSize(400,300);
		setTitle("EmployeeDetails");
		setLayout(new FlowLayout());
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		
	}

	public void actionPerformed(ActionEvent ae) {
		
		Employeee e1 = new Employeee();
		
		//get employee id
		try{
			e1.setId(Integer.parseInt(t1.getText()));
		}
		catch(NumberFormatException e) {
			t1.setText("only numbers are allowed");
		}
		
		//get employee name
		e1.setName(t2.getText());
		
		//get employee salary
		try {
			e1.setSalary(Double.parseDouble(t3.getText()));
		}
		catch(NumberFormatException ee) {
			t3.setText("only numbers are allowed");
		}
		
	
		int x = 0;
		DBService db = new DBService();
	
		//Add employe data 
		if(ae.getSource()==b1) {
			x = db.addEmployee(e1);
			if(x>0) {
				lbl.setText("Record inserted Succesfully");
			}
			else {
				lbl.setText("Record could not be Inserted");
			}
		}
		
		//delete data
		if(ae.getSource()==b2) {
			x = db.deleteEmployee(e1);
			if(x>0) {
				lbl.setText("Record deleted Succesfully");
			}
			else {
				lbl.setText("Record could not be deleted");
			}
		}
		
		//update data
		if(ae.getSource()==b3) {
			x = db.updateEmployee(e1);
			if(x>0) {
				lbl.setText("Record updated Succesfully");
			}
			else {
				lbl.setText("Record could not be updated");
			}
		}
		
		//search employ
		if(ae.getSource()==b4) {
			Employeee e2 = db.searchEmployee(e1);
			if(e2.getName()!=null) {
				t1.setText(String.valueOf(e2.getId()));
				t2.setText(e2.getName());
				t3.setText(String.valueOf(e2.getSalary()));
			}
			else {
				lbl.setText("Record not found");
			}
		}
		
		// reset data
		if(ae.getSource() == b5) {
			t1.setText("");
			t2.setText("");
			t3.setText("");
		}
		
		// close 
		if(ae.getSource() == b6) {
			System.exit(0);
		}
	
	}
	
	public static void main(String[] args) {
		EmployeeForm emp  = new EmployeeForm();
		emp.setVisible(true);
		emp.setLocation(300,200);
	}

}