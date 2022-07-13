package il.cshaifasweng.OCSFMediatorExample.client;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Complain;
import il.cshaifasweng.OCSFMediatorExample.entities.Report;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

public class ComplainReportController {

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

    }

}


