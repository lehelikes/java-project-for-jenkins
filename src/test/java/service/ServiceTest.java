package service;

import domain.Grade;
import domain.Homework;
import domain.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import repository.GradeXMLRepository;
import repository.HomeworkXMLRepository;
import repository.StudentXMLRepository;
import validation.GradeValidator;
import validation.HomeworkValidator;
import validation.StudentValidator;
import validation.Validator;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    static Service service;

    @BeforeAll
    static void init() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Homework> homeworkValidator = new HomeworkValidator();
        Validator<Grade> gradeValidator = new GradeValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "students.xml");
        HomeworkXMLRepository fileRepository2 = new HomeworkXMLRepository(homeworkValidator, "homework.xml");
        GradeXMLRepository fileRepository3 = new GradeXMLRepository(gradeValidator, "grades.xml");

        service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @Test
    void findAllStudents() {
        assertNotNull(service.findAllStudents());
    }

    @Test
    void saveStudent() {
        Integer result = service.saveStudent("6", "Lajos", 222);

        assertEquals(1, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "-100", "helo"})
    void deleteStudentsShouldFail(String number) {
        Integer result = service.deleteStudent(number);
        assertEquals(0, result);
    }

    @Test
    void deleteStudent() {
        Integer result = service.deleteStudent("6");

        assertNotEquals(0, result);
    }

    @Test
    void createStudentFile() {
        assertThrows(NullPointerException.class, () -> {
            service.createStudentFile(null, null);
        });
    }
}