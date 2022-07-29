package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "Registration")

public class Registration implements Serializable {
    private static final long serialVersionUID = 2082130701112248231L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String FirstName;
    private String LastName;
    private String Client_ID;
    private String Email;
    private String PhoneNumber;
    private String UserName;
    private String Password;
    private String Status;
    private String CreditCard;
    private String ExpiryDate;
    private String AccountType;
    private Boolean Registered;
    private double Refund;
    private String SelectedStore;

    public Registration(String userName, String lastName, String firstName, String status) {
        FirstName = firstName;
        LastName = lastName;
        UserName = userName;
        Status = status;
    }
    public Registration(String firstName, String lastName, String ID, String email, String phoneNumber, String userName, String password, String status, String creditCard, String expiryDate, String accountType, double refund,String selectedStore) {
        FirstName = firstName;
        LastName = lastName;
        this.Client_ID = ID;
        Email = email;
        PhoneNumber = phoneNumber;
        UserName = userName;
        Password = password;
        Status = status;
        CreditCard = creditCard;
        ExpiryDate = expiryDate;
        AccountType = accountType;
        setRegistered(false);
        Refund = refund;
//        setRegistered(false);
        this.SelectedStore=selectedStore;

//      Registered = registered;
//        purchases = new ArrayList<Purchase>();
    }

    public Registration(String firstName, String lastName, String ID, String email, String phoneNumber, String userName, String password, String status, String creditCard, String expiryDate, String accountType, double refund) {
        FirstName = firstName;
        LastName = lastName;
        this.Client_ID = ID;
        Email = email;
        PhoneNumber = phoneNumber;
        UserName = userName;
        Password = password;
        Status = status;
        CreditCard = creditCard;
        ExpiryDate = expiryDate;
        AccountType = accountType;
        setRegistered(false);
        Refund = refund;
//        setRegistered(false);

//      Registered = registered;
//        purchases = new ArrayList<Purchase>();
    }

    public Registration(String firstName, String lastName, String client_ID, String email, String phoneNumber, String userName, String password, String status, String creditCard, String expiryDate, String accountType, Boolean registered, double refund, String selectedStore) {
        FirstName = firstName;
        LastName = lastName;
        Client_ID = client_ID;
        Email = email;
        PhoneNumber = phoneNumber;
        UserName = userName;
        Password = password;
        Status = status;
        CreditCard = creditCard;
        ExpiryDate = expiryDate;
        AccountType = accountType;
        Registered = registered;
        Refund = refund;
        SelectedStore = selectedStore;
    }

    public Registration(String firstName, String lastName, String client_ID, String email, String phoneNumber, String userName, String password, String status, String creditCard, String expiryDate, String accountType, Boolean registered, double refund) {
        FirstName = firstName;
        LastName = lastName;
        Client_ID = client_ID;
        Email = email;
        PhoneNumber = phoneNumber;
        UserName = userName;
        Password = password;
        Status = status;
        CreditCard = creditCard;
        ExpiryDate = expiryDate;
        AccountType = accountType;
        Registered = registered;
        Refund = refund;
    }


    public Registration() {

    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getClient_ID() {
        return Client_ID;
    }

    public void setClient_ID(String ID) {
        this.Client_ID = ID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getCreditCard() {
        return CreditCard;
    }

    public void setCreditCard(String creditCard) {
        CreditCard = creditCard;
    }

    public String getExpiryDate() {
        return ExpiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        ExpiryDate = expiryDate;
    }

    public String getAccountType() {
        return AccountType;
    }

    public void setAccountType(String accountType) {
        AccountType = accountType;
    }

    public Boolean getRegistered() {
        return Registered;
    }

    public void setRegistered(Boolean registered) {
        Registered = registered;
    }

    public double getRefund() { return Refund; }

    public void setRefund(double refund) { Refund = refund; }

    public String getSelectedStore() { return SelectedStore; }

    public void setSelectedStore(String selectedStore) { SelectedStore = selectedStore; }
}
