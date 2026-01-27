package CRSE_REG_SYS.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CourseRegistry {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String emailId;
	private String courseName;
	private boolean feepayment;
	
	public CourseRegistry(String name, String emailId, String courseName, boolean feepayment) {
		this.name = name;
		this.emailId = emailId;
		this.courseName = courseName;
		this.feepayment = feepayment; 
	}
	
	public CourseRegistry() {
		
	}
	
	public boolean getFeepayment() {
		return feepayment;
	}

	public void setFeepayment(boolean feepayment) {
		this.feepayment = feepayment;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
}
