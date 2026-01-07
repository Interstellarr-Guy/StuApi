package fantasy.StuApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fantasy.StuApi.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
