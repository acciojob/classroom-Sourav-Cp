package com.driver;

import java.util.List;
import java.util.Optional;

public class StudentService {
    private StudentRepository studentRepository = new StudentRepository();

    public void addStudent(Student student)
    {

        studentRepository.addStudent(student);
    }
    public void addTeacher(Teacher teacher)
    {
        studentRepository.addTeacher(teacher);
    }

    public void addRelation(String student,String teacher) throws StudentNameException,TeacherNameException
    {
        Optional<Student> optionalStudent = studentRepository.getStudent(student);
        Optional<Teacher> optionalTeacher = studentRepository.getTeacher(teacher);

        if(optionalStudent.isEmpty())
        {
            throw new StudentNameException("Student name doesn't exist");
        }

        if(optionalTeacher.isEmpty())
        {
            throw new TeacherNameException("Teacher name doesn't exist");
        }
        Teacher teacherObj = optionalTeacher.get();
        teacherObj.setNumberOfStudents(teacherObj.getNumberOfStudents()+1);
        studentRepository.addTeacher(teacherObj);

        studentRepository.addRelation(student,teacher);

    }
    public Student getStudent(String name) throws StudentNameException
    {
        Optional<Student> optionalStudent = studentRepository.getStudent(name);
        if(optionalStudent.isPresent()) return optionalStudent.get();
        else throw new StudentNameException("Student name doesn't exist");
    }
    public Teacher getTeacher(String name) throws TeacherNameException
    {
        Optional<Teacher> optionalTeacher = studentRepository.getTeacher(name);
        if(optionalTeacher.isPresent()) return optionalTeacher.get();
        else throw new TeacherNameException("Teacher Name doesn't exist");
    }
    public List<String> getAllStudentByTeacher(String name)
    {
        return studentRepository.getAllStudentByTeacher(name);
    }

    public List<String> getAllStudents()
    {
       return studentRepository.getAllStudents();
    }
    public void deleteTeacher(String teacher)
    {
        List<String> students = getAllStudentByTeacher(teacher);
        studentRepository.deleteTeacher(teacher);

        for(String stud : students)
        {
            studentRepository.deleteStudent(stud);
        }
    }
    public void deleteAllTeachers()
    {
        List<String> teachers = studentRepository.getAllTeachers();

        for(String teacher : teachers)
        {
            deleteTeacher(teacher);
        }
    }
}
