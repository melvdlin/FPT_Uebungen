package sandbox;

public class StringIterability {
    public static void main(String[] args) {
        String s = "asdfghjkl";

        s.chars().forEach((chr) -> System.out.print((char) chr));
        System.out.println();
    }
}
