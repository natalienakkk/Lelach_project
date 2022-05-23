package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Registration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> AccountType;

    private String[] Accounts = {"Store Account", "Chain Account: Shop in any Store", "One year subscription: Pay 100nis and get 10% discount in every purchase over 50nis"};

    @FXML
    private Button Back;

    @FXML
    private TextField CreditCard;

    @FXML
    private TextField Email;

    @FXML
    private DatePicker ExpiryDate;

    @FXML
    private TextField FirstName;

    @FXML
    private TextField ID;

    @FXML
    private TextField LastName;

    @FXML
    private AnchorPane Pane1;

    @FXML
    private TextField Password;

    @FXML
    private TextField PhoneNumber;

    @FXML
    private Button SignUp;

    @FXML
    private TextField UserName;

    @FXML
    void Back(ActionEvent event) throws IOException {
        try {
            App.setRoot("HomePage");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void ExpiryDate(ActionEvent event) {
        LocalDate Expiry_Date = ExpiryDate.getValue();
    }

    void CheckInputs(String password, String ID1, String PhoneNum) {
//        try {
//            SimpleClient.getClient().sendToServer(new Message("#CheckID", ID1));
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        if (!password.matches("[a-zA-Z0-9]+")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Illegal Password");
            alert.setContentText("Please enter new Password: it most contain letters and numbers only");
//            Password.setText("Enter new Password: it most contain letters and numbers only");
//            alert.showAndWait();
            alert.show();
            Password.setText("");
        } else if (ID1.length() != 9 || !ID1.matches("[0-9]+")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Illegal ID");
            alert.setContentText("Please enter your ID");
            alert.showAndWait();
            ID.setText("");
        } else if (PhoneNum.length() != 10) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Illegal Phone Number");
            alert.setContentText("Please enter your Phone Number");
            alert.showAndWait();
            PhoneNumber.setText("");
        }
    }

    @FXML
    void SignUp(ActionEvent event) {
//        String password = Password.getText();
//        String Id = ID.getText();
//        String PhoneNum = PhoneNumber.getText();

        CheckInputs(Password.getText(), ID.getText(), PhoneNumber.getText());
        Registration newClient = new Registration(FirstName.getText(), LastName.getText(), ID.getText(),
                Email.getText(), PhoneNumber.getText(), UserName.getText(), Password.getText(), "Client",
                CreditCard.getText(), ExpiryDate.getValue().toString(), AccountType.getValue().toString());
        try {
            SimpleClient.getClient().sendToServer(new Message("#SignUpRequest", newClient));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @FXML
    void initialize() {
        assert AccountType != null : "fx:id=\"AccountType\" was not injected: check your FXML file 'SignUp.fxml'.";
        assert CreditCard != null : "fx:id=\"CreditCard\" was not injected: check your FXML file 'SignUp.fxml'.";
        assert Email != null : "fx:id=\"Email\" was not injected: check your FXML file 'SignUp.fxml'.";
        assert ExpiryDate != null : "fx:id=\"ExpiryDate\" was not injected: check your FXML file 'SignUp.fxml'.";
        assert FirstName != null : "fx:id=\"FirstName\" was not injected: check your FXML file 'SignUp.fxml'.";
        assert ID != null : "fx:id=\"ID\" was not injected: check your FXML file 'SignUp.fxml'.";
        assert LastName != null : "fx:id=\"LastName\" was not injected: check your FXML file 'SignUp.fxml'.";
        assert Password != null : "fx:id=\"Password\" was not injected: check your FXML file 'SignUp.fxml'.";
        assert PhoneNumber != null : "fx:id=\"PhoneNumber\" was not injected: check your FXML file 'SignUp.fxml'.";
        assert UserName != null : "fx:id=\"UserName\" was not injected: check your FXML file 'SignUp.fxml'.";

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        String SelectedAccount;
        AccountType.getItems().addAll(Accounts);
//        SelectedAccount = AccountType.getValue();
    }
}
