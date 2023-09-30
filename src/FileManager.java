import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private final File file;
    public FileManager(File file) {
        this.file = file;
    }
    public FileManager(String filePath) {
        this(new File(filePath));
    }
    private User read(int index, RandomAccessFile raf) throws IOException{
        raf.seek((long) User.DIM_RECORD * index);
        User user = new User();
        user.setEmail(readString(User.DIM_MAX_MAIL, raf));
        user.setFirstName(readString(User.DIM_MAX_NAME, raf));
        user.setLastName(readString(User.DIM_MAX_NAME, raf));
        user.setYOB(raf.readInt());
        return user;
    }
    public User read(int index) {
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            User user = read(index, raf);
            raf.close();
            return user;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
    public List<User> readAllRecords() {
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            int numberOfRecords = getNumberOfFileRecords(raf);
            if (numberOfRecords == -1)
                return null;
            ArrayList<User> records = new ArrayList<>(numberOfRecords);
            for (int i = 0; i < numberOfRecords; i++) {
                records.add(read(i, raf));
            }
            raf.close();
            return records;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
    private int getNumberOfFileRecords(RandomAccessFile raf) {
        try {
            return (int) (raf.length() / User.DIM_RECORD);
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }
    private String readString(int stringCapacity, RandomAccessFile raf) throws IOException {
        StringBuilder string = new StringBuilder(stringCapacity);
        char ch;
        for (int i = 0; i < stringCapacity; i++) {
            ch = raf.readChar();
            if (ch != '\0')
                string.append(ch);
        }
        return string.toString();
    }
    public boolean write(User user) {
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            raf.seek(raf.length());
            writeString(user.getEmail(), User.DIM_MAX_MAIL, raf);
            writeString(user.getFirstName(), User.DIM_MAX_NAME, raf);
            writeString(user.getLastName(), User.DIM_MAX_NAME, raf);
            raf.writeInt(user.getYOB());
            raf.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    private void writeString(String string, int stringCapacity, RandomAccessFile raf) throws IOException{
        StringBuilder stringBuilder = new StringBuilder(stringCapacity);
        stringBuilder.append(string);
        stringBuilder.setLength(stringCapacity);
        raf.writeChars(stringBuilder.toString());
    }
    public void write(String email, String firstName, String lastName, int YOB) {
        write(new User(email, firstName, lastName, YOB));
    }
}