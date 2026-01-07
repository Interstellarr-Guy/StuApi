package fantasy.StuApi.service;


import java.util.List;

import org.springframework.stereotype.Service;

import fantasy.StuApi.model.Student;
import fantasy.StuApi.repository.StudentRepository;

@Service
public class StudentService {

	private final StudentRepository studentRepository;

	public StudentService(StudentRepository studentRepository) {
		
		this.studentRepository = studentRepository;
	}
	
	public List<Student> getAllStudents(){
		return studentRepository.findAll();
	}
	
	public Student addStudent(Student student) {
		return studentRepository.save(student);
	}
	public Student updateStudent(int id,  Student student) {
		return studentRepository.findById(id)
				.map(s -> {
				s.setName(student.getName());
				s.setCourse(student.getCourse());
				return studentRepository.save(s);
				})
				.orElse(null);
	}
	
	public boolean deleteStudent(int id) {
		if(studentRepository.existsById(id)) {
			studentRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
