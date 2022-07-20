package il.cshaifasweng.OCSFMediatorExample.client;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Complain;
import il.cshaifasweng.OCSFMediatorExample.entities.Order;
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

    @FXML public static String type;

    @FXML public static void setType(String type) { ComplainReportController.type = type; }

    @FXML public static LocalDate start1;

    @FXML public static void setStart1(LocalDate start1) { ComplainReportController.start1 = start1; }

    @FXML public static LocalDate end1;

    @FXML public static void setEnd1(LocalDate end1) { ComplainReportController.end1 = end1; }

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


    //    @FXML
//   private Button back_button;

    private int counter=0;

    /*AZBTHA AB3T EL TYPE 3SHAN A3RF LWEN ARJ3*/

//    @FXML
//    void back(ActionEvent event) {
//        try {
//            App.setRoot("BranchManager");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

    @FXML
    void initialize() {
        assert amount != null : "fx:id=\"amount\" was not injected: check your FXML file 'ComplainReport.fxml'.";
        assert complain_barchart != null : "fx:id=\"complain_barchart\" was not injected: check your FXML file 'ComplainReport.fxml'.";
        assert date != null : "fx:id=\"date\" was not injected: check your FXML file 'ComplainReport.fxml'.";
        //assert back_button != null : "fx:id=\"back_button\" was not injected: check your FXML file 'ComplainReport.fxml'.";


        setComplian_list(App.getComplain_list());
        setType(App.getType());
        setStart1(App.getStart_date());
        setEnd1(App.getEnd_date());

        if(type.equals("Complain Report")) {
            /*atzkr an2s iom huna*/
            complain_barchart.setTitle("Complaints");
            amount.setLabel("amount of complaints");
            date.setLabel("date");
            LocalDate temp=start1.minusDays(1);
            XYChart.Series<String,Number> series=new XYChart.Series<>();
            for(int i=0;i<complian_list.size();i++)
            {
                System.out.println("check 1 "+complian_list.get(i).getDate().isAfter(start1.minusDays(1))+(complian_list.get(i).getDate().isBefore(end1.plusDays(1))));
                if((complian_list.get(i).getDate().isAfter(start1.minusDays(1))) && (complian_list.get(i).getDate().isBefore(end1.plusDays(1))) && !(complian_list.get(i).getDate().isEqual(temp)))
                {
                    for(int j=0;j<complian_list.size();j++)
                    {
                        System.out.println("check 2 "+complian_list.get(j).getDate().isEqual(complian_list.get(i).getDate()));
                        if(complian_list.get(j).getDate().isEqual(complian_list.get(i).getDate()))
                        {
                            counter++;
                            System.out.println("check 3"+counter);
                        }
                    }
                    System.out.println("print i="+i);
                    series.getData().add(new XYChart.Data<>(complian_list.get(i).getDate().toString(),counter));
                    counter=0;
                    temp=complian_list.get(i).getDate();
                }
            }
            complain_barchart.getData().add(series);
        }

        else if (type.equals("Revenue Report")){
            complain_barchart.setTitle("Revenue");
            amount.setLabel("revenue per day");
            date.setLabel("date");
            setOrder_list(App.getOrderList());
            double totalRevenue=0;
            /*atzkr an2s iom huna*/
            LocalDate temp=start1.minusDays(1);
            XYChart.Series<String,Number> series=new XYChart.Series<>();
            for(int i=0;i<order_list.size();i++)
            {
                LocalDate date= LocalDate.parse(order_list.get(i).getDate());
                // System.out.println("check 1 "+order_list.get(i).getDate().isAfter(start1.minusDays(1))+(order_list.get(i).getDate().isBefore(end1.plusDays(1))));
                if((date.isAfter(start1.minusDays(1))) && (date.isBefore(end1.plusDays(1))) && !(date.isEqual(temp)))
                {
                    for(int j=0;j<order_list.size();j++)
                    {
                        LocalDate date1= LocalDate.parse(order_list.get(j).getDate());
                        // System.out.println("check 2 "+order_list.get(j).getDate().isEqual(date));
                        if(date1.isEqual(date))
                        {
                            totalRevenue+=Double.parseDouble(order_list.get(j).getTotalprice());
                        }
                    }
                    System.out.println("print i="+i);
                    series.getData().add(new XYChart.Data<>(order_list.get(i).getDate(),totalRevenue));
                    totalRevenue=0;
                    temp=date;
                }
            }
            complain_barchart.getData().add(series);
        }
///elcart 3m ttl3 fadi /////////
//        else if(type.equals("Orders Report"))
//        {
//            complain_barchart.setTitle("Orders");
//            amount.setLabel("items per day");
//            date.setLabel("item type");
//            setOrder_list(App.getOrderList());
//            int type1=0,type2=0,type3=0,type4=0;
//            /*atzkr an2s iom huna*/
//            LocalDate temp=start1.minusDays(1);
//            XYChart.Series<String,Number> series1=new XYChart.Series<>();
//            XYChart.Series<String,Number> series2=new XYChart.Series<>();
//            XYChart.Series<String,Number> series3=new XYChart.Series<>();
//            XYChart.Series<String,Number> series4=new XYChart.Series<>();
//            for(int i=0;i<order_list.size();i++)
//            {
//                LocalDate date= LocalDate.parse(order_list.get(i).getDate());
//                // System.out.println("check 1 "+order_list.get(i).getDate().isAfter(start1.minusDays(1))+(order_list.get(i).getDate().isBefore(end1.plusDays(1))));
//                if((date.isAfter(start1.minusDays(1))) && (date.isBefore(end1.plusDays(1))) && !(date.isEqual(temp)))
//                {
//                    for(int j=0;j<order_list.size();j++)
//                    {
//                        LocalDate date1= LocalDate.parse(order_list.get(j).getDate());
//                        // System.out.println("check 2 "+order_list.get(j).getDate().isEqual(date));
//                        if(date1.isEqual(date))
//                        {
//                            for(int k=0;k<order_list.get(j).getCart().getItems().size();k++)
//                            if(order_list.get(j).getCart().getItems().get(k).getType().equals("flower"))
//                               type1++;
//                            else if(order_list.get(j).getCart().getItems().get(k).getType().equals("plant"))
//                                type2++;
//                            else if(order_list.get(j).getCart().getItems().get(k).getType().equals("bouquet"))
//                                type3++;
//                            else if (order_list.get(j).getCart().getItems().get(k).getType().equals("rose"))
//                               type4++;
//
//                        }
//                    }
//                    //System.out.println("print i="+i);
//                    series1.getData().add(new XYChart.Data<>(order_list.get(i).getDate(),type1));
//                    series2.getData().add(new XYChart.Data<>(order_list.get(i).getDate(),type2));
//                    series3.getData().add(new XYChart.Data<>(order_list.get(i).getDate(),type3));
//                    series4.getData().add(new XYChart.Data<>(order_list.get(i).getDate(),type4));
//                    type1=0;
//                    type2=0;
//                    type3=0;
//                    type4=0;
//                    temp=date;
//                }
//            }
//            complain_barchart.getData().add(series1);
//            complain_barchart.getData().add(series2);
//            complain_barchart.getData().add(series3);
//            complain_barchart.getData().add(series4);
//        }

    }

}


