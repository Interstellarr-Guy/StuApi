package fantasy.StuApi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fantasy.StuApi.model.Student;

@Service
public class StudentService {

	
	private final List<Student> students = new ArrayList<>(); 
	
	public StudentService() {
		students.add(new Student(1,"Avinash","Java"));
		students.add(new Student(2,"Deva","Testing"));
		students.add(new Student(3,"Naveen","Fashion"));
		students.add(new Student(4,"Mohan","Medical coding"));
		students.add(new Student(4,"Siv","Javascript"));
		
	}
	
	public List<Student> getAllStudents(){
		return students;
	}
	
	public Student addStudent(Student student) {
		students.add(student);
		return student;
	}
	
	public Student updateStudent(int id, Student updatedStudent) {
        return students.stream()
            .filter(s -> s.getId() == id)
            .findFirst()
            .map(s -> {
                s.setName(updatedStudent.getName());
                s.setCourse(updatedStudent.getCourse());
                return s;
            })
            .orElse(null);
    }

    public boolean deleteStudent(int id) {
        return students.removeIf(s -> s.getId() == id);
    }
}
