package org.lamberm.school;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.lamberm.school.dto.StudentDTO;
import org.lamberm.school.model.Student;
import org.lamberm.school.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = SchoolApplication.class)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class IntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentRepository studentRepository;

    String name = "test";

    @Nested
    class SaveStudentTest {
        @Test
        void shouldSaveStudent() throws Exception {
            var studentDTO = new StudentDTO("12345678910", name, name, name);

            mockMvc.perform(MockMvcRequestBuilders
                            .post("/student/add")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(studentDTO)))
                    .andExpect(status().isOk());
        }

        @Test
        void shouldNotSaveStudentPeselExist() throws Exception {
            var student = new Student(1L, "12345678910", name, name, name, "");
            studentRepository.save(student);
            var studentDTO = new StudentDTO("12345678910", name, name, name);

            mockMvc.perform(MockMvcRequestBuilders
                            .post("/student/add")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(studentDTO)))
                    .andExpect(status().isBadRequest());
        }

        @ParameterizedTest
        @ValueSource(strings = {"123456789", "123456789101", "", "testtesttes"})
        void shouldNotSaveStudentPeselValidations(String input) throws Exception {
            var studentDTO = new StudentDTO(input, name, name, name);

            mockMvc.perform(MockMvcRequestBuilders
                            .post("/student/add")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(studentDTO)))
                    .andExpect(status().isBadRequest());
        }

        @ParameterizedTest
        @ValueSource(strings = {"n", "namenamenamenamenamename", "", "007"})
        void shouldNotSaveStudentFirstNameValidations(String input) throws Exception {
            var studentDTO = new StudentDTO("12345678910", input, name, name);

            mockMvc.perform(MockMvcRequestBuilders
                            .post("/student/add")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(studentDTO)))
                    .andExpect(status().isBadRequest());
        }

        @ParameterizedTest
        @ValueSource(strings = {"n", "namenamenamenamenamename", "007"})
        void shouldNotSaveStudentSecondNameValidations(String input) throws Exception {
            var studentDTO = new StudentDTO("12345678910", name, input, name);

            mockMvc.perform(MockMvcRequestBuilders
                            .post("/student/add")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(studentDTO)))
                    .andExpect(status().isBadRequest());
        }

        @ParameterizedTest
        @ValueSource(strings = {"n", "namenamenamenamenamenamenamenamenamenamenamenamenamenamenamenamenamenamenamenamenamenamenamenamenamenamenamenamenamename", "", "007"})
        void shouldNotSaveStudentLastNameValidations(String input) throws Exception {
            var studentDTO = new StudentDTO("12345678910", name, name, input);

            mockMvc.perform(MockMvcRequestBuilders
                            .post("/student/add")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(studentDTO)))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    class DeleteStudentByIdTest {
        @Test
        void shouldDeleteStudent() throws Exception {
            var student = new Student(1L, "12345678910", name, name, name, "");
            studentRepository.save(student);

            mockMvc.perform(MockMvcRequestBuilders
                            .delete("/student/delete/{id}", 1))
                    .andExpect(status().isOk());
        }

        @Test
        void shouldNotDeleteStudentIdNotExist() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .delete("/student/delete/{id}", 1))
                    .andExpect(status().isNotFound());
            Assertions.assertTrue(studentRepository.findAll().isEmpty());
        }
    }

    @Nested
    class ProvideStudentListTest {
        @Test
        void shouldProvideStudentsList() throws Exception {
            var student = new Student(1L, "12345678910", name, name, name, "");
            var student1 = new Student(2L, "12345678911", name, name, name, "");
            var student2 = new Student(3L, "12345678912", name, name, name, "");
            studentRepository.save(student);
            studentRepository.save(student1);
            studentRepository.save(student2);

            mockMvc.perform(MockMvcRequestBuilders
                            .get("/student/findAll"))
                    .andExpect(status().isOk());
            Assertions.assertFalse(studentRepository.findAll().isEmpty());
        }

        @Test
        void shouldProvideBlankStudentsList() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/student/findAll"))
                    .andExpect(status().isOk());
            Assertions.assertTrue(studentRepository.findAll().isEmpty());
        }
    }

    @Nested
    class ProvideStudentByIdTest {
        @Test
        void shouldProvideStudent() throws Exception {
            var student = new Student(1L, "12345678910", name, name, name, "");
            studentRepository.save(student);

            mockMvc.perform(MockMvcRequestBuilders
                            .get("/student/find/id/{id}", 1))
                    .andExpect(status().isOk());
        }

        @Test
        void shouldNotProvideStudentIdNotExist() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/student/find/id/{id}", 1))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    class ProvideStudentByLastNameTest {
        @Test
        void shouldProvideStudentsByLastName() throws Exception {
            var student = new Student(1L, "12345678910", name, name, name, "");
            var student1 = new Student(2L, "12345678911", name, name, name, "");
            var student2 = new Student(3L, "12345678912", name, name, name, "");
            studentRepository.save(student);
            studentRepository.save(student1);
            studentRepository.save(student2);

            mockMvc.perform(MockMvcRequestBuilders
                            .get("/student/find/lastName/{lastName}", name))
                    .andExpect(status().isOk());
        }

        @Test
        void shouldNotProvideStudentLastNameNotExist() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/student/find/lastName/{lastName}", name))
                    .andExpect(status().isNotFound());
        }

        @ParameterizedTest
        @ValueSource(strings = {"n", "namenamenamenamenamenamenamenamenamenamenamenamenamenamenamenamenamenamenamenamenamenamenamenamenamenamenamenamenamename", "", "007"})
        void shouldNotProvideStudentsByLastNameValidations(String input) throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/student/find/lastName/{lastName}", input))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    class ProvideStudentByFirstNameTest {
        @Test
        void shouldProvideStudentsByFirstName() throws Exception {
            var student = new Student(1L, "12345678910", name, name, name, "");
            var student1 = new Student(2L, "12345678911", name, name, name, "");
            var student2 = new Student(3L, "12345678912", name, name, name, "");
            studentRepository.save(student);
            studentRepository.save(student1);
            studentRepository.save(student2);

            mockMvc.perform(MockMvcRequestBuilders
                            .get("/student/find/firstName/{lastName}", name))
                    .andExpect(status().isOk());
        }

        @Test
        void shouldNotProvideStudentFirstNameNotExist() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/student/find/firstName/{firstName}", name))
                    .andExpect(status().isNotFound());
        }

        @ParameterizedTest
        @ValueSource(strings = {"n", "namenamenamenamenamename", "", "007"})
        void shouldNotProvideStudentsByFirstNameValidations(String input) throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/student/find/firstName/{firstName}", input))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    class ProvideStudentByPeselTest {
        @Test
        void shouldProvideStudentByPESEL() throws Exception {
            var student = new Student(1L, "12345678910", name, name, name, "");
            studentRepository.save(student);

            mockMvc.perform(MockMvcRequestBuilders
                            .get("/student/find/PESEL/{PESEL}", "12345678910"))
                    .andExpect(status().isOk());
        }

        @Test
        void shouldNotProvideStudentPeselNotExist() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/student/find/PESEL/{PESEL}", "12345678910"))
                    .andExpect(status().isNotFound());
        }

        @ParameterizedTest
        @ValueSource(strings = {"123456789", "123456789101", "", "testtesttes"})
        void shouldNotProvideStudentByPeselValidations(String input) throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/student/find/PESEL/{PESEL}", input))
                    .andExpect(status().isBadRequest());
        }
    }
}
