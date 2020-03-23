package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import sample.Table.StringTableFirst;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Controller {

    private ObservableList<StringTableFirst> stringData = FXCollections.observableArrayList();
    private static int count = 1;

    @FXML
    private TableView<StringTableFirst> tableStrings;

    @FXML
    private TableColumn<StringTableFirst, Integer> idColumn;

    @FXML
    private TableColumn<StringTableFirst, String> nameColumn;

    @FXML
    private TableColumn<StringTableFirst, Integer> sumFactRealizeColumn;

    @FXML
    private TableColumn<StringTableFirst, Date> dateColumn;

    @FXML
    private TableColumn<StringTableFirst, Integer> numberColumn;

    @FXML
    private TableColumn<StringTableFirst, Integer> sumUchotColumn;

    @FXML
    private TableColumn<StringTableFirst, Integer> productColumn;

    @FXML
    private TableColumn<StringTableFirst, Integer> speciiAndSoltColumn;

    @FXML
    private TableColumn<StringTableFirst, Integer> taraColumn;

    @FXML
    private TableColumn<StringTableFirst, Integer> steklotaraColumn;

    @FXML
    private TableColumn<StringTableFirst, String> buhOtmetkiColumn;

    @FXML
    private void initialize(){
        initData();

        idColumn.setCellValueFactory(new PropertyValueFactory<StringTableFirst,Integer>("id"));

        nameColumn.setCellValueFactory(new PropertyValueFactory<StringTableFirst,String>("Name"));
        editStringCell(nameColumn,"Name");

        sumFactRealizeColumn.setCellValueFactory(new PropertyValueFactory<StringTableFirst,Integer>("SumFactRealize"));
        editIntegerCell(sumFactRealizeColumn,"SumFactRealize");

        dateColumn.setCellValueFactory(new PropertyValueFactory<StringTableFirst,Date>("DocumentDate"));

        numberColumn.setCellValueFactory(new PropertyValueFactory<StringTableFirst,Integer>("DocumentNumber"));
        editIntegerCell(numberColumn,"DocumentNumber");

        sumUchotColumn.setCellValueFactory(new PropertyValueFactory<StringTableFirst,Integer>("SumUchot"));
        editIntegerCell(sumUchotColumn,"SumUchot");

        productColumn.setCellValueFactory(new PropertyValueFactory<StringTableFirst,Integer>("Product"));
        editIntegerCell(productColumn,"Product");

        speciiAndSoltColumn.setCellValueFactory(new PropertyValueFactory<StringTableFirst,Integer>("SpeciiAndSolt"));
        editIntegerCell(speciiAndSoltColumn,"SpeciiAndSolt");

        taraColumn.setCellValueFactory(new PropertyValueFactory<StringTableFirst,Integer>("Tara"));
        editIntegerCell(taraColumn,"Tara");

        steklotaraColumn.setCellValueFactory(new PropertyValueFactory<StringTableFirst,Integer>("Steklotara"));
        editIntegerCell(steklotaraColumn,"Steklotara");

        buhOtmetkiColumn.setCellValueFactory(new PropertyValueFactory<StringTableFirst,String>("BuhOtmetki"));
        editStringCell(buhOtmetkiColumn,"BuhOtmetki");


        tableStrings.setItems(stringData);

    }

    private void initData(){

        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");
        stringData.add(new StringTableFirst(1,"Остаток на начало дня", 22500,formatForDateNow.format(dateNow),53,33400,22403,10200,9000,4532,""));
        count++;
    }

    //Делаем редактор для строк
    private void editStringCell(TableColumn<StringTableFirst, String> column, String name){
        column.setCellFactory(TextFieldTableCell.<StringTableFirst>forTableColumn());
        column.setOnEditCommit((TableColumn.CellEditEvent<StringTableFirst,String> event)-> {
            TablePosition<StringTableFirst, String> pos = event.getTablePosition();
            String newName = event.getNewValue();
            int row = pos.getRow();
            StringTableFirst stringTableFirst = event.getTableView().getItems().get(row);
            switch(name){
                case("Name"):
                    stringTableFirst.setName(newName);
                    if(newName.length() != 0) {
                        stringData.add(new StringTableFirst(count, ""));
                        count++;
                    }
                    break;
                case("BuhOtmetki"):
                    stringTableFirst.setBuhOtmetki(newName);
                    break;
            }

        });
    }

    //Делаем редактор для чисел
    private void editIntegerCell(TableColumn<StringTableFirst, Integer> column,String name){
        column.setCellFactory(TextFieldTableCell.<StringTableFirst, Integer>forTableColumn(new IntegerStringConverter()));
        column.setOnEditCommit((TableColumn.CellEditEvent<StringTableFirst,Integer> event)-> {
            TablePosition<StringTableFirst, Integer> pos = event.getTablePosition();
            Integer newName = event.getNewValue();
            int row = pos.getRow();
            StringTableFirst stringTableFirst = event.getTableView().getItems().get(row);
            switch(name){
                case("SumFactRealize"):
                    stringTableFirst.setSumFactRealize(newName);
                    break;
                case("DocumentNumber"):
                    stringTableFirst.setDocumentNumber(newName);
                    break;
                case("SumUchot"):
                    stringTableFirst.setSumUchot(newName);
                    break;
                case("Product"):
                    stringTableFirst.setProduct(newName);
                    break;
                case("SpeciiAndSolt"):
                    stringTableFirst.setSpeciiAndSolt(newName);
                    break;
                case("Tara"):
                    stringTableFirst.setTara(newName);
                    break;
                case("Steklotara"):
                    stringTableFirst.setSteklotara(newName);
                    break;

            }
            stringTableFirst.setSumFactRealize(newName);
        });
    }

}
