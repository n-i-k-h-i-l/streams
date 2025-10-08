package org.example.groupby;

import org.example.model.Employee;
import org.example.model.Student;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Grouping {


    public void createMapFromList(List<Employee> employeeList) {
        Map<String, List<Employee>> nameVsEmpList = employeeList
                .stream()
                .collect(Collectors.groupingBy(Employee::getName));
    }

    public void creatingLinkedHashMapFromList(List<Employee> employeeList) {
        LinkedHashMap<String, List<Integer>> map = employeeList
                .stream()
                .collect(Collectors.groupingBy(
                        Employee::getName,
                        LinkedHashMap::new,
                        Collectors.mapping(Employee::getAge, Collectors.toList())));

        LinkedHashMap<String, List<Employee>> map2 = employeeList
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Employee::getName,
                                LinkedHashMap::new,
                                Collectors.mapping(Function.identity(), Collectors.toList())
                        )
                );
    }

    public void maxAvgStudent(List<Student> students) {
        Map<String, Double> nameVsAverageMarks = students
                .stream()
                .collect(Collectors.groupingBy(Student::getName, Collectors.averagingInt(Student::getMarks)));


        Optional<Map.Entry<String, Double>> maxAvgStudent = nameVsAverageMarks
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());
    }

    public void countFrequencyOfChar(String str) {
        Map<Character, Long> charFreq = str
                .chars()
                .mapToObj(ch -> (char) ch)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    }

    public void sortByName(List<Student> students) {
        List<Student> sortedStudentList = students
                .stream()
                .sorted(Comparator.comparing(Student::getName).thenComparing(Student::getAge))
                .collect(Collectors.toList());
    }

    public void createMapWithDuplicateKey(List<Employee> employeeList) {
        LinkedHashMap<String, Integer> mapWithDuplicateKey = employeeList
                .stream()
                .collect(Collectors.toMap(
                        Employee::getName,
                        Employee::getAge,
                        (oldValue, newValue) -> newValue,
                        LinkedHashMap::new
                ));
    }
}
