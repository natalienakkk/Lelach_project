package il.cshaifasweng.OCSFMediatorExample.client;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Registration;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;

public class ShowListForSystemManagerController {

    @FXML private static List<Registration>list;

    @FXML public static List<Registration> getList() { return list; }

    @FXML public static void setList(List<Registration> list) { ShowListForSystemManagerController.list = list; }

    @FXML private static String type;

    @FXML public static String getType() { return type; }

    @FXML public static void setType(String type) { ShowListForSystemManagerController.type = type; }


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button back_button;

    @FXML
    private TableColumn<Registration, String> name1_column;

    @FXML
    private TableColumn<Registration, String> name2_column;

    @FXML
    private Text text;

    @FXML
    private TableView<Registration> table;

    @FXML
    private TableColumn<Registration, String> type_column;

    @FXML
    private TableColumn<Registration, String> username_column;

    @FXML
    void back_butt(ActionEvent event) {
        try {
            App.setRoot("SystemManager");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ObservableList<Registration> getList2() {
        ObservableList<Registration>List=FXCollections.observableArrayList();
        if(type.equals("clients")) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getStatus().equals("Client")||(list.get(i).getStatus().equals("blocked client"))) {
                    List.add(new Registration(list.get(i).getUserName(), list.get(i).getFirstName(), list.get(i).getLastName(), list.get(i).getStatus()));
                }
            }
        }
        else if(type.equals("workers"))
        {
            for (int i = 0; i < list.size(); i++) {
                if (!(list.get(i).getStatus().equals("Client")) && !(list.get(i).getStatus().equals("blocked client"))) {
                    List.add(new Registration(list.get(i).getUserName(), list.get(i).getFirstName(), list.get(i).getLastName(), list.get(i).getStatus()));
                }
            }
        }
        return List;
    }

    @FXML
    void initialize() {
        assert name1_column != null : "fx:id=\"name1_column\" was not injected: check your FXML file 'ShowListForSystemManager.fxml'.";
        assert name2_column != null : "fx:id=\"name2_column\" was not injected: check your FXML file 'ShowListForSystemManager.fxml'.";
        assert text != null : "fx:id=\"text\" was not injected: check your FXML file 'ShowListForSystemManager.fxml'.";
        assert type_column != null : "fx:id=\"type_column\" was not injected: check your FXML file 'ShowListForSystemManager.fxml'.";
        assert username_column != null : "fx:id=\"username_column\" was not injected: check your FXML file 'ShowListForSystemManager.fxml'.";
        setList(App.getSystemManager_list());
        setType(App.getType_SystemManager());

        username_column.setCellValueFactory(new PropertyValueFactory<Registration,String>("UserName"));
        name1_column.setCellValueFactory(new PropertyValueFactory<Registration,String>("FirstName"));
        name2_column.setCellValueFactory(new PropertyValueFactory<Registration,String>("LastName"));
        type_column.setCellValueFactory(new PropertyValueFactory<Registration,String>("Status"));
        table.setItems(getList2());
        table.getColumns().addAll();

    }
}
