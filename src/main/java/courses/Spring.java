package courses;


public class Spring implements Course {

    private String name = "Databases";
    private int points;
    private static int completedTasks;
    private static int followers;
    private static final double MAX_POINTS = 550;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addPoints(int points) {
        if (this.points == 0 && points != 0) {
            followers++;
        }
        this.points += points;
        if (points != 0) {
            completedTasks++;
        }
    }

    @Override
    public int getPoints() {
        return points;
    }


    public static int getFollowers() {
        return followers;
    }

    @Override
    public String getCourseCompletion() {
        return String.format("%.1f", points / MAX_POINTS * 100);
    }

    @Override
    public boolean isCompleted() {
        return points >= MAX_POINTS;
    }

    public static int getCompletedTasks() {
        return completedTasks;
    }
}
