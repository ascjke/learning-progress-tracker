import courses.*;

import java.util.*;

public class Student {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private List<Course> courses = new ArrayList<>(); // 0-java 1-dsa 2-databases 3-spring

    private static int count;

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        initCourses();
        this.id = String.valueOf(++count);
    }

    private void initCourses() {
        courses.add(new Java());
        courses.add(new DSA());
        courses.add(new Databases());
        courses.add(new Spring());
    }

    public void updatePoints(int javaPoints, int dsaPoints, int databasesPoints, int springPoints) {
        courses.get(0).addPoints(javaPoints);
        courses.get(1).addPoints(dsaPoints);
        courses.get(2).addPoints(databasesPoints);
        courses.get(3).addPoints(springPoints);
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void printCourses() {
        int javaPoints = courses.get(0).getPoints();
        int dsaPoints = courses.get(1).getPoints();
        int databasesPoints = courses.get(2).getPoints();
        int springPoints = courses.get(3).getPoints();
        System.out.printf("%s points: Java=%d; DSA=%d; Databases=%d; Spring=%d\n",
                id, javaPoints, dsaPoints, databasesPoints, springPoints);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public int getJavaPoints() {
        return courses.get(0).getPoints();
    }

    public int getDsaPoints() {
        return courses.get(1).getPoints();
    }

    public int getDatabasesPoints() {
        return courses.get(2).getPoints();
    }

    public int getSpringPoints() {
        return courses.get(3).getPoints();
    }

    public List<String> getCompletedCourses() {
        List<String> completedCourses = new ArrayList<>();
        if (getCourses().get(0).isCompleted()) {
            completedCourses.add("Java");
        }
        if (getCourses().get(1).isCompleted()) {
            completedCourses.add("DSA");
        }
        if (getCourses().get(2).isCompleted()) {
            completedCourses.add("Databases");
        }
        if (getCourses().get(3).isCompleted()) {
            completedCourses.add("Spring");
        }

        return completedCourses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) &&
                Objects.equals(firstName, student.firstName) &&
                Objects.equals(lastName, student.lastName) &&
                Objects.equals(email, student.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", courses=" + courses +
                '}';
    }
}
