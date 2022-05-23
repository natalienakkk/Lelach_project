package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Report;

import java.util.List;

public class ReportEvent {
    private String type;
    private String start_date;
    private String end_date;
    private List<Report> report_list;
    private String start_date2;
    private String end_date2;

    public String getStart_date2() {
        return start_date2;
    }

    public void setStart_date2(String start_date2) {
        this.start_date2 = start_date2;
    }

    public String getEnd_date2() {
        return end_date2;
    }

    public void setEnd_date2(String end_date2) {
        this.end_date2 = end_date2;
    }


    public  List<Report> getReport_list() {
        return report_list;
    }

    public  void setReport_list(List<Report> report_list) {
        this.report_list = report_list;
    }


    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }


    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }


    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }



    public ReportEvent(String type, String start_date, String end_date, List<Report> report_list) {
        this.type = type;
        this.start_date = start_date;
        this.end_date = end_date;
        this.report_list = report_list;
    }


    public ReportEvent(String type, String start_date, String end_date, String start_date2, String end_date2, List<Report> report_list) {
        this.type = type;
        this.start_date = start_date;
        this.end_date = end_date;
        this.report_list = report_list;
        this.start_date2 = start_date2;
        this.end_date2 = end_date2;
    }
}
