package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.database.DatabaseConnection;
import sample.models.MedicineTable;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PharmacistController implements Initializable{

    @FXML
    private TableColumn<MedicineTable, String> active_col;

    @FXML
    private JFXButton add_button;

    @FXML
    private TableColumn<MedicineTable, Integer> amount_col;

    @FXML
    private JFXButton chat_button;

    @FXML
    private TableColumn<MedicineTable, Double> cost_col;

    @FXML
    private JFXButton delete_button;

    @FXML
    private JFXButton edit_button;

    @FXML
    private Label employee_text;

    @FXML
    private JFXButton exit_button;

    @FXML
    private TableColumn<MedicineTable, Integer> id_col;

    @FXML
    private TableView<MedicineTable> medicineTable;

    @FXML
    private TableColumn<MedicineTable, String> name_col;

    @FXML
    private JFXButton order_button;

    @FXML
    private JFXButton reload;

    @FXML
    private TextField search_field;

    @FXML
    private TableColumn<MedicineTable, String> symptop_col;

ObservableList<MedicineTable> medicine_list = FXCollections.observableArrayList();

    public void setMedicineTable(){
        medicineTable.getItems().clear();

        String sql = "select * from drugs";
        try {
            ResultSet rst = DatabaseConnection.getConnection().createStatement().executeQuery(sql);

            while (rst.next()) {
                medicine_list.add(new MedicineTable(Integer.parseInt(rst.getString("id")), rst.getString("name"), rst.getString("active"), Integer.parseInt(rst.getString("amount")),
                        Double.parseDouble(rst.getString("cost")), rst.getString("symptoms")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        id_col.setCellValueFactory(new PropertyValueFactory<MedicineTable, Integer>("id"));
        name_col.setCellValueFactory(new PropertyValueFactory<MedicineTable, String>("name"));
        active_col.setCellValueFactory(new PropertyValueFactory<MedicineTable, String>("active"));
        cost_col.setCellValueFactory(new PropertyValueFactory<MedicineTable, Double>("cost"));
        amount_col.setCellValueFactory(new PropertyValueFactory<MedicineTable, Integer>("amount"));
        symptop_col.setCellValueFactory(new PropertyValueFactory<MedicineTable, String>("symptoms"));

        medicineTable.setItems(medicine_list);



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setMedicineTable();

    }
}
