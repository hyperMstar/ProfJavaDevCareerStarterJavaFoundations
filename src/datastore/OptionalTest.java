package datastore;

import java.time.Year;
import java.util.List;
import java.util.Optional;

public class OptionalTest {
    record Car(String make, String model, String color, Year year) {}
    record Person(Long id, String firstName, String lastName, Optional<Car> car) implements Repository.IDable<Long>, Repository.Saveable {}
    public static void main(String[] args) {
        Repository<Person, Long> repo = new Repository<>();
        Person p1 = new Person(100L,"Tom", "Thumb", Optional.of(new Car("Tesla", "X", "Red", Year.of(2023))));
        Person p2 = new Person(200L, "Jerry", "Thumb", Optional.of(new Car("Tesla", "Y", "White", Year.of(2024))));
        Person p3 = new Person(200L, "Jake", "Thumb", Optional.of(new Car("Tesla", "3", "Blue", Year.of(2021))));
        Person p4 = new Person(200L, "Johnny", "Thumb", Optional.of(new Car("Tesla", "S", "Black", Year.of(2020))));
        Person p5 = null;

        Optional<Person> p11 = Optional.of(p1);
        p11.stream()
                .map(Person::firstName)
                .forEach(System.out::println);


        List<Optional<Person>> people = List
                .of(Optional.of(p1),
                    Optional.of(p2),
                    Optional.of(p3),
                    Optional.of(p4),
                    Optional.ofNullable(p5
        ));
        people.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(Person::firstName)
                .forEach(System.out::println);

        repo.save(p1);
        repo.save(p2);

//        Optional<String> optMsg = Optional.of("Hello");

//
        String FoundFirstName = repo.findById(300L)
                .map(Person::firstName)
                .orElse("First name not found");
        System.out.println(FoundFirstName);
//        String msg = "Hello";
//        String msg2 = "cats";
//        Optional<String> optMsg2 = Optional.ofNullable(msg2);
//        if (optMsg2.isPresent()){
//            System.out.println(optMsg2.get().toUpperCase());
//        }else {
//            System.out.println("there was nothing there");
//        }
//        String finalOutput = optMsg2.orElse("alternative").toUpperCase();
//        System.out.println(finalOutput);
//        System.out.println(optMsg2.orElse("there was nothing there").toUpperCase());
//        System.out.println(optMsg2.orElseThrow(()-> new RuntimeException("Nothing found")));
//        System.out.println(optMsg2.orElseGet(()-> "my alt"));
//        System.out.println(optMsg2.or(()-> Optional.of("nothing here")));

    }



}
