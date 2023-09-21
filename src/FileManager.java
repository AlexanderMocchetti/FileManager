import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileManager {
    private final File file;
    public FileManager(File file) {
        this.file = file;
    }
    public FileManager(String filePath) {
        this(new File(filePath));
    }
    public void read(int index) {
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            raf.seek((long) User.DIM_RECORD * index);
            User user = new User();
            char ch;
            StringBuilder string = new StringBuilder(User.DIM_MAX_NAME);
            for (int i = 0; i < 2 * User.DIM_MAX_NAME; i++) {
                ch = raf.readChar();
                if (ch != '\0')
                    string.append(ch);
                if (i == User.DIM_MAX_NAME - 1) {
                    user.setFirstName(string.toString());
                    string.delete(0, string.capacity());
                }
                if (i == 2 * User.DIM_MAX_NAME - 1) {
                    user.setLastName(string.toString());
                    string.delete(0, string.capacity());
                }
            }
            user.setYOB(raf.readInt());
            raf.close();
            System.out.println(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void write(User user) {
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            raf.seek(raf.length());

            StringBuilder string = new StringBuilder(User.DIM_MAX_NAME);

            string.append(user.getFirstName());
            string.setLength(string.capacity());
            raf.writeChars(string.toString());

            string.delete(0, string.capacity());

            string.append(user.getLastName());
            string.setLength(string.capacity());
            raf.writeChars(string.toString());

            raf.writeInt(user.getYOB());

            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void write(String firstName, String lastName, int YOB) {
        write(new User(firstName, lastName, YOB));
    }
}