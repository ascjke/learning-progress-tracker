import courses.DSA;
import courses.Databases;
import courses.Java;
import courses.Spring;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Statistics {

    private StudentRepository repository;

    public Statistics(StudentRepository repository) {
        this.repository = repository;
    }

    public String getMostPopular() {
        List<String> mostPopulars = new ArrayList<>();
        int java = Java.getFollowers();
        int dsa = DSA.getFollowers();
        int databases = Databases.getFollowers();
        int spring = Spring.getFollowers();
        String result;

        int[] courseFollowers = new int[]{java, dsa, databases, spring};
        int max = courseFollowers[0];
        for (int i = 1; i < 4; i++) {
            if (courseFollowers[i] > max) {
                max = courseFollowers[i];
            }
        }

        if (max == java && max != 0) {
            mostPopulars.add("Java");
        }
        if (max == dsa && max != 0) {
            mostPopulars.add("DSA");
        }
        if (max == databases && max != 0) {
            mostPopulars.add("Databases");
        }
        if (max == spring && max != 0) {
            mostPopulars.add("Spring");
        }

        if (mostPopulars.isEmpty()) {
            result = "n/a";
        } else {
            result = String.join(", ", mostPopulars);
        }

        return result;
    }

    public String getLeastPopular() {
        List<String> leastPopular = new ArrayList<>();
        int java = Java.getFollowers();
        int dsa = DSA.getFollowers();
        int databases = Databases.getFollowers();
        int spring = Spring.getFollowers();
        String result;

        int[] courseFollowers = new int[]{java, dsa, databases, spring};
        int min = courseFollowers[0];
        for (int i = 1; i < 4; i++) {
            if (courseFollowers[i] < min) {
                min = courseFollowers[i];
            }
        }

        if (min == java || java == 0) {
            leastPopular.add("Java");
        }
        if (min == dsa || dsa == 0) {
            leastPopular.add("DSA");
        }
        if (min == databases || databases == 0) {
            leastPopular.add("Databases");
        }
        if (min == spring || spring == 0) {
            leastPopular.add("Spring");
        }

        if (java == 0 && dsa == 0 && databases == 0 && spring == 0
                || String.join(", ", leastPopular).equals(getMostPopular())) {
            result = "n/a";
        } else {
            result = String.join(", ", leastPopular);
        }

        return result;
    }

    public String getHighestActivityCourse() {
        List<String> highestActivity = new ArrayList<>();
        int javaCompletedTasks = Java.getCompletedTasks();
        int dsaCompletedTasks = DSA.getCompletedTasks();
        int databasesCompletedTasks = Databases.getCompletedTasks();
        int springCompletedTasks = Spring.getCompletedTasks();
        String result;

        int[] mostActivityArray =
                new int[]{javaCompletedTasks, dsaCompletedTasks, databasesCompletedTasks, springCompletedTasks};
        int max = mostActivityArray[0];
        for (int i = 1; i < 4; i++) {
            if (mostActivityArray[i] > max) {
                max = mostActivityArray[i];
            }
        }

        if (max == javaCompletedTasks && max != 0) {
            highestActivity.add("Java");
        }
        if (max == dsaCompletedTasks && max != 0) {
            highestActivity.add("DSA");
        }
        if (max == databasesCompletedTasks && max != 0) {
            highestActivity.add("Databases");
        }
        if (max == springCompletedTasks && max != 0) {
            highestActivity.add("Spring");
        }

        if (highestActivity.isEmpty()) {
            result = "n/a";
        } else {
            result = String.join(", ", highestActivity);
        }

        return result;
    }

    public String getLowestActivityCourse() {
        List<String> lowestActivity = new ArrayList<>();
        int javaCompletedTasks = Java.getCompletedTasks();
        int dsaCompletedTasks = DSA.getCompletedTasks();
        int databasesCompletedTasks = Databases.getCompletedTasks();
        int springCompletedTasks = Spring.getCompletedTasks();
        String result;

        int[] lowestActivityArray =
                new int[]{javaCompletedTasks, dsaCompletedTasks, databasesCompletedTasks, springCompletedTasks};
        int min = lowestActivityArray[0];
        for (int i = 1; i < 4; i++) {
            if (lowestActivityArray[i] < min) {
                min = lowestActivityArray[i];
            }
        }

        if (min == javaCompletedTasks || javaCompletedTasks == 0) {
            lowestActivity.add("Java");
        }
        if (min == dsaCompletedTasks || dsaCompletedTasks == 0) {
            lowestActivity.add("DSA");
        }
        if (min == databasesCompletedTasks || databasesCompletedTasks == 0) {
            lowestActivity.add("Databases");
        }
        if (min == springCompletedTasks || springCompletedTasks == 0) {
            lowestActivity.add("Spring");
        }

        if (javaCompletedTasks == 0 && dsaCompletedTasks == 0
                && databasesCompletedTasks == 0 && springCompletedTasks == 0
                || String.join(", ", lowestActivity).equals(getHighestActivityCourse())) {
            result = "n/a";
        } else {
            result = String.join(", ", lowestActivity);
        }

        return result;
    }

    public String getEasiestCourse() {
        Collection<Student> students = repository.getAllStudents().values();
        List<String> easiestCourse = new ArrayList<>();

        AtomicInteger java = new AtomicInteger();
        AtomicInteger dsa = new AtomicInteger();
        AtomicInteger databases = new AtomicInteger();
        AtomicInteger spring = new AtomicInteger();
        String result;

        students.forEach(s -> {
            java.addAndGet(s.getCourses().get(0).getPoints());
            dsa.addAndGet(s.getCourses().get(1).getPoints());
            databases.addAndGet(s.getCourses().get(2).getPoints());
            spring.addAndGet(s.getCourses().get(3).getPoints());
        });

        double[] coursePoints = new double[]{java.doubleValue() / Java.getCompletedTasks(),
                dsa.doubleValue() / DSA.getCompletedTasks(),
                databases.doubleValue() / Databases.getCompletedTasks(),
                spring.doubleValue() / Spring.getCompletedTasks()};
        double max = coursePoints[0];
        for (int i = 1; i < 4; i++) {
            if (coursePoints[i] > max) {
                max = coursePoints[i];
            }
        }

        if (max == java.doubleValue() / Java.getCompletedTasks() && max != 0) {
            easiestCourse.add("Java");
        }
        if (max == dsa.doubleValue() / DSA.getCompletedTasks() && max != 0) {
            easiestCourse.add("DSA");
        }
        if (max == databases.doubleValue() / Databases.getCompletedTasks() && max != 0) {
            easiestCourse.add("Databases");
        }
        if (max == spring.doubleValue() / Spring.getCompletedTasks() && max != 0) {
            easiestCourse.add("Spring");
        }

        if (easiestCourse.isEmpty()) {
            result = "n/a";
        } else {
            result = String.join(", ", easiestCourse);
        }

        return result;
    }

    public String getHardestCourse() {
        Collection<Student> students = repository.getAllStudents().values();
        List<String> hardestCourse = new ArrayList<>();

        AtomicInteger java = new AtomicInteger();
        AtomicInteger dsa = new AtomicInteger();
        AtomicInteger databases = new AtomicInteger();
        AtomicInteger spring = new AtomicInteger();
        String result;

        students.forEach(s -> {
            java.addAndGet(s.getCourses().get(0).getPoints());
            dsa.addAndGet(s.getCourses().get(1).getPoints());
            databases.addAndGet(s.getCourses().get(2).getPoints());
            spring.addAndGet(s.getCourses().get(3).getPoints());
        });

        double[] coursePoints = new double[]{java.doubleValue() / Java.getCompletedTasks(),
                dsa.doubleValue() / DSA.getCompletedTasks(),
                databases.doubleValue() / Databases.getCompletedTasks(),
                spring.doubleValue() / Spring.getCompletedTasks()};
        double min = coursePoints[0];
        for (int i = 1; i < 4; i++) {
            if (coursePoints[i] < min) {
                min = coursePoints[i];
            }
        }

        if (min == java.doubleValue() / Java.getCompletedTasks()) {
            hardestCourse.add("Java");
        }
        if (min == dsa.doubleValue() / DSA.getCompletedTasks()) {
            hardestCourse.add("DSA");
        }
        if (min == databases.doubleValue() / Databases.getCompletedTasks()) {
            hardestCourse.add("Databases");
        }
        if (min == spring.doubleValue() / Spring.getCompletedTasks()) {
            hardestCourse.add("Spring");
        }

        if (hardestCourse.isEmpty()) {
            result = "n/a";
        } else {
            result = String.join(", ", hardestCourse);
        }

        return result;
    }

    public void printJavaStats() {
        List<Student> students = new ArrayList<>(repository.getAllStudents().values());
        System.out.println("Java\nid\tpoints\tcompleted");
        students.sort(Comparator.comparing(Student::getJavaPoints)
                .reversed()
                .thenComparing(Student::getId));
        students.forEach(s -> {
                    if (s.getJavaPoints() > 0) {
                        System.out.println(s.getId() + "\t" + s.getJavaPoints() +
                                "\t\t" + s.getCourses().get(0).getCourseCompletion() + "%");
                    }
                }
        );
    }

    public void printDsaStats() {
        List<Student> students = new ArrayList<>(repository.getAllStudents().values());
        System.out.println("DSA\nid\tpoints\tcompleted");
        students.sort(Comparator.comparing(Student::getDsaPoints)
                .reversed()
                .thenComparing(Student::getId));
        students.forEach(s -> {
                    if (s.getDsaPoints() > 0) {
                        System.out.println(s.getId() + "\t" + s.getDsaPoints() +
                                "\t\t" + s.getCourses().get(1).getCourseCompletion() + "%");
                    }
                }
        );
    }

    public void printDatabasesStats() {
        List<Student> students = new ArrayList<>(repository.getAllStudents().values());
        System.out.println("Databases\nid\tpoints\tcompleted");
        students.sort(Comparator.comparing(Student::getDatabasesPoints)
                .reversed()
                .thenComparing(Student::getId));
        students.forEach(s -> {
                    if (s.getDatabasesPoints() > 0) {
                        System.out.println(s.getId() + "\t" + s.getDatabasesPoints() +
                                "\t\t" + s.getCourses().get(2).getCourseCompletion() + "%");
                    }
                }
        );
    }

    public void printSpringStats() {
        List<Student> students = new ArrayList<>(repository.getAllStudents().values());
        System.out.println("Spring\nid\tpoints\tcompleted");
        students.sort(Comparator.comparing(Student::getSpringPoints)
                .reversed()
                .thenComparing(Student::getId));
        students.forEach(s -> {
                    if (s.getSpringPoints() > 0) {
                        System.out.println(s.getId() + "\t" + s.getSpringPoints() +
                                "\t\t" + s.getCourses().get(3).getCourseCompletion() + "%");
                    }
                }
        );
    }
}
