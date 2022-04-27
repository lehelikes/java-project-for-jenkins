package service;

import domain.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.GradeXMLRepository;
import repository.HomeworkXMLRepository;
import repository.StudentXMLRepository;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MockitoServiceTest {
    Service service;

    @Mock
    StudentXMLRepository studentXMLRepositoryMock;
    @Mock
    HomeworkXMLRepository homeworkXMLRepositoryMock;
    @Mock
    GradeXMLRepository gradeXMLRepositoryMock;

    @BeforeEach
    public void init() {
//        studentXMLRepositoryMock = mock(StudentXMLRepository.class);
//        homeworkXMLRepositoryMock = mock(HomeworkXMLRepository.class);
//        gradeXMLRepositoryMock = mock(GradeXMLRepository.class);
        MockitoAnnotations.initMocks(this);
        service = new Service(studentXMLRepositoryMock, homeworkXMLRepositoryMock, gradeXMLRepositoryMock);
    }

    @Test
    void findAllStudents() {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("10", "Albert", 521));
        students.add(new Student("11", "Meli", 521));

        when(studentXMLRepositoryMock.findAll()).thenReturn(students);

        ArrayList<Student> studentsFromService = (ArrayList<Student>) service.findAllStudents();

        assertEquals(students, studentsFromService);
    }

    @Test
    void saveStudent() {
        when(studentXMLRepositoryMock.save(any(Student.class))).thenReturn(null);

        Integer result = service.saveStudent("6", "Lajos", 222);

        assertEquals(1, result);
        verify(studentXMLRepositoryMock).save(any(Student.class));
    }

    @Test
    void deleteStudent() {
        when(studentXMLRepositoryMock.delete(anyString())).thenReturn(new Student("11", "Meli", 521));

        Integer result = service.deleteStudent("6");

        assertEquals(1, result);
        verify(studentXMLRepositoryMock).delete(anyString());
    }
}