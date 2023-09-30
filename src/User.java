import java.time.Year;

public class User {
    private String email;
    private String firstName, lastName;
    private int YOB;
    public static final int
        DIM_MAX_NAME = 20,
        DIM_MAX_MAIL = 60,
        DIM_RECORD = 2 * (DIM_MAX_NAME * Character.BYTES) + Integer.BYTES + DIM_MAX_MAIL * Character.BYTES;
    public User(String email, String firstName, String lastName, int YOB) {
        if (!setEmail(email))
            this.email = "";
        if (!setFirstName(firstName))
            this.firstName = "";
        if (!setLastName(lastName))
            this.lastName = "";
        if (!setYOB(YOB))
            this.YOB = 0;
    }
    public User() {
        this("", "", "", 0);
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public int getYOB() {
        return YOB;
    }
    public boolean setFirstName(String firstName) {
        if (firstName.length() > DIM_MAX_NAME)
            return false;
        this.firstName = firstName;
        return true;
    }
    public boolean setLastName(String lastName) {
        if (lastName.length() > DIM_MAX_NAME)
            return false;
        this.lastName = lastName;
        return true;
    }

    public String getEmail() {
        return email;
    }

    public boolean setEmail(String email) {
        if (email.length() > DIM_MAX_MAIL)
            return false;
        this.email = email;
        return true;
    }

    public boolean setYOB(int YOB) {
        if (YOB > Year.now().getValue())
            return false;
        this.YOB = YOB;
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", YOB=" + YOB +
                '}';
    }
}
