package java8Action;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

/**
 * @author gavin
 * @createDate 2020/3/6
 */
public class OptionalAction {

    public static void main(String[] args) {


        Optional.ofNullable(getInsuranceNameByOptional(null)).ifPresent(System.out::println);

    }

    private static String getInsuranceNameByOptional(Person person) {

        return Optional.ofNullable(person)
                .flatMap(Person::getCar).flatMap(Car::getInsurance)
                .map(Insurance::getName).orElse("Unknown");
    }


}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Person{
    private Optional<Car> car;
}


@Data
@AllArgsConstructor
class Car{
    private Optional<Insurance> insurance;
}

@Data
@AllArgsConstructor
class Insurance {
    private String name;
}
