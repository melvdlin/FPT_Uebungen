package comparisons.param_check_none;

class Person {
    private final String lastName, firstName;
    private final int age;
    private final double height;

    public Person(String lastName, String firstName, int age, double height) {
        this.age = age;
        this.lastName = lastName;
        this.firstName = firstName;
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
}
