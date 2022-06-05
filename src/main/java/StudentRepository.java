import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;


public class StudentRepository {

    private Map<String, Student> students = new HashMap<>();

    public Map<String, Student> getAllStudents() {
        return students;
    }

    public Student getStudentById(String id) {
        return students.get(id);
    }

    public void addStudent(Student student) {
        Collection<Student> checkStudentsEmail = students.values();
        AtomicBoolean canBeAdded = new AtomicBoolean(true);
        checkStudentsEmail.forEach(s -> {
            if (s.getEmail().equals(student.getEmail())) {
                System.out.println("This email is already taken.");
                canBeAdded.set(false);
            }
        });
        if (canBeAdded.get()) {
            students.put(student.getId(), student);
            System.out.println("The student has been added.");
        }
    }

    public void addPointsToStudent(String id, int javaPoints, int dsaPoints, int databasesPoints, int springPoints) {
        Student student = students.get(id);
        student.updatePoints(javaPoints, dsaPoints, databasesPoints, springPoints);
        students.put(id, student);
        System.out.println("Points updated.");
    }
}
