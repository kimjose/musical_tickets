package com.infinitops.musicaltickets;

import com.infinitops.musicaltickets.model.Musical;
import com.infinitops.musicaltickets.model.MyTableColumn;
import com.infinitops.musicaltickets.model.Schedule;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ShowSchedules implements Initializable {

    public Label labelMusicalTitle;
    public TableView<Schedule> tableSchedules;
    private Musical musical;
    private Schedule[] schedules;

    public Musical getMusical() {
        return musical;
    }

    public void setMusical(Musical musical) {
        this.musical = musical;
    }

    public Schedule[] getSchedules() {
        return schedules;
    }

    public void setSchedules(Schedule[] schedules) {
        this.schedules = schedules;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableSchedules.getColumns().removeAll(tableSchedules.getColumns());
        Platform.runLater(() -> {
            tableSchedules.getColumns().addAll(createColumns(new MyTableColumn[]{
                    new MyTableColumn("Musical", "musicalTitle", 0.25),
                    new MyTableColumn("Venue", "venueName", 0.24),
                    new MyTableColumn("Time Slot", "timeSlot", 0.24),
                    new MyTableColumn("Price", "price", 0.23),
            }));
            tableSchedules.setItems(FXCollections.observableList(Arrays.asList(schedules)));
        });
    }

    private TableColumn[] createColumns(MyTableColumn[] myTableColumns) {
        TableColumn[] tableColumns = new TableColumn[]{};
        List<TableColumn<String, String>> tableColumnList = new ArrayList<>();
        for (MyTableColumn m : myTableColumns) {
            TableColumn<String, String> tableColumn = new TableColumn<>(m.getName());
            tableColumn.setCellValueFactory(new PropertyValueFactory<>(m.getProperty()));
            tableColumn.prefWidthProperty().bind(tableSchedules.prefWidthProperty().multiply(m.getMultiplier()));
            tableColumn.setResizable(true);
            tableColumnList.add(tableColumn);
        }
        return tableColumnList.toArray(tableColumns);
    }
}
