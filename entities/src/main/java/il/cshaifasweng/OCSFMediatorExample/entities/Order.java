package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")

public class Order implements Serializable {

    private static final long serialVersionUID = 7144712553170938007L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private String recievedate;
    private String recievetime;
    private String card;
    private String deliveryOp;
    private String clientid;
    private String clientname;
    private String clientmail;
    private String receiveraddress;
    private String receivername;
    private String receivermail;
    private String totalprice;
    private String status;
    private String note;
    private String items1;



    @OneToOne
            (cascade = CascadeType.ALL)
    @JoinColumn(name = "Shoppingcart_id")
    private ShoppingCart cart;

//    public Order(String date, String recievedate, String card, String deliveryOp, String clientid, String clientname, String totalprice, String status,ShoppingCart cart) {
//        this.date = date;
//        this.recievedate = recievedate;
//        this.card = card;
//        this.deliveryOp = deliveryOp;
//        this.clientid = clientid;
//        this.clientname = clientname;
//        this.totalprice = totalprice;
//        this.status = status;
//        this.cart = cart;
//    }
    public Order( String status, Long id, String recievedate ,String totalprice, String date, String items1) {
        this.id = id;
        this.date = date;
        this.recievedate = recievedate;
        this.totalprice = totalprice;
        this.status = status;
        this.items1 = items1;
    }

    public Order(String date, String recievedate ,String card, String deliveryOp, String clientid, String clientname, String receiveraddress, String receivername,String receivermail, String totalprice, String status, ShoppingCart cart,String Note) {
        this.date = date;
        this.recievedate = recievedate;
        this.card = card;
        this.deliveryOp = deliveryOp;
        this.clientid = clientid;
        this.clientname = clientname;
        this.receiveraddress = receiveraddress;
        this.receivername = receivername;
        this.receivermail = receivermail;
        this.totalprice = totalprice;
        this.status = status;
        this.note = note;
        this.cart = cart;
    }
    public Order(String date, String recievedate,String recievetime, String card, String deliveryOp, String clientid, String clientname, String clientmail,String receiveraddress, String receivername,String receivermail, String totalprice, String status, String note) {
        this.date = date;
        this.recievedate = recievedate;
        this.recievetime = recievetime;
        this.card = card;
        this.deliveryOp = deliveryOp;
        this.clientid = clientid;
        this.clientname = clientname;
        this.clientmail = clientmail;
        this.receiveraddress = receiveraddress;
        this.receivername = receivername;
        this.receivermail = receivermail;
        this.totalprice = totalprice;
        this.status = status;
        this.note = note;
    }
    public Order(String date, String recievedate,String recievetime, String card, String deliveryOp, String clientid, String clientname, String receiveraddress, String receivername,String receivermail, String totalprice, String status, ShoppingCart cart) {
        this.date = date;
        this.recievedate = recievedate;
        this.recievetime = recievetime;
        this.card = card;
        this.deliveryOp = deliveryOp;
        this.clientid = clientid;
        this.clientname = clientname;
        this.receiveraddress = receiveraddress;
        this.receivername = receivername;
        this.receivermail = receivermail;
        this.totalprice = totalprice;
        this.status = status;
        this.cart = cart;
    }
    public Order(String date, String recievedate,String recievetime, String card, String deliveryOp, String clientid, String clientname,String clientmail, String receiveraddress, String receivername,String receivermail, String totalprice, String status) {
        this.date = date;
        this.recievedate = recievedate;
        this.recievetime = recievetime;
        this.card = card;
        this.deliveryOp = deliveryOp;
        this.clientid = clientid;
        this.clientname = clientname;
        this.clientmail = clientmail;
        this.receiveraddress = receiveraddress;
        this.receivername = receivername;
        this.receivermail = receivermail;
        this.totalprice = totalprice;
        this.status = status;
    }

    public Order(Long id, String recievetime, String recievedate, String clientid, String totalprice, String status) {
        this.id = id;
        this.recievedate = recievedate;
        this.clientid = clientid;
        this.totalprice = totalprice;
        this.status = status;
        this.recievetime = recievetime;
    }

    public Order() {

    }

    public String getItems1() {
        return items1;
    }

    public void setItems1(String items1) {
        this.items1 = items1;
    }

    public ShoppingCart getCart() { return cart; }

    public void setCart(ShoppingCart cart) { this.cart = cart; }

    public static long getSerialVersionUID() { return serialVersionUID; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    public String getRecievedate() { return recievedate; }

    public void setRecievedate(String recievedate) { this.recievedate = recievedate; }

    public String getCard() { return card; }

    public void setCard(String card) { this.card = card; }

    public String getDeliveryOp() { return deliveryOp; }

    public void setDeliveryOp(String deliveryOp) { this.deliveryOp = deliveryOp; }

    public String getClientid() { return clientid; }

    public void setClientid(String clientid) { this.clientid = clientid; }

    public String getClientname() { return clientname; }

    public void setClientname(String clientname) { this.clientname = clientname; }

    public String getReceiveraddress() { return receiveraddress; }

    public void setReceiveraddress(String receiveraddress) { this.receiveraddress = receiveraddress; }

    public String getReceivername() { return receivername; }

    public void setReceivername(String receivername) { this.receivername = receivername; }

    public String getTotalprice() { return totalprice; }

    public void setTotalprice(String totalprice) { this.totalprice = totalprice; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public String getNote() { return note; }

    public void setNote(String note) { this.note = note; }

    public String getReceivermail() { return receivermail; }

    public void setReceivermail(String receivermail) { this.receivermail = receivermail; }

    public String getRecievetime() { return recievetime; }

    public void setRecievetime(String recievetime) { this.recievetime = recievetime; }

    public String getClientmail() { return clientmail; }

    public void setClientmail(String clientmail) { this.clientmail = clientmail; }
}
