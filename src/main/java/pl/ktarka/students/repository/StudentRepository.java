package pl.ktarka.students.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.ktarka.students.model.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    /*
    List<Student> findAllByLastName(String lastName, Pageable pageable);

    List<Student> findByLastNameAndFirstNameIsNotLikeAllIgnoreCase(String lastName, String firstName);

    @Query("SELECT s FROM Student s WHERE s.firstName = 'Marian'")
    List<Student> findStudentsWithNameMarian();*/
}
