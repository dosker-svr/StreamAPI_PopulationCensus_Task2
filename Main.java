package StreamAPI_PopulationCensus_Task2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");

        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            persons.add(new Person(names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(30),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        Stream<Person> stream = persons.stream();
        Stream<Person> stream_1 = stream.filter(person -> person.getAge() < 18);
        long countYoung = stream_1.count();

        List<String> recruits = persons.stream().filter(person -> person.getAge() > 18 && person.getAge() < 27)
                .map(Person::getSurname)
                .collect(Collectors.toList());

        List<Person> workersWithEducation = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .collect(Collectors.toList());

//        System.out.println();
//        System.out.println("Работники с высшим образованием:");
//        workersWithEducation.stream().map(Person::getSurname).forEach(surname -> System.out.print(surname + " | "));
//        System.out.println();

        List<Person> workersMen = workersWithEducation.stream()
                .filter(person -> person.getSex() == Sex.MAN)
                .filter(person -> person.getAge() > 18 && person.getAge() < 65)
                .collect(Collectors.toList());

//        System.out.println();
//        System.out.println("Мужчины:");
//        workersMen.stream().map(Person::getSurname).forEach(surname -> System.out.print(surname + " | "));
//        System.out.println();

        List<Person> workersWomen = workersWithEducation.stream()
                .filter(person -> person.getSex() == Sex.WOMEN)
                .filter(person -> person.getAge() > 18 && person.getAge() < 60)
                .collect(Collectors.toList());

//        System.out.println();
//        System.out.println("Женщины:");
//        workersWomen.stream().map(Person::getSurname).forEach(surname -> System.out.print(surname + " | "));
//        System.out.println();

        List<Person> allWorkers = Stream.of(workersMen, workersWomen)
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(Person::getSurname))
                .collect(Collectors.toList());

//        System.out.println();
//        System.out.println("Отсортированные работники:");
//        allWorkers.stream().map(Person::getSurname).forEach(surname -> System.out.print(surname + " | "));

    }
}
