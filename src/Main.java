public class Main {
    public static void main(String[] args) {
        Application application = new Application();
        application.createHashtable();
        System.out.println(application.readAllRecords());
        System.out.println(application.readRecord(3));
    }
}
