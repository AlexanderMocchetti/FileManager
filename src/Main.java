public class Main {
    public static void main(String[] args) {
        User user = new User("Alexander", "Mocchetti", 2004);
        FileManager fm = new FileManager("dati.dat");
        fm.read(0);
    }
}
