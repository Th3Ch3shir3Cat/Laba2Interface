package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Table.StringTableFirst;

import java.util.Date;

public class Controller {

    private ObservableList<StringTableFirst> stringData = FXCollections.observableArrayList();

    @FXML
    private TableView<StringTableFirst> tableStrings;

    @FXML
    private TableColumn<StringTableFirst, Integer> idColumn;

    @FXML
    private TableColumn<StringTableFirst, String> nameColumn;

    @FXML
    private TableColumn<StringTableFirst, Double> sumFactRealizeColumn;

    @FXML
    private TableColumn<StringTableFirst, Date> dateColumn;

    @FXML
    private TableColumn<StringTableFirst, Long> numberColumn;

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
        sumFactRealizeColumn.setCellValueFactory(new PropertyValueFactory<StringTableFirst,Double>("SumFactRealize"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<StringTableFirst,Date>("DocumentDate"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<StringTableFirst,Long>("DocumentNumber"));
        sumUchotColumn.setCellValueFactory(new PropertyValueFactory<StringTableFirst,Integer>("SumUchot"));
        productColumn.setCellValueFactory(new PropertyValueFactory<StringTableFirst,Integer>("Product"));
        speciiAndSoltColumn.setCellValueFactory(new PropertyValueFactory<StringTableFirst,Integer>("SpeciiAndSolt"));
        taraColumn.setCellValueFactory(new PropertyValueFactory<StringTableFirst,Integer>("Tara"));
        steklotaraColumn.setCellValueFactory(new PropertyValueFactory<StringTableFirst,Integer>("Steklotara"));
        buhOtmetkiColumn.setCellValueFactory(new PropertyValueFactory<StringTableFirst,String>("BuhOtmetki"));

        tableStrings.setItems(stringData);

    }

    private void initData(){
        stringData.add(new StringTableFirst(1,"Остаток на начало дня"));
        stringData.add(new StringTableFirst(2,"Приход"));
    }

}
