package CRSE_REG_SYS.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Course {
	
	@Id
	private String courseId;
	
	private String courseName;
	private String trainer;
	private int duration;
	private int coursefee;
	
	public Course(String courseId, String courseName, String trainer, int duration, int coursefee) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.trainer = trainer;
		this.duration = duration;
		this.coursefee = coursefee;
	}
	
	public int getCoursefee() {
		return coursefee;
	}

	public void setCoursefee(int coursefee) {
		this.coursefee = coursefee;
	}

	public Course() {
		
	}
	
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getTrainer() {
		return trainer;
	}
	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}

}
