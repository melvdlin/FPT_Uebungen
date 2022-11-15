package comparisons.param_check_manual;

class Person {
    private final String lastName, firstName;
    private final int age;
    private final double height;

    public Person(String lastName, String firstName, int age, double height) {
        if (lastName == null) {
            throw new IllegalArgumentException();
        }
        if (firstName == null) {
            throw new IllegalArgumentException();
        }
        if (age < 0) {
            throw new IllegalArgumentException();
        }
        if (height < 0.0){
            throw new IllegalArgumentException();
        }
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
