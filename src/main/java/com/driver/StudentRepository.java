package com.driver;

import java.util.*;

public class StudentRepository {
    private Map<String,Teacher> teacherData = new HashMap<>();
    private Map<String,Student> studentData = new HashMap<>();
    private Map<String, ArrayList<String>> relationData = new HashMap<>();

    public void addStudent(Student student)
    {
        studentData.put(student.getName(),student);
    }
    public void addTeacher(Teacher teacher)
    {
        teacherData.put(teacher.getName(),teacher);
    }
    public void addRelation(String student,String teacher)
    {
        ArrayList<String> students = (ArrayList<String>) relationData.getOrDefault(teacher,new ArrayList<>());
        students.add(student);
        relationData.put(teacher,students);
    }
    public Optional<Student> getStudent(String student)
    {
         if(studentData.containsKey(student))
         {
             return Optional.of(studentData.get(student));
         }
         return Optional.empty();
    }
    public Optional<Teacher> getTeacher(String teacher)
    {
        if(teacherData.containsKey(teacher))
        {
            return Optional.of(teacherData.get(teacher));
        }
        return Optional.empty();
    }
    public List<String> getAllStudentByTeacher(String name)
    {
        return relationData.getOrDefault(name,new ArrayList<>());
    }
    public List<String> getAllStudents()
    {
        return new ArrayList<>(studentData.keySet());
    }
    public void deleteStudent(String stud)
    {
        studentData.remove(stud);
    }
    public void deleteTeacher(String teacher)
    {
        teacherData.remove(teacher);
        relationData.remove(teacher);
    }
    public List<String> getAllTeachers()
    {
        return new ArrayList<>(teacherData.keySet());
    }
}
