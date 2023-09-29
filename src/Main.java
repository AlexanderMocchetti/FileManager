public class Main {
    public static void main(String[] args) {
        Application application = new Application();
        application.createHashtable();
        application.addRecord(new User("lmao@lmao.com", "Alexander", "Mocchetti", 2004));
        application.addRecord(new User("giuse@giuse.com", "Giuseppe", "Sfalanga", 2004));
        application.addRecord(new User("lazza@lazza.com", "Anthony", "Lazzaro", 2004));
        System.out.println(application.readAllRecords());
    }
}
