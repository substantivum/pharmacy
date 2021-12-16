package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.stage.Stage;
import sample.database.DatabaseConnection;
import sample.models.Supplier;
import sample.models.User;

public class Controller implements Initializable {

    @FXML
    private Label label;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane signInPane;

    @FXML
    private Pane signUpPane;

    @FXML
    private TextField signIn_login;

    @FXML
    private PasswordField signIn_pass;

    @FXML
    private JFXButton in_button;

    @FXML
    private TextField fname;

    @FXML
    private TextField lname;

    @FXML
    private TextField company;

    @FXML
    private TextField new_username;

    @FXML
    private TextField new_password;

    @FXML
    private TextField reap_pass;

    @FXML
    private JFXButton signUp_button;


    public void start(){
        signUpPane.setVisible(false);
    }

    public void OpenSignUp(MouseEvent event) {
        signInPane.setVisible(false);
        signUpPane.setVisible(true);
    }

    public void openSignIn(MouseEvent event) {
        signUpPane.setVisible(false);
        signInPane.setVisible(true);
    }

    public void changeScene(String window, Button button) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(window));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Pharmacy");
        stage.setScene(scene);
        stage.show();
    }

    public boolean Login(String user, String password){
        int count = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            String sql = "select * from user where username = '" + user + "' and password = '" + password + "'";
            ResultSet rs = DatabaseConnection.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) count += 1;
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count == 1;
    }

    public void signIn(ActionEvent event) throws IOException {

        User user = new User(signIn_login.getText().trim(), signIn_pass.getText().trim());
        int a = 0;
        if(Login(user.getLogin(), user.getPassword())) {
            String sql = "select acc_type from user where username = '" + user.getLogin() + "' and password = '" + user.getPassword() + "'";
            try {
                ResultSet rs = DatabaseConnection.getConnection().createStatement().executeQuery(sql);
                while (rs.next()) {
                    a = Integer.parseInt(rs.getString("acc_type"));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            System.out.println(a);

            if(a == 2) {
                changeScene("/sample/view/Supplier.fxml", in_button);
                in_button.getScene().getWindow().hide();
            }
            else if(a == 1){
                changeScene("/sample/view/Pharmacist.fxml", in_button);
                in_button.getScene().getWindow().hide();
            }
            label.setText("Username or password is incorrect");

        }
    }

    public void signUp(ActionEvent event) {
        Supplier supplier = new Supplier(fname.getText().trim(), lname.getText().trim(), company.getText().trim());

        if (new_password.getText().trim().equals(reap_pass.getText().trim())) {
            User user = new User(new_username.getText().trim(), new_password.getText().trim());
            String sql = "insert into supplier(fname, lname, company) values(?, ?, ?)";
            PreparedStatement prst = null;
            try {
                prst = DatabaseConnection.getConnection().prepareStatement(sql);
                prst.setString(1, supplier.getFname());
                prst.setString(2, supplier.getLname());
                prst.setString(3, supplier.getCompany());
                prst.executeUpdate();
                prst.close();

                String usersql = "insert into user(username, password, acc_type) values(?, ?, 2)";
                PreparedStatement userps = DatabaseConnection.getConnection().prepareStatement(usersql);
                userps.setString(1, user.getLogin());
                userps.setString(2, user.getPassword());
                userps.executeUpdate();
                userps.close();
                changeScene("/sample/view/Supplier.fxml", signUp_button);
                signUp_button.getScene().getWindow().hide();
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
                System.out.println("Can not add new Supplier");
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Passwords are not same");
            alert.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        start();
    }

}
