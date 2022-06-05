import java.util.Objects;


public class Notification {

    private String courseName;
    private Student student;

    public Notification(String courseName, Student student) {
        this.courseName = courseName;
        this.student = student;
    }

    public void showNotification() {
        System.out.printf("To: %s \n" +
                        "Re: Your Learning Progress \n" +
                        "Hello, %s %s! You have accomplished our %s course! \n",
                student.getEmail(), student.getFirstName(), student.getLastName(),
                courseName);
    }

    public String getCourseName() {
        return courseName;
    }

    public Student getStudent() {
        return student;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return Objects.equals(courseName, that.courseName) && Objects.equals(student, that.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseName, student);
    }
}
