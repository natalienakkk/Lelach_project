package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Complain;
import il.cshaifasweng.OCSFMediatorExample.entities.Order;
import il.cshaifasweng.OCSFMediatorExample.entities.Registration;
import il.cshaifasweng.OCSFMediatorExample.entities.Report;

import java.time.LocalDate;
import java.util.List;

public class ReportEvent {
    private String type;
    private LocalDate start_date;
    private LocalDate end_date;
    private List<Report> report_list;

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

    private List<Order> order_list;
    private List<Complain>complain_list;
    private List<Registration>registrations;
    private String shop_name;

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public List<Order> getOrder_list() {
        return order_list;
    }

    public void setOrder_list(List<Order> order_list) {
        this.order_list = order_list;
    }

    public ReportEvent(String type, LocalDate start_date, LocalDate end_date, List<Order>order_list ) {
        this.type = type;
        this.start_date = start_date;
        this.end_date = end_date;
        this.order_list = order_list;
    }

    public ReportEvent(String type, LocalDate start_date, LocalDate end_date, List<Order>order_list , String shop_name,List<Registration>registrations) {
        this.type = type;
        this.start_date = start_date;
        this.end_date = end_date;
        this.order_list = order_list;
        this.shop_name=shop_name;
        this.registrations=registrations;
    }

    private LocalDate start_date2;

    public List<Complain> getComplain_list() { return complain_list; }

    public ReportEvent(String type, LocalDate start_date, LocalDate end_date, List<Report> report_list, List<Complain> complain_list,String shop_name,List<Registration>registrations) {
        this.type = type;
        this.start_date = start_date;
        this.end_date = end_date;
        this.report_list = report_list;
        this.complain_list = complain_list;
        this.shop_name=shop_name;
        this.registrations=registrations;
    }

    private LocalDate end_date2;

    public LocalDate getStart_date2() {
        return start_date2;
    }

    public void setStart_date2(LocalDate start_date2) {
        this.start_date2 = start_date2;
    }

    public LocalDate getEnd_date2() {
        return end_date2;
    }

    public void setEnd_date2(LocalDate end_date2) {
        this.end_date2 = end_date2;
    }


    public  List<Report> getReport_list() {
        return report_list;
    }

    public  void setReport_list(List<Report> report_list) {
        this.report_list = report_list;
    }


    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }


    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public ReportEvent(String type, LocalDate start_date, LocalDate end_date, LocalDate start_date2, LocalDate end_date2, List<Complain> complain_list,String shop_name,List<Registration>registrations) {
        this.type = type;
        this.start_date = start_date;
        this.end_date = end_date;
        this.complain_list = complain_list;
        this.start_date2 = start_date2;
        this.end_date2 = end_date2;
        this.shop_name=shop_name;
        this.registrations=registrations;
    }

    public ReportEvent(List<Order> order_list, LocalDate start_date, LocalDate end_date, LocalDate start_date2, LocalDate end_date2,String type,String shop_name,List<Registration>registrations) {
        this.type = type;
        this.start_date = start_date;
        this.end_date = end_date;
        this.order_list = order_list;
        this.start_date2 = start_date2;
        this.end_date2 = end_date2;
        this.shop_name=shop_name;
        this.registrations=registrations;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }


}
