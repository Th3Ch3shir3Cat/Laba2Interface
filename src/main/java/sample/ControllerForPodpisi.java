package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import sample.Table.StringTableWorkers;


public class ControllerForPodpisi {

    @FXML
    private TextField materialPodpis;

    @FXML
    private TextField checkPodpis;

    @FXML
    private TextField rashPodpis;

    @FXML
    private ComboBox dolgnost;

    @FXML
    private ComboBox mainDolgnost;

    @FXML
    private TextField mainSolution;

    @FXML
    private TextField mainPodpis;

    @FXML
    private TextField mainRashPodpis;

    @FXML
    private TableView<StringTableWorkers> workers;

    private final ObservableList<StringTableWorkers> stringDataForWorkers = FXCollections.observableArrayList();
    private static int count = 1;

    @FXML
    private TableColumn<StringTableWorkers, Integer> idColumn;

    @FXML
    private TableColumn<StringTableWorkers, String> fioColumn;

    @FXML
    private void initialize(){
        materialPodpis.setText("");
        checkPodpis.setText("");
        rashPodpis.setText("");
        mainSolution.setText("");
        mainPodpis.setText("");
        mainRashPodpis.setText("");

        idColumn.setCellValueFactory(new PropertyValueFactory<StringTableWorkers,Integer>("id"));
        fioColumn.setCellValueFactory(new PropertyValueFactory<StringTableWorkers, String>("fio"));
        editStringCell(fioColumn);

        initData();
        workers.setItems(stringDataForWorkers);
    }

    private void initData(){
        stringDataForWorkers.add(new StringTableWorkers(count,""));
        count++;
    }

    //Делаем редактор для строк
    private void editStringCell(TableColumn<StringTableWorkers, String> column){
        column.setCellFactory(TextFieldTableCell.forTableColumn());
        column.setOnEditCommit((TableColumn.CellEditEvent<StringTableWorkers,String> event)-> {
            TablePosition<StringTableWorkers, String> pos = event.getTablePosition();
            String newName = event.getNewValue();
            int row = pos.getRow();
            StringTableWorkers stringTableWorkers = event.getTableView().getItems().get(row);
                    stringTableWorkers.setFio(newName);
                    if(newName.length() != 0) {
                        if(row == stringDataForWorkers.size()-1) {
                            stringDataForWorkers.add(new StringTableWorkers(count, ""));
                            count++;
                        }
                    }

        });
    }

}
