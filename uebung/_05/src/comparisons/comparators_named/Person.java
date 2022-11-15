package comparisons.comparators_named;

import java.util.Objects;

class Person {
    private final String lastName, firstName;
    private final int age;
    private final double height;

    public Person(String lastName, String firstName, int age, double height) {
        if (age < 0) {
            throw new IllegalArgumentException();
        }
        if (height < 0.0) {
            throw new IllegalArgumentException();
        }
        this.age = age;
        this.lastName = Objects.requireNonNull(lastName);
        this.firstName = Objects.requireNonNull(firstName);
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return String.format(
                "%12s, %8s, age: %3d, height: %1.3fm",
                lastName, firstName, age, height);
    }
}
