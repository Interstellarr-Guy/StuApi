package fantasy.StuApi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fantasy.StuApi.model.Student;
import fantasy.StuApi.service.StudentService;

@CrossOrigin(origins="*")  // allow front end to call an API
@RestController
@RequestMapping("/cazoo/5122/list")
public class StudentApiController  {

	private final StudentService studentService;

	public StudentApiController(StudentService studentService) {
		
		this.studentService = studentService;
	}
	
	// Getting students
	@GetMapping
	public List <Student> getStudents(){
		return studentService.getAllStudents();
	}
	
	@PostMapping
	public  Student createStudent(@RequestBody Student student) {
		return studentService.addStudent(student);
	}
	
	@PutMapping
	public Student editStudent(@PathVariable int id, @RequestBody Student student) {
		return studentService.updateStudent(id, student);
	}
	
	@DeleteMapping("/{id}")
	public String removeStudent(@PathVariable int id) {
		boolean deleted = studentService.deleteStudent(id);
		
		return deleted ? "Student deleted" : "Student not deleted";
	}
	
	
}
