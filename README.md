# LearningProgressTracker
A [project](https://hyperskill.org/projects/197) from Java Developer Track by JetBrains Academy.
## About
Application that keeps track of the registered users, their learning progress, and metrics. It will also provide detailed information about each user or 
any category of users and the overall statistics for the entire learning platform.
## Usage
To add students use `add students` command and pass *first name*, *last name* and *email*. There are several limitations on credentials so make sure they have 
correct format.

![add_students](https://github.com/ascjke/learning-progress-tracker/blob/master/examples/add_students.jpg)

You can use `list` to output all students' id.

By `add points` you can upload student's points on each track which are Java, Data Structures and Algorithms (DSA), Databases, and Spring.

![add_points](https://github.com/ascjke/learning-progress-tracker/blob/master/examples/add_points.jpg)

Program also provide the statistics about each course and track the performance of each student in each course. Command `statistics` shows some info about tracks:
- most and least popular
- highest and lowest activity
- easiest and hardest course

To establish top learners for each course type \<course name>

![statistics](https://github.com/ascjke/learning-progress-tracker/blob/master/examples/statistics.jpg)

Finally, the `notify` command will print a list of messages to students that got required amount of points to finish a course.

![notify](https://github.com/ascjke/learning-progress-tracker/blob/master/examples/notify.jpg)
