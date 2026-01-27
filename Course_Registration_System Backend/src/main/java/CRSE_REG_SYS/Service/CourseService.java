package CRSE_REG_SYS.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CRSE_REG_SYS.Model.Course;
import CRSE_REG_SYS.Model.CourseRegistry;
import CRSE_REG_SYS.Model.User_Records;
import CRSE_REG_SYS.Repository.CourseRegistryRepository;
import CRSE_REG_SYS.Repository.CourseRepository;
import CRSE_REG_SYS.Repository.User_records;

@Service
public class CourseService {
	
	@Autowired
	CourseRepository CR;
	
	@Autowired
	CourseRegistryRepository CRR;
	
	@Autowired
	User_records ur;
	
	public List<Course> getallcourse() {
		return CR.findAll();
	}
	public List<CourseRegistry> enrolledStudents() {
		return CRR.findAll();
	}
	public void enrollStudent(String name, String emailId, String courseName, boolean feepayment) {
		CourseRegistry cr = new CourseRegistry(name,emailId,courseName,feepayment);
		CRR.save(cr);
	}
	public void enrollCourse(String courseId, String courseName, String trainer, int duration, int coursefee) {
		Course crr = new Course(courseId,courseName,trainer,duration,coursefee);
		CR.save(crr);
	}
	public void updateStudent(int id,CourseRegistry cs) {
		CourseRegistry csr = CRR.findById(id).orElseThrow();
		if(csr!=null) {
			csr.setName(cs.getName());
			csr.setEmailId(cs.getEmailId());
			csr.setCourseName(cs.getCourseName());
			csr.setFeepayment(cs.getFeepayment());
			CRR.save(csr);
		}
	}
	public void updateCourse(String courseId, Course c) {
		Course cc = CR.findById(courseId).orElseThrow();
		if(cc!=null) {
			cc.setCourseId(c.getCourseId());
			cc.setCourseName(c.getCourseName());
			cc.setTrainer(c.getTrainer());
			cc.setDuration(c.getDuration());
			cc.setCoursefee(c.getCoursefee());
			CR.save(cc);
		}
		
	}
	public boolean deleteCourse(String courseId) {
		if(CR.existsById(courseId)) {
			CR.deleteById(courseId);
			return true;
		}
		else {
			return false;
		}
	}
	public boolean deleteStudent(int id) {
		if(CRR.existsById(id)) {
			CRR.deleteById(id);
			return true;
		}
		else {
			return false;
		}
	}
	public Optional<Course> getSingleCourse(String courseId) {
		return CR.findById(courseId);
	}
	public Optional<CourseRegistry> getSingleStudent(int id) {
		return CRR.findById(id);
	}
	
	public String login(User_Records user) { 
		User_Records urs = ur.findByUsername(user.getUsername());
		if(urs!=null && urs.getPassword().equals(user.getPassword()) && urs.getRole().equalsIgnoreCase(user.getRole())) {
			return "true";
		}
		else {
			return "false";
		}
	}
	public String signup(User_Records user) {
		ur.save(user);
		return "User Added Successfully";
	}
	public List<User_Records> getallrecords() {
		return ur.findAll();
	}

}
