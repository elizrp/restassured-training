import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee {

    private String name;
    private int salary;
    private int age;

    public Employee(String name, int salary, int age) {
        this.name = name;
        this.salary = salary;
        this.age = age;
    }
}
