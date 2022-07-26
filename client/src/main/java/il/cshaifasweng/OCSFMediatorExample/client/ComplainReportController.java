package il.cshaifasweng.OCSFMediatorExample.client;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Complain;
import il.cshaifasweng.OCSFMediatorExample.entities.Order;
import il.cshaifasweng.OCSFMediatorExample.entities.Registration;
import il.cshaifasweng.OCSFMediatorExample.entities.Report;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

public class ComplainReportController {

    @FXML private static List<Order> order_list;

    @FXML public static List<Order> getOrder_list() {
        return order_list;
    }

    @FXML public static void setOrder_list(List<Order> order_list) {
        ComplainReportController.order_list = order_list;
    }

    @FXML private static List<Complain> complian_list;

    @FXML public static void setComplian_list(List<Complain> complian_list) { ComplainReportController.complian_list = complian_list; }

    @FXML public static List<Registration> getRegistrations() { return registrations; }

    @FXML public static void setRegistrations(List<Registration> registrations) { ComplainReportController.registrations = registrations; }

    @FXML private static List<Registration> registrations;

    @FXML public static String type;

    @FXML public static void setType(String type) { ComplainReportController.type = type; }

    @FXML public static LocalDate start1;

    @FXML public static void setStart1(LocalDate start1) { ComplainReportController.start1 = start1; }

    @FXML public static LocalDate end1;

    @FXML public static void setEnd1(LocalDate end1) { ComplainReportController.end1 = end1; }

    @FXML public static String shopname;

    @FXML public static String getShopname() { return shopname; }

    @FXML public static void setShopname(String shopname) { ComplainReportController.shopname = shopname; }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private NumberAxis amount;

    @FXML
    private BarChart<String, Number> complain_barchart;

    @FXML
    private CategoryAxis date;

    @FXML
    private Button back;


    private int counter=0;

    Registration username;

    public Registration getUsername() { return username; }

    public void setUsername(Registration username) { this.username = username; }



    @FXML
    public void back1(javafx.event.ActionEvent actionEvent) {

        if(username.getStatus().equals("BranchManager"))
        {
            try {
                App.setRoot("BranchManager");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(username.getStatus().equals("CEO"))
        {
            try {
                App.setRoot("CEO");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    void initialize() {
        assert amount != null : "fx:id=\"amount\" was not injected: check your FXML file 'ComplainReport.fxml'.";
        assert complain_barchart != null : "fx:id=\"complain_barchart\" was not injected: check your FXML file 'ComplainReport.fxml'.";
        assert date != null : "fx:id=\"date\" was not injected: check your FXML file 'ComplainReport.fxml'.";
        assert back != null : "fx:id=\"back\" was not injected: check your FXML file 'ComplainReport.fxml'.";

        setUsername(App.getUser1());
        setComplian_list(App.getComplain_list());
        setType(App.getType());
        setStart1(App.getStart_date());
        setEnd1(App.getEnd_date());
        setShopname(App.getShopname());
        setRegistrations(App.getRegistrations());

        if(shopname.equals("Lelach")) {
            if (type.equals("Complain Report")) {
                complain_barchart.setTitle("Complaints");
                amount.setLabel("amount of complaints");
                date.setLabel("date");
                LocalDate temp = start1.minusDays(1);
                XYChart.Series<String, Number> series = new XYChart.Series<>();
                series.setName("date");
                for (int i = 0; i < complian_list.size(); i++) {
                    if ((complian_list.get(i).getDate().isAfter(start1.minusDays(1))) && (complian_list.get(i).getDate().isBefore(end1.plusDays(1))) && !(complian_list.get(i).getDate().isEqual(temp))) {
                        for (int j = 0; j < complian_list.size(); j++) {
                            if (complian_list.get(j).getDate().isEqual(complian_list.get(i).getDate())) {
                                counter++;
                            }
                        }
                        series.getData().add(new XYChart.Data<>(complian_list.get(i).getDate().toString(), counter));
                        counter = 0;
                        temp = complian_list.get(i).getDate();
                    }
                }
                complain_barchart.getData().add(series);
            } else if (type.equals("Revenue Report")) {
                complain_barchart.setTitle("Revenue");
                amount.setLabel("revenue per day");
                date.setLabel("date");
                setOrder_list(App.getOrderList());
                double totalRevenue = 0;
                LocalDate temp = start1.minusDays(1);
                XYChart.Series<String, Number> series = new XYChart.Series<>();
                series.setName("date");
                for (int i = 0; i < order_list.size(); i++) {
                    LocalDate date = LocalDate.parse(order_list.get(i).getDate());
                    if ((date.isAfter(start1.minusDays(1))) && (date.isBefore(end1.plusDays(1))) && !(date.isEqual(temp))) {
                        for (int j = 0; j < order_list.size(); j++) {
                            LocalDate date1 = LocalDate.parse(order_list.get(j).getDate());
                            if (date1.isEqual(date)) {
                                if(!(order_list.get(j).getStatus().equals("Canceled"))) {
                                    totalRevenue += Double.parseDouble(order_list.get(j).getTotalprice());
                                }
                            }
                        }
                        series.getData().add(new XYChart.Data<>(order_list.get(i).getDate(), totalRevenue));
                        totalRevenue = 0;
                        temp = date;
                    }
                }
                complain_barchart.getData().add(series);
            } else if (type.equals("Orders Report")) {
                complain_barchart.setTitle("Orders");
                amount.setLabel("items per type");
                date.setLabel("item type");
                setOrder_list(App.getOrderList());
                int type1 = 0, type2 = 0, type3 = 0, type4 = 0;
                /*atzkr an2s iom huna*/
                LocalDate temp = start1.minusDays(1);
                XYChart.Series<String, Number> series1 = new XYChart.Series<>();
                XYChart.Series<String, Number> series2 = new XYChart.Series<>();
                XYChart.Series<String, Number> series3 = new XYChart.Series<>();
                XYChart.Series<String, Number> series4 = new XYChart.Series<>();
                series1.setName("flower");
                series2.setName("Plant");
                series3.setName("Bouquet");
                series4.setName("Rose");
                for (int i = 0; i < order_list.size(); i++) {
                    LocalDate date = LocalDate.parse(order_list.get(i).getDate());
                    if ((date.isAfter(start1.minusDays(1))) && (date.isBefore(end1.plusDays(1))) && !(date.isEqual(temp))) {
                        for (int j = 0; j < order_list.size(); j++) {
                            LocalDate date1 = LocalDate.parse(order_list.get(j).getDate());
                            if (date1.isEqual(date)) {
                                for (int k = 0; k < order_list.get(j).getCart().getItems().size(); k++) {
                                    if (order_list.get(j).getCart().getItems().get(k).getType().equals("flower") && !(order_list.get(j).getStatus().equals("Canceled")))
                                        type1 += order_list.get(j).getCart().getAmount().get(k);
                                    else if (order_list.get(j).getCart().getItems().get(k).getType().equals("Plant")&& !(order_list.get(j).getStatus().equals("Canceled")))
                                        type2 += order_list.get(j).getCart().getAmount().get(k);
                                    else if (order_list.get(j).getCart().getItems().get(k).getType().equals("Bouquet")&& !(order_list.get(j).getStatus().equals("Canceled")))
                                        type3 += order_list.get(j).getCart().getAmount().get(k);
                                    else if (order_list.get(j).getCart().getItems().get(k).getType().equals("Rose")&& !(order_list.get(j).getStatus().equals("Canceled")))
                                        type4 += order_list.get(j).getCart().getAmount().get(k);
                                }
                            }
                        }
                        series1.getData().add(new XYChart.Data<>("flower", type1));
                        series2.getData().add(new XYChart.Data<>("Plant", type2));
                        series3.getData().add(new XYChart.Data<>("Bouquet", type3));
                        series4.getData().add(new XYChart.Data<>("Rose", type4));
                        type1 = 0;
                        type2 = 0;
                        type3 = 0;
                        type4 = 0;
                        temp = date;
                    }
                }
                complain_barchart.getData().add(series1);
                complain_barchart.getData().add(series2);
                complain_barchart.getData().add(series3);
                complain_barchart.getData().add(series4);
            }
        }

        if (shopname.equals("Lelach, Haifa")) {
            if (type.equals("Complain Report")) {
                complain_barchart.setTitle("Complaints");
                amount.setLabel("amount of complaints");
                date.setLabel("date");
                LocalDate temp = start1.minusDays(1);
                XYChart.Series<String, Number> series = new XYChart.Series<>();
                series.setName("date");
                for (int i = 0; i < complian_list.size(); i++) {
                    if ((complian_list.get(i).getDate().isAfter(start1.minusDays(1))) && (complian_list.get(i).getDate().isBefore(end1.plusDays(1))) && !(complian_list.get(i).getDate().isEqual(temp))) {
                        for (int j = 0; j < complian_list.size(); j++) {
                            if (complian_list.get(j).getDate().isEqual(complian_list.get(i).getDate())) {
                                for(int k=0;k<registrations.size();k++)
                                {
                                    if(complian_list.get(j).getUsername().equals(registrations.get(k).getUserName())) {
                                        if (registrations.get(k).getSelectedStore().equals("Lelach, Haifa"))
                                            counter++;
                                    }
                                }

                            }
                        }
                        series.getData().add(new XYChart.Data<>(complian_list.get(i).getDate().toString(), counter));
                        counter = 0;
                        temp = complian_list.get(i).getDate();
                    }
                }

                complain_barchart.getData().add(series);
            } else if (type.equals("Revenue Report")) {
                complain_barchart.setTitle("Revenue");
                amount.setLabel("revenue per day");
                date.setLabel("date");
                setOrder_list(App.getOrderList());
                double totalRevenue = 0;
                LocalDate temp = start1.minusDays(1);
                XYChart.Series<String, Number> series = new XYChart.Series<>();
                series.setName("date");
                for (int i = 0; i < order_list.size(); i++) {
                    LocalDate date = LocalDate.parse(order_list.get(i).getDate());
                    if ((date.isAfter(start1.minusDays(1))) && (date.isBefore(end1.plusDays(1))) && !(date.isEqual(temp))) {
                        for (int j = 0; j < order_list.size(); j++) {
                            LocalDate date1 = LocalDate.parse(order_list.get(j).getDate());
                            if (date1.isEqual(date)) {
                                for(int k=0;k<registrations.size();k++)
                                {
                                    if(order_list.get(j).getClientname().equals(registrations.get(k).getUserName())) {
                                        if (registrations.get(k).getSelectedStore().equals("Lelach, Haifa")) {
                                            if(!(order_list.get(j).getStatus().equals("Canceled")))
                                                totalRevenue += Double.parseDouble(order_list.get(j).getTotalprice());
                                        }
                                    }
                                }

                            }
                        }
                        series.getData().add(new XYChart.Data<>(order_list.get(i).getDate(), totalRevenue));
                        totalRevenue = 0;
                        temp = date;
                    }
                }
                complain_barchart.getData().add(series);
            } else if (type.equals("Orders Report")) {
                complain_barchart.setTitle("Orders");
                amount.setLabel("items per type");
                date.setLabel("item type");
                setOrder_list(App.getOrderList());
                int type1 = 0, type2 = 0, type3 = 0, type4 = 0;
                LocalDate temp = start1.minusDays(1);
                XYChart.Series<String, Number> series1 = new XYChart.Series<>();
                XYChart.Series<String, Number> series2 = new XYChart.Series<>();
                XYChart.Series<String, Number> series3 = new XYChart.Series<>();
                XYChart.Series<String, Number> series4 = new XYChart.Series<>();
                series1.setName("flower");
                series2.setName("Plant");
                series3.setName("Bouquet");
                series4.setName("Rose");
                for (int i = 0; i < order_list.size(); i++) {
                    LocalDate date = LocalDate.parse(order_list.get(i).getDate());
                    if ((date.isAfter(start1.minusDays(1))) && (date.isBefore(end1.plusDays(1))) && !(date.isEqual(temp))) {
                        for (int j = 0; j < order_list.size(); j++) {
                            LocalDate date1 = LocalDate.parse(order_list.get(j).getDate());
                            if (date1.isEqual(date)) {
                                for(int t=0;t<registrations.size();t++)
                                {
                                    if(order_list.get(j).getClientname().equals(registrations.get(t).getUserName())) {
                                        if (registrations.get(t).getSelectedStore().equals("Lelach, Haifa")) {
                                            for (int k = 0; k < order_list.get(j).getCart().getItems().size(); k++) {
                                                if (order_list.get(j).getCart().getItems().get(k).getType().equals("flower") && !(order_list.get(j).getStatus().equals("Canceled")))
                                                    type1 += order_list.get(j).getCart().getAmount().get(k);
                                                else if (order_list.get(j).getCart().getItems().get(k).getType().equals("Plant") && !(order_list.get(j).getStatus().equals("Canceled")))
                                                    type2 += order_list.get(j).getCart().getAmount().get(k);
                                                else if (order_list.get(j).getCart().getItems().get(k).getType().equals("Bouquet") && !(order_list.get(j).getStatus().equals("Canceled")))
                                                    type3 += order_list.get(j).getCart().getAmount().get(k);
                                                else if (order_list.get(j).getCart().getItems().get(k).getType().equals("Rose") && !(order_list.get(j).getStatus().equals("Canceled")))
                                                    type4 += order_list.get(j).getCart().getAmount().get(k);
                                            }
                                        }
                                    }
                                }


                            }
                        }
                        series1.getData().add(new XYChart.Data<>("flower", type1));
                        series2.getData().add(new XYChart.Data<>("Plant", type2));
                        series3.getData().add(new XYChart.Data<>("Bouquet", type3));
                        series4.getData().add(new XYChart.Data<>("Rose", type4));
                        type1 = 0;
                        type2 = 0;
                        type3 = 0;
                        type4 = 0;
                        temp = date;
                    }
                }
                complain_barchart.getData().add(series1);
                complain_barchart.getData().add(series2);
                complain_barchart.getData().add(series3);
                complain_barchart.getData().add(series4);
            }

        }
        else if (shopname.equals("Lelach, Tel Aviv")) {
            if (type.equals("Complain Report")) {
                complain_barchart.setTitle("Complaints");
                amount.setLabel("amount of complaints");
                date.setLabel("date");
                LocalDate temp = start1.minusDays(1);
                XYChart.Series<String, Number> series = new XYChart.Series<>();
                series.setName("date");
                for (int i = 0; i < complian_list.size(); i++) {
                    if ((complian_list.get(i).getDate().isAfter(start1.minusDays(1))) && (complian_list.get(i).getDate().isBefore(end1.plusDays(1))) && !(complian_list.get(i).getDate().isEqual(temp))) {
                        for (int j = 0; j < complian_list.size(); j++) {
                            if (complian_list.get(j).getDate().isEqual(complian_list.get(i).getDate())) {
                                for(int k=0;k<registrations.size();k++)
                                {
                                    if(complian_list.get(j).getUsername().equals(registrations.get(k).getUserName())) {
                                        if (registrations.get(k).getSelectedStore().equals("Lelach, Tel Aviv"))
                                            counter++;
                                    }
                                }

                            }
                        }
                        series.getData().add(new XYChart.Data<>(complian_list.get(i).getDate().toString(), counter));
                        counter = 0;
                        temp = complian_list.get(i).getDate();
                    }
                }
                complain_barchart.getData().add(series);
            } else if (type.equals("Revenue Report")) {
                complain_barchart.setTitle("Revenue");
                amount.setLabel("revenue per day");
                date.setLabel("date");
                setOrder_list(App.getOrderList());
                double totalRevenue = 0;
                LocalDate temp = start1.minusDays(1);
                XYChart.Series<String, Number> series = new XYChart.Series<>();
                series.setName("date");
                for (int i = 0; i < order_list.size(); i++) {
                    LocalDate date = LocalDate.parse(order_list.get(i).getDate());
                    if ((date.isAfter(start1.minusDays(1))) && (date.isBefore(end1.plusDays(1))) && !(date.isEqual(temp))) {
                        for (int j = 0; j < order_list.size(); j++) {
                            LocalDate date1 = LocalDate.parse(order_list.get(j).getDate());
                            if (date1.isEqual(date)) {
                                for(int k=0;k<registrations.size();k++)
                                {
                                    if(order_list.get(j).getClientname().equals(registrations.get(k).getUserName()) && !(order_list.get(j).getStatus().equals("Canceled"))) {
                                        if (registrations.get(k).getSelectedStore().equals("Lelach, Tel Aviv"))
                                            totalRevenue += Double.parseDouble(order_list.get(j).getTotalprice());
                                    }
                                }

                            }
                        }
                        series.getData().add(new XYChart.Data<>(order_list.get(i).getDate(), totalRevenue));
                        totalRevenue = 0;
                        temp = date;
                    }
                }
                complain_barchart.getData().add(series);
            } else if (type.equals("Orders Report")) {
                complain_barchart.setTitle("Orders");
                amount.setLabel("items per type");
                date.setLabel("item type");
                setOrder_list(App.getOrderList());
                int type1 = 0, type2 = 0, type3 = 0, type4 = 0;
                /*atzkr an2s iom huna*/
                LocalDate temp = start1.minusDays(1);
                XYChart.Series<String, Number> series1 = new XYChart.Series<>();
                XYChart.Series<String, Number> series2 = new XYChart.Series<>();
                XYChart.Series<String, Number> series3 = new XYChart.Series<>();
                XYChart.Series<String, Number> series4 = new XYChart.Series<>();
                series1.setName("flower");
                series2.setName("Plant");
                series3.setName("Bouquet");
                series4.setName("Rose");
                for (int i = 0; i < order_list.size(); i++) {
                    LocalDate date = LocalDate.parse(order_list.get(i).getDate());
                    if ((date.isAfter(start1.minusDays(1))) && (date.isBefore(end1.plusDays(1))) && !(date.isEqual(temp))) {
                        for (int j = 0; j < order_list.size(); j++) {
                            LocalDate date1 = LocalDate.parse(order_list.get(j).getDate());
                            if (date1.isEqual(date)) {
                                for(int t=0;t<registrations.size();t++)
                                {
                                    if(order_list.get(j).getClientname().equals(registrations.get(t).getUserName()) && !(order_list.get(j).getStatus().equals("Canceled"))) {
                                        if (registrations.get(t).getSelectedStore().equals("Lelach, Tel Aviv")) {
                                            for (int k = 0; k < order_list.get(j).getCart().getItems().size(); k++) {
                                                if (order_list.get(j).getCart().getItems().get(k).getType().equals("flower") && !(order_list.get(j).getStatus().equals("Canceled")))
                                                    type1 += order_list.get(j).getCart().getAmount().get(k);
                                                else if (order_list.get(j).getCart().getItems().get(k).getType().equals("Plant") && !(order_list.get(j).getStatus().equals("Canceled")))
                                                    type2 += order_list.get(j).getCart().getAmount().get(k);
                                                else if (order_list.get(j).getCart().getItems().get(k).getType().equals("Bouquet") && !(order_list.get(j).getStatus().equals("Canceled")))
                                                    type3 += order_list.get(j).getCart().getAmount().get(k);
                                                else if (order_list.get(j).getCart().getItems().get(k).getType().equals("Rose") && !(order_list.get(j).getStatus().equals("Canceled")))
                                                    type4 += order_list.get(j).getCart().getAmount().get(k);
                                            }
                                        }
                                    }
                                }

                            }
                        }
                        series1.getData().add(new XYChart.Data<>("flower", type1));
                        series2.getData().add(new XYChart.Data<>("Plant", type2));
                        series3.getData().add(new XYChart.Data<>("Bouquet", type3));
                        series4.getData().add(new XYChart.Data<>("Rose", type4));
                        type1 = 0;
                        type2 = 0;
                        type3 = 0;
                        type4 = 0;
                        temp = date;
                    }
                }
                complain_barchart.getData().add(series1);
                complain_barchart.getData().add(series2);
                complain_barchart.getData().add(series3);
                complain_barchart.getData().add(series4);
            }

        }

    }

}




