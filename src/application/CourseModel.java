package application;

public class CourseModel {
    private String courseID;  // Changed to String to accept alphanumeric
    private String courseName;
    private String instructorName;
    private int duration;
    private int credits;

    // Constructor now accepts String for CourseID
    public CourseModel(String courseID, String courseName, String instructorName, int duration, int credits) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.instructorName = instructorName;
        this.duration = duration;
        this.credits = credits;
    }

    // Getters and setters for CourseID (now String)
    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    // Getters and setters for other fields remain unchanged
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
