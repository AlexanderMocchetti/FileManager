import java.util.Hashtable;
import java.util.List;

public class Application {
    private final FileManager fileManager;
    private Hashtable<String, Integer> hashtable;
    public Application(String filePath) {
        fileManager = new FileManager(filePath);
    }
    public Application() {
        this("dati.dat");
    }
    public boolean createHashtable() {
        List<User> records = fileManager.readAllRecords();
        if (records == null)
            return false;
        hashtable = new Hashtable<>(records.size() + 20);
        int pos = 0;
        for (User record : records) {
            hashtable.put(record.getEmail(), pos);
            pos++;
        }
        return true;
    }
    public boolean addRecord(User user) {
        if (hashtable == null || !fileManager.write(user))
            return false;
        hashtable.put(user.getEmail(), hashtable.size());
        return true;
    }
    public int getRecordPosition(String email) {
        return hashtable.get(email);
    }
    public User readRecord(int position) {
        return fileManager.read(position);
    }
    public User readRecord(String email) {
        return readRecord(getRecordPosition(email));
    }
    public List<User> readAllRecords() {
        return fileManager.readAllRecords();
    }
}
