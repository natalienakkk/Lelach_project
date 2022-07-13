package il.cshaifasweng.OCSFMediatorExample.client;

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

public class ComplainCompareController {

    @FXML private static List<Complain> complian_list;

    @FXML public static void setComplian_list(List<Complain> complian_list) { ComplainCompareController.complian_list = complian_list; }

    @FXML public static String type;

    @FXML public static void setType(String type) { ComplainCompareController.type = type; }

    @FXML public static LocalDate start1;

    @FXML public static void setStart1(LocalDate start1) { ComplainCompareController.start1 = start1; }

    @FXML public static LocalDate end1;

    @FXML public static void setEnd1(LocalDate end1) { ComplainCompareController.end1 = end1; }

    @FXML public static LocalDate start2;

    @FXML public static void setStart2(LocalDate start2) { ComplainCompareController.start2 = start2; }

    @FXML public static LocalDate end2;

    @FXML public static void setEnd2(LocalDate end2) { ComplainCompareController.end2 = end2; }


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

    private int counter=0;

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
        System.out.println("elias"+App.getComplain_list().get(0).getMessage());

        System.out.println("natalie"+complian_list.get(0).getMessage());
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
        complain_report1.getData().add(series);

        /*atzkr an2s iom huna*/
        temp=start2.minusDays(1);
        XYChart.Series<String,Number> series2=new XYChart.Series<>();
        for(int i=0;i<complian_list.size();i++)
        {
            System.out.println("check 1 "+complian_list.get(i).getDate().isAfter(start2.minusDays(1))+(complian_list.get(i).getDate().isBefore(end2.plusDays(1))));
            if((complian_list.get(i).getDate().isAfter(start2.minusDays(1))) && (complian_list.get(i).getDate().isBefore(end2.plusDays(1))) && !(complian_list.get(i).getDate().isEqual(temp)))
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
                series2.getData().add(new XYChart.Data<>(complian_list.get(i).getDate().toString(),counter));
                counter=0;
                temp=complian_list.get(i).getDate();
            }
        }
        complain_report2.getData().add(series2);
    }

}
