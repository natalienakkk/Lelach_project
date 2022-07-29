package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "complains")

public class Complain implements Serializable {

    private static final long serialVersionUID= -1511178949604528461L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderID;
    private LocalDate date;
    private LocalTime time;
    private String status;
    private String message;
    private String complain_type;
    private double refund;
    private String answer;
    private String shopname;
    private LocalDate deadline_date;
    private LocalTime deadline_time;

    public LocalDate getDeadline_date() {
        return deadline_date;
    }

    public void setDeadline_date(LocalDate deadline_date) {
        this.deadline_date = deadline_date;
    }

    public LocalTime getDeadline_time() {
        return deadline_time;
    }

    public void setDeadline_time(LocalTime deadline_time) {
        this.deadline_time = deadline_time;
    }



    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public Complain(String message, String status, double refund, String answer) {
        this.status = status;
        this.message = message;
        this.refund = refund;
        this.answer = answer;
    }

    private String username;


    public Complain(String complain_type, LocalDate date, LocalTime time, String status, String username, Long orderID, String message) {
        this.username=username;
        this.date = date;
        this.time = time;
        this.status = status;
        this.message = message;
        this.complain_type = complain_type;
        this.orderID=orderID;
    }


    public Complain(String complain_type, LocalDate date, LocalTime time, String status, String username,Long id, Long orderID,String message,LocalDate deadline_date,LocalTime deadline_time) {
        this.username=username;
        this.date = date;
        this.time = time;
        this.status = status;
        this.message = message;
        this.complain_type = complain_type;
        this.orderID=orderID;
        this.id=id;
        this.deadline_date=deadline_date;
        this.deadline_time=deadline_time;
    }

    public Complain(String complain_type, LocalDate date, LocalTime time, String status, String username,Long id, Long orderID,String message) {
        this.username=username;
        this.date = date;
        this.time = time;
        this.status = status;
        this.message = message;
        this.complain_type = complain_type;
        this.orderID=orderID;
        this.id=id;
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
//do not forget this//////
    // @ManyToOne


    public Complain(String username ,LocalDate date, LocalTime time, String status, String message, String complain_type,Long orderID) {
        this.username=username;
        this.date = date;
        this.time = time;
        this.status = status;
        this.message = message;
        this.complain_type = complain_type;
        this.orderID=orderID;
    }

    public Complain(String username,LocalDate date, LocalTime time, String status, String message, String complain_type, double refund,Long orderID) {
        this.date = date;
        this.time = time;
        this.status = status;
        this.message = message;
        this.complain_type = complain_type;
        this.refund = refund;
        this.orderID=orderID;
        this.username=username;
    }

    public Complain(String username,LocalDate date, LocalTime time, String status, String message, String complain_type) {
        this.date = date;
        this.time = time;
        this.status = status;
        this.message = message;
        this.complain_type = complain_type;
        this.username=username;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public Complain(String username,LocalDate date, LocalTime time, String status, String message, String answer, String complain_type, double refund,Long orderID) {
        this.date = date;
        this.time = time;
        this.status = status;
        this.message = message;
        this.answer = answer;
        this.complain_type = complain_type;
        this.refund = refund;
        this.orderID=orderID;
        this.username=username;
    }

    public Complain() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getComplain_type() {
        return complain_type;
    }

    public void setComplain_type(String complain_type) {
        this.complain_type = complain_type;
    }

    public double getRefund() {
        return refund;
    }

    public void setRefund(double refund) {
        this.refund = refund;
    }





}
