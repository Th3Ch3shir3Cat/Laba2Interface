package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.apache.poi.ss.formula.functions.T;
import sample.Information.InfoAboutCompany;
import sample.Information.Rashifrovka;
import sample.Table.StringTableFirst;
import sample.Table.StringTableWorkers;

import java.io.IOException;


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
    private TableView workers;

    private final ObservableList<StringTableWorkers> stringDataForWorkers = FXCollections.observableArrayList();
    private static int count = 1;

    private static Rashifrovka rashifrovka;


    @FXML
    private TableColumn<StringTableWorkers, Integer> idColumn;

    @FXML
    private TableColumn<StringTableWorkers, String> fioColumn;

    private ObservableList<StringTableFirst> stringData;
    private ObservableList<StringTableFirst> stringDataForRash;

    private TableView tableView;
    private InfoAboutCompany infoAboutCompany;

    @FXML
    private void initialize(){
        rashifrovka = new Rashifrovka();

        materialPodpis.setText("");
        checkPodpis.setText("");
        rashPodpis.setText("");
        mainSolution.setText("");
        mainPodpis.setText("");
        mainRashPodpis.setText("");

        dolgnost.getItems().setAll("Главный бухгалтер", "Неглавный бухгалтер", "Директор", "Зам.директора");
        mainDolgnost.getItems().setAll("Главный бухгалтер", "Неглавный бухгалтер", "Директор", "Зам.директора");

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

    @FXML
    public void onClickSave() throws IOException {

        RoadToExcel roadToExcel = new RoadToExcel();
        roadToExcel.exportToExcelInfoAboutComp(infoAboutCompany);

        tableView.setItems(stringData);

        roadToExcel.exportToExcelTable(tableView,1);
        stringData.remove(tableView.getItems().size()-1);

        tableView.setItems(stringDataForRash);

        roadToExcel.exportToExcelTable(tableView,2);
        stringDataForRash.remove(tableView.getItems().size()-1);
        stringDataForRash.remove(tableView.getItems().size()-1);
        stringDataForRash.remove(tableView.getItems().size()-1);
        stringDataForRash.remove(tableView.getItems().size()-1);

        setRashifrovka();

        roadToExcel.exportToExcelRash(rashifrovka);

        stringDataForWorkers.remove(stringDataForWorkers.size()-1);
        workers.setItems(stringDataForWorkers);
        roadToExcel.exportToExcelTableWorkers(workers);
        roadToExcel.closeFiles();

    }

    private void setRashifrovka(){

        rashifrovka.setMaterialPodpis(materialPodpis.getText());
        rashifrovka.setDognost(dolgnost.getValue().toString());
        rashifrovka.setRashifrovka(rashPodpis.getText());
        rashifrovka.setMainSolution(mainSolution.getText());
        rashifrovka.setMainDolgnost(mainDolgnost.getValue().toString());
        rashifrovka.setRashRuk(mainRashPodpis.getText());
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



    public void setMaterialPodpis(String materialPodpis){
        this.materialPodpis.setText(materialPodpis);
    }

    public void setInfoAboutCompany(InfoAboutCompany infoAboutCompany){
        this.infoAboutCompany = infoAboutCompany;
    }

    public void setTableView(TableView<T> tableView){
        tableView.getColumns().get(0).getCellObservableValue(0).getValue().toString();
        this.tableView = tableView;
    }

    public void setStringData(ObservableList<StringTableFirst> observableList){
        this.stringData = observableList;
    }

    public void setStringDataForRash(ObservableList<StringTableFirst> observableList){
        this.stringDataForRash = observableList;
    }
}
