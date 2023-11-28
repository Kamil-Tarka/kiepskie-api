package pl.ktarka.students.controler;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import pl.ktarka.students.model.Student;
import pl.ktarka.students.repository.StudentRepository;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student addStudent(@RequestBody @Valid Student student) {
        return studentRepository.save(student);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        return studentRepository.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long studentId) {
        return studentRepository.findById(studentId).map(student -> {
            studentRepository.delete(student);
            return ResponseEntity.ok().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Student> putStudent(@PathVariable Long studentId, @RequestBody @Valid Student student) {
        return studentRepository.findById(studentId).map(studentFromDb -> {
            studentFromDb.setFirstName(student.getFirstName());
            studentFromDb.setLastName(student.getLastName());
            studentFromDb.setEmail(student.getEmail());
            return ResponseEntity.ok(studentRepository.save(studentFromDb));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /*@PutMapping
    public ResponseEntity<Student> putStudent(@RequestBody @Valid Student student) {
        return ResponseEntity.ok().body(studentRepository.save(student));
    }*/

    //nie będzie działać -> model student wymusza not blank na polach (potrzebne dto)
    @PatchMapping("/{studentId}")
    public ResponseEntity<Student> patchStudent(@PathVariable Long studentId, @RequestBody Student student) {
        return studentRepository.findById(studentId).map(studentFromDb -> {
            if (StringUtils.isEmpty(student.getFirstName())) {
                studentFromDb.setFirstName(student.getFirstName());
            }
            if (StringUtils.isEmpty(student.getLastName())) {
                studentFromDb.setLastName(student.getLastName());
            }
            return ResponseEntity.ok(studentRepository.save(studentFromDb));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
