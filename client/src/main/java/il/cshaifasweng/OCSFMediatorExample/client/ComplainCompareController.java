package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Complain;
import il.cshaifasweng.OCSFMediatorExample.entities.Order;
import il.cshaifasweng.OCSFMediatorExample.entities.Registration;
import il.cshaifasweng.OCSFMediatorExample.entities.Report;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

public class ComplainCompareController {

    @FXML
    private static List<Order> order_list;

    @FXML
    public static List<Order> getOrder_list() {
        return order_list;
    }

    @FXML
    public static void setOrder_list(List<Order> order_list) {
        ComplainCompareController.order_list = order_list;
    }

    @FXML
    private static List<Complain> complian_list;

    @FXML
    public static void setComplian_list(List<Complain> complian_list) { ComplainCompareController.complian_list = complian_list; }

    @FXML
    public static String type;

    @FXML
    public static void setType(String type) {
        ComplainCompareController.type = type;
    }

    @FXML
    public static LocalDate start1;

    @FXML
    public static void setStart1(LocalDate start1) {
        ComplainCompareController.start1 = start1;
    }

    @FXML
    public static LocalDate end1;

    @FXML
    public static void setEnd1(LocalDate end1) {
        ComplainCompareController.end1 = end1;
    }

    @FXML
    public static LocalDate start2;

    @FXML
    public static void setStart2(LocalDate start2) {
        ComplainCompareController.start2 = start2;
    }

    @FXML
    public static LocalDate end2;

    @FXML
    public static void setEnd2(LocalDate end2) {
        ComplainCompareController.end2 = end2;
    }

    @FXML public static String shopname;

    @FXML public static String getShopname() { return shopname; }

    @FXML public static void setShopname(String shopname) { ComplainCompareController.shopname = shopname; }

    @FXML public static List<Registration> getRegistrations() { return registrations; }

    @FXML public static void setRegistrations(List<Registration> registrations) { ComplainCompareController.registrations = registrations; }

    @FXML private static List<Registration> registrations;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private NumberAxis amount1;

    @FXML
    private NumberAxis amount2;

    @FXML
    private CategoryAxis compalin1;

    @FXML
    private CategoryAxis complain2;

    @FXML
    private BarChart<String, Number> complain_report1;

    @FXML
    private BarChart<String, Number> complain_report2;

    @FXML
    private Button back;
    @FXML
    void back1(ActionEvent event) {
        try {
            App.setRoot("CEO");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private int counter = 0;

    @FXML
    void initialize() {
        assert amount1 != null : "fx:id=\"amount1\" was not injected: check your FXML file 'ComplainCompare.fxml'.";
        assert amount2 != null : "fx:id=\"amount2\" was not injected: check your FXML file 'ComplainCompare.fxml'.";
        assert compalin1 != null : "fx:id=\"compalin1\" was not injected: check your FXML file 'ComplainCompare.fxml'.";
        assert complain2 != null : "fx:id=\"complain2\" was not injected: check your FXML file 'ComplainCompare.fxml'.";
        assert complain_report1 != null : "fx:id=\"complain_report1\" was not injected: check your FXML file 'ComplainCompare.fxml'.";
        assert complain_report2 != null : "fx:id=\"complian_report2\" was not injected: check your FXML file 'ComplainCompare.fxml'.";
        setComplian_list(App.getComplain_list());
        setType(App.getType());
        setStart1(App.getStart_date());
        setEnd1(App.getEnd_date());
        setStart2(App.getStart_date2());
        setEnd2(App.getEnd_date2());
        setOrder_list(App.getOrderList());
        setShopname(App.getShopname());
        setRegistrations(App.getRegistrations());

        if (shopname.equals("Lelach")) {
            if (type.equals("Complain Compare")) {
                LocalDate temp = start1.minusDays(1);
                amount1.setLabel("amount of complains");
                amount2.setLabel("amount of complains");
                complain_report1.setTitle("Complaints");
                complain_report2.setTitle("Complaints");
                XYChart.Series<String, Number> series = new XYChart.Series<>();
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
                complain_report1.getData().add(series);


                temp = start2.minusDays(1);
                XYChart.Series<String, Number> series2 = new XYChart.Series<>();
                for (int i = 0; i < complian_list.size(); i++) {
                    if ((complian_list.get(i).getDate().isAfter(start2.minusDays(1))) && (complian_list.get(i).getDate().isBefore(end2.plusDays(1))) && !(complian_list.get(i).getDate().isEqual(temp))) {
                        for (int j = 0; j < complian_list.size(); j++) {
                            if (complian_list.get(j).getDate().isEqual(complian_list.get(i).getDate())) {
                                counter++;
                            }
                        }
                        series2.getData().add(new XYChart.Data<>(complian_list.get(i).getDate().toString(), counter));
                        counter = 0;
                        temp = complian_list.get(i).getDate();
                    }
                }
                complain_report2.getData().add(series2);
            } else if (type.equals("Revenue Compare")) {
                complain_report1.setTitle("Revenue");
                complain_report2.setTitle("Revenue");
                amount1.setLabel("amount of revenue");
                amount2.setLabel("amount of revenue");
                System.out.println();
                double totalRevenue = 0;
                LocalDate temp = start1.minusDays(1);
                XYChart.Series<String, Number> series = new XYChart.Series<>();
                for (int i = 0; i < order_list.size(); i++) {
                    LocalDate date = LocalDate.parse(order_list.get(i).getDate());
                    if ((date.isAfter(start1.minusDays(1))) && (date.isBefore(end1.plusDays(1))) && !(date.isEqual(temp))) {
                        for (int j = 0; j < order_list.size(); j++) {
                            LocalDate date1 = LocalDate.parse(order_list.get(j).getDate());
                            if (date1.isEqual(date)) {
                                if (!(order_list.get(j).getStatus().equals("Canceled")))
                                    totalRevenue += Double.parseDouble(order_list.get(j).getTotalprice());
                            }
                        }
                        series.getData().add(new XYChart.Data<>(order_list.get(i).getDate(), totalRevenue));
                        totalRevenue = 0;
                        temp = date;
                    }
                }
                complain_report1.getData().add(series);

                temp = start2.minusDays(1);
                XYChart.Series<String, Number> series2 = new XYChart.Series<>();
                for (int i = 0; i < order_list.size(); i++) {
                    LocalDate date = LocalDate.parse(order_list.get(i).getDate());
                    if ((date.isAfter(start2.minusDays(1))) && (date.isBefore(end2.plusDays(1))) && !(date.isEqual(temp))) {
                        for (int j = 0; j < order_list.size(); j++) {
                            LocalDate date1 = LocalDate.parse(order_list.get(j).getDate());
                            if (date1.isEqual(date)) {
                                if(!(order_list.get(j).getStatus().equals("Canceled")))
                                    totalRevenue += Double.parseDouble(order_list.get(j).getTotalprice());
                            }
                        }
                        series2.getData().add(new XYChart.Data<>(order_list.get(i).getDate(), totalRevenue));
                        totalRevenue = 0;
                        temp = date;
                    }
                }
                complain_report2.getData().add(series2);
            } else if (type.equals("Orders Compare")) {
                complain_report1.setTitle("Orders");
                complain_report2.setTitle("Orders");
                amount1.setLabel("items per type");
                amount2.setLabel("items per type");
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
                            if (date1.isEqual(date) && !(order_list.get(j).getStatus().equals("Canceled"))) {
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
                complain_report1.getData().add(series1);
                complain_report1.getData().add(series2);
                complain_report1.getData().add(series3);
                complain_report1.getData().add(series4);

                temp = start2.minusDays(1);
                XYChart.Series<String, Number> series11 = new XYChart.Series<>();
                XYChart.Series<String, Number> series22 = new XYChart.Series<>();
                XYChart.Series<String, Number> series33 = new XYChart.Series<>();
                XYChart.Series<String, Number> series44 = new XYChart.Series<>();
                series11.setName("flower");
                series22.setName("Plant");
                series33.setName("Bouquet");
                series44.setName("Rose");
                for (int i = 0; i < order_list.size(); i++) {
                    LocalDate date = LocalDate.parse(order_list.get(i).getDate());
                    if ((date.isAfter(start2.minusDays(1))) && (date.isBefore(end2.plusDays(1))) && !(date.isEqual(temp))) {
                        for (int j = 0; j < order_list.size(); j++) {
                            LocalDate date1 = LocalDate.parse(order_list.get(j).getDate());
                            if (date1.isEqual(date)) {
                                for (int k = 0; k < order_list.get(j).getCart().getItems().size(); k++) {
                                    if (order_list.get(j).getCart().getItems().get(k).getType().equals("flower") && !(order_list.get(j).getStatus().equals("Canceled")))
                                        type1 += order_list.get(j).getCart().getAmount().get(k);
                                    else if (order_list.get(j).getCart().getItems().get(k).getType().equals("Plant") && !(order_list.get(j).getStatus().equals("Canceled")))
                                        type2 += order_list.get(j).getCart().getAmount().get(k);
                                    else if (order_list.get(j).getCart().getItems().get(k).getType().equals("Bouquet") && !(order_list.get(j).getStatus().equals("Canceled")))
                                        type3 += order_list.get(j).getCart().getAmount().get(k);
                                    else if (order_list.get(j).getCart().getItems().get(k).getType().equals("Rose")&& !(order_list.get(j).getStatus().equals("Canceled")))
                                        type4 += order_list.get(j).getCart().getAmount().get(k);
                                }

                            }
                        }
                        series11.getData().add(new XYChart.Data<>("flower", type1));
                        series22.getData().add(new XYChart.Data<>("Plant", type2));
                        series33.getData().add(new XYChart.Data<>("Bouquet", type3));
                        series44.getData().add(new XYChart.Data<>("Rose", type4));
                        type1 = 0;
                        type2 = 0;
                        type3 = 0;
                        type4 = 0;
                        temp = date;
                    }
                }
                complain_report2.getData().add(series11);
                complain_report2.getData().add(series22);
                complain_report2.getData().add(series33);
                complain_report2.getData().add(series44);

            }
        }

        if (shopname.equals("Lelach, Haifa")) {
            if (type.equals("Complain Compare")) {
                LocalDate temp = start1.minusDays(1);
                amount1.setLabel("amount of complains");
                amount2.setLabel("amount of complains");
                complain_report1.setTitle("Complaints");
                complain_report2.setTitle("Complaints");
                XYChart.Series<String, Number> series = new XYChart.Series<>();
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
                complain_report1.getData().add(series);


                temp = start2.minusDays(1);
                XYChart.Series<String, Number> series2 = new XYChart.Series<>();
                for (int i = 0; i < complian_list.size(); i++) {
                    if ((complian_list.get(i).getDate().isAfter(start2.minusDays(1))) && (complian_list.get(i).getDate().isBefore(end2.plusDays(1))) && !(complian_list.get(i).getDate().isEqual(temp))) {
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
                        series2.getData().add(new XYChart.Data<>(complian_list.get(i).getDate().toString(), counter));
                        counter = 0;
                        temp = complian_list.get(i).getDate();
                    }
                }
                complain_report2.getData().add(series2);
            } else if (type.equals("Revenue Compare")) {
                complain_report1.setTitle("Revenue");
                complain_report2.setTitle("Revenue");
                amount1.setLabel("amount of revenue");
                amount2.setLabel("amount of revenue");
                System.out.println();
                double totalRevenue = 0;
                LocalDate temp = start1.minusDays(1);
                XYChart.Series<String, Number> series = new XYChart.Series<>();
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
                                            if (!(order_list.get(j).getStatus().equals("Canceled")))
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
                complain_report1.getData().add(series);

                temp = start2.minusDays(1);
                XYChart.Series<String, Number> series2 = new XYChart.Series<>();
                for (int i = 0; i < order_list.size(); i++) {
                    LocalDate date = LocalDate.parse(order_list.get(i).getDate());
                    if ((date.isAfter(start2.minusDays(1))) && (date.isBefore(end2.plusDays(1))) && !(date.isEqual(temp))) {
                        for (int j = 0; j < order_list.size(); j++) {
                            LocalDate date1 = LocalDate.parse(order_list.get(j).getDate());
                            if (date1.isEqual(date)) {
                                for(int k=0;k<registrations.size();k++)
                                {
                                    if(order_list.get(j).getClientname().equals(registrations.get(k).getUserName())) {
                                        if (registrations.get(k).getSelectedStore().equals("Lelach, Haifa")){
                                            if (!(order_list.get(j).getStatus().equals("Canceled")))
                                                totalRevenue += Double.parseDouble(order_list.get(j).getTotalprice());
                                        }

                                    }
                                }
                            }
                        }
                        series2.getData().add(new XYChart.Data<>(order_list.get(i).getDate(), totalRevenue));
                        totalRevenue = 0;
                        temp = date;
                    }
                }
                complain_report2.getData().add(series2);
            } else if (type.equals("Orders Compare")) {
                complain_report1.setTitle("Orders");
                complain_report2.setTitle("Orders");
                amount1.setLabel("items per type");
                amount2.setLabel("items per type");
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
                complain_report1.getData().add(series1);
                complain_report1.getData().add(series2);
                complain_report1.getData().add(series3);
                complain_report1.getData().add(series4);

                temp = start2.minusDays(1);
                XYChart.Series<String, Number> series11 = new XYChart.Series<>();
                XYChart.Series<String, Number> series22 = new XYChart.Series<>();
                XYChart.Series<String, Number> series33 = new XYChart.Series<>();
                XYChart.Series<String, Number> series44 = new XYChart.Series<>();
                series11.setName("flower");
                series22.setName("Plant");
                series33.setName("Bouquet");
                series44.setName("Rose");
                for (int i = 0; i < order_list.size(); i++) {
                    LocalDate date = LocalDate.parse(order_list.get(i).getDate());
                    if ((date.isAfter(start2.minusDays(1))) && (date.isBefore(end2.plusDays(1))) && !(date.isEqual(temp))) {
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
                        series11.getData().add(new XYChart.Data<>("flower", type1));
                        series22.getData().add(new XYChart.Data<>("Plant", type2));
                        series33.getData().add(new XYChart.Data<>("Bouquet", type3));
                        series44.getData().add(new XYChart.Data<>("Rose", type4));
                        type1 = 0;
                        type2 = 0;
                        type3 = 0;
                        type4 = 0;
                        temp = date;
                    }
                }
                complain_report2.getData().add(series11);
                complain_report2.getData().add(series22);
                complain_report2.getData().add(series33);
                complain_report2.getData().add(series44);

            }
        }
        if (shopname.equals("Lelach, Tel Aviv")) {
            if (type.equals("Complain Compare")) {
                LocalDate temp = start1.minusDays(1);
                amount1.setLabel("amount of complains");
                amount2.setLabel("amount of complains");
                complain_report1.setTitle("Complaints");
                complain_report2.setTitle("Complaints");
                XYChart.Series<String, Number> series = new XYChart.Series<>();
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
                complain_report1.getData().add(series);


                temp = start2.minusDays(1);
                XYChart.Series<String, Number> series2 = new XYChart.Series<>();
                for (int i = 0; i < complian_list.size(); i++) {
                    if ((complian_list.get(i).getDate().isAfter(start2.minusDays(1))) && (complian_list.get(i).getDate().isBefore(end2.plusDays(1))) && !(complian_list.get(i).getDate().isEqual(temp))) {
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
                        series2.getData().add(new XYChart.Data<>(complian_list.get(i).getDate().toString(), counter));
                        counter = 0;
                        temp = complian_list.get(i).getDate();
                    }
                }
                complain_report2.getData().add(series2);
            } else if (type.equals("Revenue Compare")) {
                complain_report1.setTitle("Revenue");
                complain_report2.setTitle("Revenue");
                amount1.setLabel("amount of revenue");
                amount2.setLabel("amount of revenue");
                System.out.println();
                double totalRevenue = 0;
                LocalDate temp = start1.minusDays(1);
                XYChart.Series<String, Number> series = new XYChart.Series<>();
                for (int i = 0; i < order_list.size(); i++) {
                    LocalDate date = LocalDate.parse(order_list.get(i).getDate());
                    if ((date.isAfter(start1.minusDays(1))) && (date.isBefore(end1.plusDays(1))) && !(date.isEqual(temp))) {
                        for (int j = 0; j < order_list.size(); j++) {
                            LocalDate date1 = LocalDate.parse(order_list.get(j).getDate());
                            if (date1.isEqual(date)) {
                                for(int k=0;k<registrations.size();k++)
                                {
                                    if(order_list.get(j).getClientname().equals(registrations.get(k).getUserName())) {
                                        if (registrations.get(k).getSelectedStore().equals("Lelach, Tel Aviv")) {
                                            if (!(order_list.get(j).getStatus().equals("Canceled")))
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
                complain_report1.getData().add(series);

                temp = start2.minusDays(1);
                XYChart.Series<String, Number> series2 = new XYChart.Series<>();
                for (int i = 0; i < order_list.size(); i++) {
                    LocalDate date = LocalDate.parse(order_list.get(i).getDate());
                    if ((date.isAfter(start2.minusDays(1))) && (date.isBefore(end2.plusDays(1))) && !(date.isEqual(temp))) {
                        for (int j = 0; j < order_list.size(); j++) {
                            LocalDate date1 = LocalDate.parse(order_list.get(j).getDate());
                            if (date1.isEqual(date)) {
                                for(int k=0;k<registrations.size();k++)
                                {
                                    if(order_list.get(j).getClientname().equals(registrations.get(k).getUserName())) {
                                        if (registrations.get(k).getSelectedStore().equals("Lelach, Tel Aviv")) {
                                            if(!(order_list.get(j).getStatus().equals("Canceled")))
                                                totalRevenue += Double.parseDouble(order_list.get(j).getTotalprice());
                                        }
                                    }
                                }
                            }
                        }
                        series2.getData().add(new XYChart.Data<>(order_list.get(i).getDate(), totalRevenue));
                        totalRevenue = 0;
                        temp = date;
                    }
                }
                complain_report2.getData().add(series2);
            } else if (type.equals("Orders Compare")) {
                complain_report1.setTitle("Orders");
                complain_report2.setTitle("Orders");
                amount1.setLabel("items per type");
                amount2.setLabel("items per type");
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
                complain_report1.getData().add(series1);
                complain_report1.getData().add(series2);
                complain_report1.getData().add(series3);
                complain_report1.getData().add(series4);

                temp = start2.minusDays(1);
                XYChart.Series<String, Number> series11 = new XYChart.Series<>();
                XYChart.Series<String, Number> series22 = new XYChart.Series<>();
                XYChart.Series<String, Number> series33 = new XYChart.Series<>();
                XYChart.Series<String, Number> series44 = new XYChart.Series<>();
                series11.setName("flower");
                series22.setName("Plant");
                series33.setName("Bouquet");
                series44.setName("Rose");
                for (int i = 0; i < order_list.size(); i++) {
                    LocalDate date = LocalDate.parse(order_list.get(i).getDate());
                    if ((date.isAfter(start2.minusDays(1))) && (date.isBefore(end2.plusDays(1))) && !(date.isEqual(temp))) {
                        for (int j = 0; j < order_list.size(); j++) {
                            LocalDate date1 = LocalDate.parse(order_list.get(j).getDate());
                            if (date1.isEqual(date)) {
                                for(int t=0;t<registrations.size();t++)
                                {
                                    if(order_list.get(j).getClientname().equals(registrations.get(t).getUserName())) {
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
                        series11.getData().add(new XYChart.Data<>("flower", type1));
                        series22.getData().add(new XYChart.Data<>("Plant", type2));
                        series33.getData().add(new XYChart.Data<>("Bouquet", type3));
                        series44.getData().add(new XYChart.Data<>("Rose", type4));
                        type1 = 0;
                        type2 = 0;
                        type3 = 0;
                        type4 = 0;
                        temp = date;
                    }
                }
                complain_report2.getData().add(series11);
                complain_report2.getData().add(series22);
                complain_report2.getData().add(series33);
                complain_report2.getData().add(series44);

            }
        }
    }
}