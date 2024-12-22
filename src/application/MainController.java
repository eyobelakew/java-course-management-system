package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController {

    @FXML
    private TextField txtCourseID, txtCourseName, txtInstructorName, txtDuration, txtCredits;

    @FXML
    private TableView<CourseModel> tableCourses;

    @FXML
    private TableColumn<CourseModel, String> colCourseID, colCourseName, colInstructorName;

    @FXML
    private TableColumn<CourseModel, Integer> colDuration, colCredits;

    @FXML
    private Button btnAdd, btnUpdate, btnDelete;

    private final CourseDAO courseDAO = new CourseDAO();

    private ObservableList<CourseModel> courseList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colCourseID.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colInstructorName.setCellValueFactory(new PropertyValueFactory<>("instructorName"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colCredits.setCellValueFactory(new PropertyValueFactory<>("credits"));

        loadCourses();
    }

    private void loadCourses() {
        courseList.clear();
        courseList.addAll(courseDAO.getAllCourses());
        tableCourses.setItems(courseList);
    }

    @FXML
    private void handleAdd(ActionEvent event) {
        String courseID = txtCourseID.getText();
        String courseName = txtCourseName.getText();
        String instructorName = txtInstructorName.getText();
        try {
            int duration = Integer.parseInt(txtDuration.getText());
            int credits = Integer.parseInt(txtCredits.getText());

            CourseModel course = new CourseModel(courseID, courseName, instructorName, duration, credits);
            courseDAO.addCourse(course);
            loadCourses();
            clearFields();
        } catch (NumberFormatException e) {
            showAlert("Please enter valid numbers for duration and credits.");
        }
    }

    @FXML
    private void handleUpdate(ActionEvent event) {
        CourseModel selectedCourse = tableCourses.getSelectionModel().getSelectedItem();
        if (selectedCourse != null) {
            selectedCourse.setCourseID(txtCourseID.getText());
            selectedCourse.setCourseName(txtCourseName.getText());
            selectedCourse.setInstructorName(txtInstructorName.getText());
            try {
                selectedCourse.setDuration(Integer.parseInt(txtDuration.getText()));
                selectedCourse.setCredits(Integer.parseInt(txtCredits.getText()));
                courseDAO.updateCourse(selectedCourse);
                loadCourses();
                clearFields();
            } catch (NumberFormatException e) {
                showAlert("Please enter valid numbers for duration and credits.");
            }
        } else {
            showAlert("Please select a course to update.");
        }
    }

    @FXML
    private void handleDelete(ActionEvent event) {
        CourseModel selectedCourse = tableCourses.getSelectionModel().getSelectedItem();
        if (selectedCourse != null) {
            courseDAO.deleteCourse(selectedCourse.getCourseID());
            loadCourses();
            clearFields();
        } else {
            showAlert("Please select a course to delete.");
        }
    }

    private void clearFields() {
        txtCourseID.clear();
        txtCourseName.clear();
        txtInstructorName.clear();
        txtDuration.clear();
        txtCredits.clear();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
