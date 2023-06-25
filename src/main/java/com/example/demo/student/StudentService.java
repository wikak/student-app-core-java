package com.example.demo.student;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
        private final StudentRepository StudentRepository;

        @Autowired
        public StudentService(StudentRepository StudentRepository) {
                this.StudentRepository = StudentRepository;
        }

        public List<Student> getStudents() {
                return StudentRepository.findAll();

        }

        public void addNewStudent(Student student) {
                Optional<Student> studentOptional = StudentRepository.findStudentByEmail(student.getEmail());
                if (studentOptional.isPresent()) {
                        throw new IllegalStateException("Email taken");
                }
                StudentRepository.save(student);
        }

        public void deleteStudent(Long studentId) {
                boolean exist = StudentRepository.existsById(studentId);
                if (!exist) {
                        throw new IllegalStateException("Studen with id" + studentId + " is not present");
                }
                StudentRepository.deleteById(studentId);

        }

        @Transactional
        public void updateStudent(Long studentId, String studentName, String studentEmail) {
                Student student = StudentRepository.findById(studentId)
                                .orElseThrow(() -> new IllegalStateException(
                                                "Student with id " + studentId + " not found"));

                if (studentName != null &&
                                studentName.length() > 0 &&
                                !studentName.equals(student.getName())) {
                        student.setName(studentName);
                }

                if (studentEmail != null &&
                                studentEmail.length() > 0 &&
                                !studentEmail.equals(student.getEmail())) {
                        Optional<Student> studentOptional = StudentRepository.findStudentByEmail(studentEmail);
                        if (studentOptional.isPresent()) {
                                throw new IllegalStateException("Email taken");
                        }
                }

        }
}
