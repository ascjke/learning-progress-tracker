import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;


public class CLI {

    private static final String EXIT = "exit";
    private static final String BACK = "back";
    private static final String ADD_STUDENTS = "add students";
    private static final String ADD_POINTS = "add points";
    private static final String LIST = "list";
    private static final String FIND = "find";
    private static final String STATISTICS = "statistics";
    private static final String NOTIFY = "notify";
    private static StudentRepository repository = new StudentRepository();
    private static Scanner scanner = new Scanner(System.in);
    private List<Notification> notifications = new ArrayList<>();

    public void start() {

        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.isEmpty() || userInput.isBlank()) {
                System.out.println("No input.");
            } else if (isValidInput(userInput)) {
                switch (userInput) {
                    case ADD_STUDENTS -> addStudents();
                    case LIST -> list();
                    case ADD_POINTS -> addPoints();
                    case FIND -> find();
                    case STATISTICS -> statistics();
                    case NOTIFY -> notifyStudents();
                    case BACK -> back();
                    case EXIT -> exit();
                }
            } else {
                System.out.println("Error: unknown command!");
            }
        }
    }

    private void notifyStudents() {
        List<Student> students = new ArrayList<>(repository.getAllStudents().values());
        AtomicBoolean flag = new AtomicBoolean(false);
        students.forEach(student -> student.getCourses()
                .forEach(course -> {
                    if (course.isCompleted()) {
                        Notification notification = new Notification(course.getName(), student);
                        if (!notifications.contains(notification)) {
                            notification.showNotification();
                            notifications.add(notification);
                            flag.set(true);
                        }
                    }
                }));
        AtomicInteger notifiedStudents = new AtomicInteger();
        students.forEach(student -> {
            if (!student.getCompletedCourses().isEmpty()) {
                notifiedStudents.getAndIncrement();
            }
        });
        if (flag.get()) {
            System.out.printf("Total %d students have been notified.\n", notifiedStudents.get());
        } else {
            System.out.printf("Total %d students have been notified.\n", 0);
        }
    }

    private void statistics() {
        Statistics statistics = new Statistics(repository);
        String mostPopular = statistics.getMostPopular();
        String leastPopular = statistics.getLeastPopular();
        String highestActivity = statistics.getHighestActivityCourse();
        String lowestActivity = statistics.getLowestActivityCourse();
        String easiestCourse = statistics.getEasiestCourse();
        String hardestCourse = statistics.getHardestCourse();

        System.out.println("Type the name of a course to see details or 'back' to quit:");
        System.out.println("Most popular: " + mostPopular);
        System.out.println("Least popular: " + leastPopular);
        System.out.println("Highest activity: " + highestActivity);
        System.out.println("Lowest activity: " + lowestActivity);
        System.out.println("Easiest course: " + easiestCourse);
        System.out.println("Hardest course: " + hardestCourse);

        while (true) {
            String input = scanner.nextLine();
            if ("back".equals(input)) {
                break;
            }
            switch (input.toLowerCase()) {
                case "java" -> statistics.printJavaStats();
                case "dsa" -> statistics.printDsaStats();
                case "databases" -> statistics.printDatabasesStats();
                case "spring" -> statistics.printSpringStats();
                default -> System.out.println("Unknown course");
            }
        }
    }

    private void find() {
        System.out.println("Enter an id or 'back' to return:");
        while (true) {
            String id = scanner.nextLine();
            if ("back".equals(id)) {
                break;
            }
            Student student = repository.getAllStudents().get(id);
            if (student == null) {
                System.out.printf("No student is found for id=%s.\n", id);
            } else {
                student.printCourses();
            }
        }
    }

    private void addPoints() {
        System.out.println("Enter an id and points or 'back' to return:");
        while (true) {
            String[] idAndPoints = scanner.nextLine().split(" ");
            if ("back".equals(idAndPoints[0])) {
                break;
            }
            if (idAndPoints.length != 5) {
                System.out.println("Incorrect points format.");
            } else {
                try {
                    String id = idAndPoints[0];
                    int javaPoints = Integer.parseInt(idAndPoints[1]);
                    int dsaPoints = Integer.parseInt(idAndPoints[2]);
                    int databasesPoints = Integer.parseInt(idAndPoints[3]);
                    int springPoints = Integer.parseInt(idAndPoints[4]);
                    if (repository.getAllStudents().get(id) == null) {
                        System.out.printf("No student is found for id=%s.\n", id);
                    } else if (javaPoints < 0 || dsaPoints < 0 || databasesPoints < 0 || springPoints < 0) {
                        System.out.println("Incorrect points format.");
                    } else if (Objects.nonNull(repository.getAllStudents().get(id))) {
                        repository.addPointsToStudent(id, javaPoints, dsaPoints, databasesPoints, springPoints);
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Incorrect points format.");
                }
            }
        }
    }

    private void list() {
        if (repository.getAllStudents().size() == 0) {
            System.out.println("No students found.");
        } else {
            System.out.println("Students:");
            repository.getAllStudents().values().forEach(s -> System.out.println(s.getId()));
        }
    }

    private void addStudents() {
        System.out.println("Enter student credentials or 'back' to return:");
        while (true) {
            String studentCredentials = scanner.nextLine();
            if ("back".equals(studentCredentials)) {
                System.out.println("Total " + repository.getAllStudents().size() + " students have been added.");
                break;
            }
            if (CredentialsChecker.isValidCredentials(studentCredentials)) {
                Student student = createStudent(studentCredentials);
                repository.addStudent(student);
            }
        }
    }

    private void back() {
        System.out.println("Enter 'exit' to exit the program.");
    }

    private void exit() {
        System.out.println("Bye!");
        System.exit(0);
    }

    private boolean isValidInput(String userInput) {
        List<String> availableCommands = List.of(EXIT, BACK, ADD_STUDENTS, ADD_POINTS, LIST, FIND, STATISTICS, NOTIFY);
        return availableCommands.contains(userInput);
    }

    private static Student createStudent(String studentCredentials) {
        String[] inputParts = studentCredentials.split(" ");
        String firstName = inputParts[0];
        String email = inputParts[inputParts.length - 1];
        String lastName = "";
        for (int i = 1; i < inputParts.length - 1; i++) {
            lastName += inputParts[i] + " ";
        }
        lastName = lastName.substring(0, lastName.length() - 1);

        return new Student(firstName, lastName, email);
    }
}
