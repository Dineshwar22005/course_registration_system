package CRSE_REG_SYS.Controller;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import CRSE_REG_SYS.Model.Course;
import CRSE_REG_SYS.Model.CourseRegistry;
import CRSE_REG_SYS.Model.User_Records;
import CRSE_REG_SYS.Service.CourseService;

@RestController
@CrossOrigin(origins = "*")
public class CourseController {
	
	@Autowired
	CourseService CS;
	
	@PostMapping("auth/login")
	public String login(@RequestBody User_Records user) {
		return CS.login(user);
	}
	
	@PostMapping("auth/Signup")
	public String signup(@RequestBody User_Records user) {
		return CS.signup(user);
	}

	@GetMapping("courses")
	public List<Course> getCourses(){
		return CS.getallcourse();
	}
	
	@GetMapping("courses/enrolled")
	public List<CourseRegistry> getenrolledStudents(){
		return CS.enrolledStudents();
	}
	
	@GetMapping("course/{courseId}")
	public Optional<Course> getSingleCourse(@PathVariable String courseId){
		return CS.getSingleCourse(courseId);
	}
	
	@GetMapping("course/getStudent/{id}")
	public Optional<CourseRegistry> getSingleStudent(@PathVariable int id){
		return CS.getSingleStudent(id);
	}
	
	@GetMapping("user_records")
	public List<User_Records> getallrecords(){
		return CS.getallrecords();
	}
	
	@PostMapping("courses/registerStudent")
	public String enrollStudent(@RequestParam String name,
								@RequestParam String emailId,
								@RequestParam String courseName,
								@RequestParam boolean feepayment) {
		CS.enrollStudent(name,emailId,courseName,feepayment);
		return "Congratulation! Enrollment Successful";
	}
	
	@PostMapping("courses/registerCourse")
	public String enrollcourse(@RequestParam String courseId,
							   @RequestParam String courseName,
			                   @RequestParam String trainer,
			                   @RequestParam int duration,
			                   @RequestParam int coursefee) {
		CS.enrollCourse(courseId,courseName,trainer,duration,coursefee);
		return "Congratulation! Course added Successfully";
	}
	
	@PutMapping("course/updateStudent/{id}")
	public String updateStudent(@PathVariable int id, @RequestBody CourseRegistry cs) {
		CS.updateStudent(id,cs);
		return "Student Details updated successfully";
	}
	
	@PutMapping("course/updatecourse/{courseId}")
	public String updateCourse(@PathVariable String courseId, @RequestBody Course c) {
		CS.updateCourse(courseId,c);
		return "Course Details updated successfully";
	}
	
	@DeleteMapping("course/delete/{courseId}")
	public String deleteCourse(@PathVariable String courseId) {
		boolean delete = CS.deleteCourse(courseId);
		if(delete) {
			return "Course is deleted";
		}
		else {
			return "Course is not found";
		}
	}
	
	@DeleteMapping("course/deleteStudent/{id}")
	public String deleteStudent(@PathVariable int id) {
		boolean delete = CS.deleteStudent(id);
		if(delete) {
			return "Student is deleted";
		}
		else {
			return "Student is not found";
		}
	}
}
