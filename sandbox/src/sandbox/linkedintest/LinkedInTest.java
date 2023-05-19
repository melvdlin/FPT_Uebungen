package sandbox.linkedintest;

public class LinkedInTest {

    public static void main(String[] args) {
        String a = "bikini";
        String b = new String("bikini");
        String c = new String("bikini");
        System.out.println(a == b);
        System.out.println(b == c);
    }

}
