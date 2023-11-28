package com.infinitops.musicaltickets;

import com.infinitops.musicaltickets.model.Musical;
import com.infinitops.musicaltickets.model.MyTableColumn;
import com.infinitops.musicaltickets.model.Schedule;
import com.infinitops.musicaltickets.model.Venue;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.util.*;

public class MainController implements Initializable {

    private Venue[] venues = new Venue[]{
            new Venue(1, "Lyceum Theatre", new String[]{"A1", "A2", "A3", "B1", "B2", "B3", "C1", "C2", "C3"}),
            new Venue(2, "Royal Drury", new String[]{"A1", "A2", "A3", "B1", "B2", "B3", "C1", "C2", "C3"}),
            new Venue(3, "Nairobi Cinema", new String[]{"A1", "A2", "A3", "B1", "B2", "B3", "C1", "C2", "C3"})
    };
    private Musical[] musicals = new Musical[]{
            new Musical(1, "The Lion King", 130, "Disney Shows, Family and Kids", 6),
            new Musical(2, "Frozen", 145, "Disney Shows, Family and Kids, Weekday Matinees", 6),
    };
    private Schedule[] schedules = new Schedule[]{
            new Schedule(venues[0], "2023-11-30", "18:00", musicals[0]),
            new Schedule(venues[0], "2023-12-02", "18:00", musicals[0]),
            new Schedule(venues[0], "2023-11-29", "14:00", musicals[1]),
            new Schedule(venues[1], "2023-12-01", "17:00", musicals[0]),
            new Schedule(venues[1], "2023-11-30", "18:00", musicals[1])
    };

    @FXML
    private TableView<Musical> myTableView;

    @FXML
    private VBox vBoxPane;

    @FXML
    protected void showMusicalList(){
    }

    @FXML
    protected void showSchedule(){
        Musical musical = myTableView.getSelectionModel().getSelectedItem();
        if(musical == null){
            createNotification(0, "Select a musical first from the table.");
            return;
        }
        List<Schedule> musicalSchedules = new ArrayList<>();
        for (Schedule s: schedules) {
            if (s.getMusical().equals(musical)) musicalSchedules.add(s);
        }
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/infinitops/musicaltickets/show_schedules.fxml")));
            AnchorPane pane = loader.load();
            ShowSchedules showSchedules = loader.getController();
            showSchedules.setSchedules(musicalSchedules.toArray(new Schedule[]{}));
            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setTitle("Musical Schedule");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void bookTickets(){
        Musical musical = myTableView.getSelectionModel().getSelectedItem();
        if(musical == null){
            createNotification(0, "Select a musical first from the table.");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/infinitops/musicaltickets/book_ticket.fxml")));
            AnchorPane pane = loader.load();
            BookTicket bookTicket = loader.getController();
            bookTicket.setVenues(venues);
            bookTicket.setMusical(musical);
            bookTicket.setSchedules(schedules);
            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setTitle("Book Ticket");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void exitApp(){
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        myTableView.prefWidthProperty().bind(vBoxPane.widthProperty());
        if(myTableView == null) System.out.println("Table is null");
        myTableView.getColumns().removeAll(myTableView.getColumns());
        Platform.runLater(() -> {
            myTableView.getColumns().addAll(createColumns(new MyTableColumn[]{
                    new MyTableColumn("Title", "title", 0.33),
                    new MyTableColumn("Min Age", "minAge", 0.33),
                    new MyTableColumn("Categories", "category", 0.35),
            }));
            Platform.runLater(() -> myTableView.setItems(FXCollections.observableArrayList(Arrays.asList(musicals))));
        });
    }

    private void createNotification(int type, String message) {
        Notifications n = Notifications.create()
                .owner(vBoxPane)
                .text(message)
                .hideAfter(Duration.seconds(8))
                .darkStyle()
                .position(Pos.BOTTOM_RIGHT);
        Platform.runLater(() -> {
            switch (type) {
                case -1:
                    n.showError();
                    break;
                case 0:
                    n.showInformation();
                    break;
                default:
                    n.showWarning();
            }

        });

    }

    private TableColumn[] createColumns(MyTableColumn[] myTableColumns) {
        TableColumn[] tableColumns = new TableColumn[]{};
        List<TableColumn<String, String>> tableColumnList = new ArrayList<>();
        for (MyTableColumn m : myTableColumns) {
            TableColumn<String, String> tableColumn = new TableColumn<>(m.getName());
            tableColumn.setCellValueFactory(new PropertyValueFactory<>(m.getProperty()));
            tableColumn.prefWidthProperty().bind(myTableView.prefWidthProperty().multiply(m.getMultiplier()));
            tableColumn.setResizable(true);
            tableColumnList.add(tableColumn);
        }
        return tableColumnList.toArray(tableColumns);
    }
}
