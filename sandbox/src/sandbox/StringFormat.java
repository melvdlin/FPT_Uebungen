package sandbox;

public class StringFormat {
    public static void main(String[] args) {
        String tableName = "A Table";
        String firstNameColumn = "firstName";
        String lastNameColumn = "lastName";
        String s1 = "CREATE TABLE IF NOT EXISTS " + tableName + " ( "
                + firstNameColumn + " VARCHAR(255) PRIMARY KEY, "
                + lastNameColumn + " VARCHAR(255) )";
        String s2 = String.format(
                "CREATE TABLE IF NOT EXISTS %s ( %s VARCHAR(255) PRIMARY KEY, %s VARCHAR(255) )",
                tableName, firstNameColumn, lastNameColumn);

        System.out.println(s1);
        System.out.println(s2);
    }
}
