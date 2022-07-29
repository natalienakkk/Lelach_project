package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Registration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

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

    @FXML
    private Tooltip AccountTypeToolTip;

    private String[] Accounts = {"Store Account", "Chain Account: Shop in any Store", "One year subscription"};

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
    private Text SelectStoreText;

    @FXML
    private ChoiceBox<String> StoreSelection;

    private String[] Stores = {"Lelach, Haifa" , "Lelach, Tel Aviv"};

    @FXML
    private TextArea ChainAccountText;

    @FXML
    private CheckBox SubscriptionCheck;

    @FXML
    private TextArea SubscriptionText;


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

    boolean CheckInputs(String password, String ID1, String PhoneNum) {
//        try {
//            SimpleClient.getClient().sendToServer(new Message("#CheckID", ID1));
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        if (!password.matches("[a-zA-Z0-9]+")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Illegal Password");
            alert.setContentText("Please enter new Password: it must contain letters and numbers only");
//            Password.setText("Enter new Password: it most contain letters and numbers only");
//            alert.showAndWait();
            alert.show();
            Password.setText("");
            return false;
        }  if (ID1.length() != 9 || !ID1.matches("[0-9]+")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Illegal ID");
            alert.setContentText("Please enter your ID");
            alert.showAndWait();
            ID.setText("");
            return false;
        }  if (PhoneNum.length() != 10) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Illegal Phone Number");
            alert.setContentText("Please enter your Phone Number");
            alert.showAndWait();
            PhoneNumber.setText("");
            return false;
        }
        if(AccountType.getValue().equalsIgnoreCase("One year subscription"))
        {
            if(!SubscriptionCheck.isSelected())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Please confirm charges");
                alert.setContentText("Please check confirmation account type");
                alert.showAndWait();
                ID.setText("");
                return false;
            }

        }
        return true;
    }


    @FXML
    void SignUp(ActionEvent event) {
        AccountType.setTooltip(AccountTypeToolTip);
        if(Password.getText() == "" || FirstName.getText() == "" || LastName.getText()=="" || ID.getText()=="" ||
                Email.getText() == "" || PhoneNumber.getText()=="" || UserName.getText()=="" ||  Password.getText()=="" ||
                CreditCard.getText()== "" || ExpiryDate.getValue().toString() == "" || AccountType.getValue() == "")
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Fields");
            alert.setContentText("Please fill out all required fields");
            alert.showAndWait();
        }
        else if(CheckInputs(Password.getText(), ID.getText(), PhoneNumber.getText()))
        {
            Registration newClient;
            if(AccountType.getValue().equalsIgnoreCase("Store Account"))
            {
                newClient = new Registration(FirstName.getText(), LastName.getText(), ID.getText(),
                        Email.getText(), PhoneNumber.getText(), UserName.getText(), Password.getText(),
                        "Client", CreditCard.getText(), ExpiryDate.getValue().toString(),
                        AccountType.getValue().toString(),false , 0, StoreSelection.getValue());

            }
            else
            {
                newClient = new Registration(FirstName.getText(), LastName.getText(), ID.getText(),
                        Email.getText(), PhoneNumber.getText(), UserName.getText(), Password.getText(),
                        "Client", CreditCard.getText(), ExpiryDate.getValue().toString(),
                        AccountType.getValue().toString(),false , 0, "");

            }
            try {
                SimpleClient.getClient().sendToServer(new Message("#SignUpRequest", newClient));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
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
        assert AccountTypeToolTip != null : "fx:id=\"AccountTypeToolTip\" was not injected: check your FXML file 'SingUp.fxml'.";
        assert StoreSelection != null : "fx:id=\"StoreSelection\" was not injected: check your FXML file 'SingUp.fxml'.";
        assert SubscriptionCheck != null : "fx:id=\"SubscriptionCheck\" was not injected: check your FXML file 'SingUp.fxml'.";
        assert SubscriptionText != null : "fx:id=\"SubscriptionText\" was not injected: check your FXML file 'SingUp.fxml'.";

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        SelectStoreText.setVisible(false);
        StoreSelection.setVisible(false);
        ChainAccountText.setVisible(false);
        SubscriptionCheck.setVisible(false);
        SubscriptionText.setVisible(false);

        AccountType.getItems().addAll(Accounts);
        StoreSelection.getItems().addAll(Stores);

        AccountType.setOnAction((event) -> {
            String AccountSelection = AccountType.getValue();
            if(AccountSelection.equalsIgnoreCase("Store Account"))
            {
                System.out.println("   ChoiceBox.getValue(): " + AccountSelection);
                SelectStoreText.setVisible(true);
                StoreSelection.setVisible(true);
                ChainAccountText.setVisible(false);
                SubscriptionCheck.setVisible(false);
                SubscriptionText.setVisible(false);
            }
            else if(AccountSelection.equalsIgnoreCase("Chain Account: Shop in any Store"))
            {
                System.out.println("   ChoiceBox.getValue(): " + AccountSelection);
                ChainAccountText.setVisible(true);
                SelectStoreText.setVisible(false);
                StoreSelection.setVisible(false);
                SubscriptionCheck.setVisible(false);
                SubscriptionText.setVisible(false);
            }
            else if(AccountSelection.equalsIgnoreCase("One year subscription"))
            {
                System.out.println("   ChoiceBox.getValue(): " + AccountSelection);
                SubscriptionText.setVisible(true);
                SubscriptionCheck.setVisible(true);
                SelectStoreText.setVisible(false);
                StoreSelection.setVisible(false);
                ChainAccountText.setVisible(false);
            }
        });

        ExpiryDate.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.compareTo(today) < 0 );
            }
        });
    }
}

