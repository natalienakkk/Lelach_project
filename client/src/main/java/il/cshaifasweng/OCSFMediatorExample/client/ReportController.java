//package il.cshaifasweng.OCSFMediatorExample.client;
//
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.io.IOException;
//import java.net.URL;
//import java.time.LocalDate;
//import java.util.List;
//import java.util.ResourceBundle;
//
//import il.cshaifasweng.OCSFMediatorExample.entities.Order;
//import il.cshaifasweng.OCSFMediatorExample.entities.Registration;
//import il.cshaifasweng.OCSFMediatorExample.entities.Report;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.layout.AnchorPane;
//
//public class ReportController {
//    @FXML private static List<Report> report_list;
//    @FXML public static void setReport_list(List<Report> list) { ReportController.report_list = list; }
//    @FXML public static List<Report> getReport_list() { return report_list; }
//
//    @FXML private static String type;
//    @FXML public static String getType() { return type; }
//    @FXML public static void setType(String type1) { ReportController.type = type1; }
//
//    @FXML private static String start;
//    @FXML public static String getStart() { return start; }
//    @FXML public static void setStart(String start) { ReportController.start = start; }
//
//    @FXML private static String end;
//    @FXML public static String getEnd() { return end; }
//    @FXML public static void setEnd(String end) {ReportController.end = end; }
//
//    @FXML private static String second_start;
//    @FXML public static String getSecond_start() { return second_start; }
//    @FXML public static void setSecond_start(String second_start) { ReportController.second_start = second_start; }
//
//    @FXML private static String second_end;
//    @FXML public static String getSecond_end() { return second_end; }
//    @FXML public static void setSecond_end(String second_end) { ReportController.second_end = second_end; }
//
//    @FXML
//    private AnchorPane Pane1;
//
////    @FXML
////    private Button back;
//
//    @FXML
//    private ResourceBundle resources;
//
//    @FXML
//    private URL location;
//
//    @FXML
//    private TableView<Report> Table_view;
//
//    @FXML
//    private TableColumn<Report,String> branch_manager_col;
//
//    @FXML
//    private TableColumn<Report,String> branch_name_col;
//
//    @FXML
//    private TableColumn<Report, LocalDate> date_col;
//
//    @FXML
//    private TableColumn<Order, Long> order_id_col;
//
//    @FXML
//    private TableColumn<Order, String> order_items_col;
//
//    @FXML
//    private TableColumn<Report, Long> report_id_col;
//
//    @FXML
//    private TableColumn<Order, Long> revenue_col;
//
////    @FXML
////    void back(ActionEvent event) throws IOException {
////        AnchorPane pane = FXMLLoader.load(getClass().getResource("BranchManager.fxml"));
////        Pane1.getChildren().setAll(pane);
////    }
//
//
//    @FXML
//    void initialize() {
//        assert Table_view != null : "fx:id=\"Table_view\" was not injected: check your FXML file 'Report.fxml'.";
//        //assert back != null : "fx:id=\"back\" was not injected: check your FXML file 'Report.fxml'.";
//        assert branch_manager_col != null : "fx:id=\"branch_manager_col\" was not injected: check your FXML file 'Report.fxml'.";
//        assert branch_name_col != null : "fx:id=\"branch_name_col\" was not injected: check your FXML file 'Report.fxml'.";
//        assert date_col != null : "fx:id=\"date_col\" was not injected: check your FXML file 'Report.fxml'.";
//        assert order_id_col != null : "fx:id=\"oder_id_col\" was not injected: check your FXML file 'Report.fxml'.";
//        assert order_items_col != null : "fx:id=\"order_items_col\" was not injected: check your FXML file 'Report.fxml'.";
//        assert report_id_col != null : "fx:id=\"report_id_col\" was not injected: check your FXML file 'Report.fxml'.";
//        assert revenue_col != null : "fx:id=\"revenue_col\" was not injected: check your FXML file 'Report.fxml'.";
//
//        setType(App.getType());
//        setStart(App.getStart_date());
//        setEnd(App.getEnd_date());
//        setReport_list(App.getReport_list());
//        setSecond_start(App.getStart_date2());
//        setSecond_end(App.getEnd_date2());
//        if(type.equals("Revenue Report"))
//        {
//            order_id_col.setVisible(false);
//            // branch_manager_col.setVisible(false);
//            order_items_col.setVisible(false);
//            report_id_col.prefWidthProperty().bind(Table_view.widthProperty().multiply(0.1));
//            revenue_col.prefWidthProperty().bind(Table_view.widthProperty().multiply(0.3));
//            date_col.prefWidthProperty().bind(Table_view.widthProperty().multiply(0.2));
//            branch_manager_col.prefWidthProperty().bind(Table_view.widthProperty().multiply(0.2));
//            branch_name_col.prefWidthProperty().bind(Table_view.widthProperty().multiply(0.2));
//
//
//        }
//        if(type.equals("Orders Report"))
//        {
//            revenue_col.setVisible(false);
//            order_items_col.prefWidthProperty().bind(Table_view.widthProperty().multiply(0.4));
//        }
//
//
//        //  branch_manager_col.prefWidthProperty().bind(Table_view.widthProperty().multiply(0));
//
//    }
//}