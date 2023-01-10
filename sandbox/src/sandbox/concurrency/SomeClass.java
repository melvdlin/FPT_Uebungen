package sandbox.concurrency;

public class SomeClass {
    private String someString;
    private String anotherString;

    public SomeClass(String someString, String anotherString) {
        this.someString = someString;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }
        this.anotherString = anotherString;
    }

    public String getSomeString() {
        return someString;
    }

    public String getAnotherString() {
        return anotherString;
    }
}
