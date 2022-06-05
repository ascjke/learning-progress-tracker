package courses;

public interface Course {

    String getName();

    void addPoints(int points);

    int getPoints();

    String getCourseCompletion();

    boolean isCompleted();
}
