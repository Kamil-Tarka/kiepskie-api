package pl.ktarka.students.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ktarka.students.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
