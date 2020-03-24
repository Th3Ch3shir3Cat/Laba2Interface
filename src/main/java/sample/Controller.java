package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import sample.Table.StringTableFirst;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Controller {

    private static int numTable = 1;
    private static final Date dateNow = new Date();
    private static final SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");
    /**
     * Шапка
     */
    @FXML
    private TextField nameOrganizaition;

    @FXML
    private TextField OKPO;

    @FXML
    private TextField OKDP;

    @FXML
    private TextField typeOperation;

    @FXML
    private TextField numberDocument;

    @FXML
    private TextField dateSost;

    @FXML
    private TextField fio;

    @FXML
    private TextField tableNum;

    @FXML
    private ComboBox<String> dolgnost;

    @FXML
    private ComboBox<String> structPodr;

    /**
     * Таблица
     */

    /**
     * Для прихода
     */
    private final ObservableList<StringTableFirst> stringData = FXCollections.observableArrayList();
    private static int count = 1;

    @FXML
    private Button prixod;

    /**
     * Для расхода
     */
    private final ObservableList<StringTableFirst> stringDataForRash = FXCollections.observableArrayList();
    private static int countRash = 1;

    @FXML
    private Button rasxod;

    @FXML
    private TableView tableStrings;

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

    /**
     * Подвал формы
     */

    @FXML
    private Button rashPodpisi;

    /**
     * Итого
     */
    @FXML
    private Label sumTotal;

    @FXML
    private Label dateTotal;


    @FXML
    private Label stoimostTotal;

    @FXML
    private Label productTotal;

    @FXML
    private Label speciiAndSoltTotal;

    @FXML
    private Label taraTotal;

    @FXML
    private Label steklotaraTotal;

    /**
     * Фактический остаток
     */
    @FXML
    private TextField sumFactOst;


    @FXML
    private Label dateFactOst;


    @FXML
    private TextField stoimostFactOst;

    @FXML
    private TextField productFactOst;

    @FXML
    private TextField speciiAndSoltFactOst;

    @FXML
    private TextField taraFactOst;

    @FXML
    private TextField steklotaraFactOst;

    /**
     * Излишки
     */

    @FXML
    private Label sumExcess;

    @FXML
    private Label dateExcess;


    @FXML
    private Label stoimostExcess;

    @FXML
    private Label productExcess;

    @FXML
    private Label speciiAndSoltExcess;

    @FXML
    private Label taraExcess;

    @FXML
    private Label steklotaraExcess;

    /**
     * Недостача
     */

    @FXML
    private Label sumDeficit;

    @FXML
    private Label dateDeficit;


    @FXML
    private Label stoimostDeficit;

    @FXML
    private Label productDeficit;

    @FXML
    private Label speciiAndSoltDeficit;

    @FXML
    private Label taraDeficit;

    @FXML
    private Label steklotaraDeficit;


    @FXML
    private void onClickRush() throws IOException {
        Stage stage  = (Stage) rashPodpisi.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/podpisi.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Расшифровка подписей");
        stage.setScene(new Scene(root1));
        stage.show();

        RoadToExcel roadToExcel = new RoadToExcel();
        roadToExcel.createXMLFile();
        tableStrings.setItems(stringData);
        roadToExcel.exportToExcel(tableStrings);
        tableStrings.setItems(stringDataForRash);
        roadToExcel.exportToExcel(tableStrings);
        roadToExcel.closeFiles();
    }

    @FXML
    private void onClickPrixod(){
        tableStrings.setItems(stringData);
        numTable = 1;
    }

    @FXML
    private void onClickRasxod(){
        tableStrings.setItems(stringDataForRash);
        numTable = 2;
    }

    @FXML
    private void initialize(){

        nameOrganizaition.setText("");
        OKPO.setText("");
        OKDP.setText("");
        typeOperation.setText("");
        numberDocument.setText("");
        dateSost.setText("");
        fio.setText("");
        tableNum.setText("");

        structPodr.getItems().setAll("Главный отдел", "Несовсем главный отдел", "Отдел кадров", "Бухгалтерия");
        dolgnost.getItems().setAll("Главный бухгалтер", "Неглавный бухгалтер", "Директор", "Зам.директора");
        initData();

        idColumn.setCellValueFactory(new PropertyValueFactory<StringTableFirst, Integer>("id"));

        nameColumn.setCellValueFactory(new PropertyValueFactory<StringTableFirst, String>("Name"));
        editStringCell(nameColumn,"Name");

        sumFactRealizeColumn.setCellValueFactory(new PropertyValueFactory<StringTableFirst, Integer>("SumFactRealize"));
        editIntegerCell(sumFactRealizeColumn,"SumFactRealize");

        dateColumn.setCellValueFactory(new PropertyValueFactory<StringTableFirst, Date>("DocumentDate"));

        numberColumn.setCellValueFactory(new PropertyValueFactory<StringTableFirst, Integer>("DocumentNumber"));
        editIntegerCell(numberColumn,"DocumentNumber");

        sumUchotColumn.setCellValueFactory(new PropertyValueFactory<StringTableFirst, Integer>("SumUchot"));
        editIntegerCell(sumUchotColumn,"SumUchot");

        productColumn.setCellValueFactory(new PropertyValueFactory<StringTableFirst, Integer>("Product"));
        editIntegerCell(productColumn,"Product");

        speciiAndSoltColumn.setCellValueFactory(new PropertyValueFactory<StringTableFirst, Integer>("SpeciiAndSolt"));
        editIntegerCell(speciiAndSoltColumn,"SpeciiAndSolt");

        taraColumn.setCellValueFactory(new PropertyValueFactory<StringTableFirst, Integer>("Tara"));
        editIntegerCell(taraColumn,"Tara");

        steklotaraColumn.setCellValueFactory(new PropertyValueFactory<StringTableFirst, Integer>("Steklotara"));
        editIntegerCell(steklotaraColumn,"Steklotara");

        buhOtmetkiColumn.setCellValueFactory(new PropertyValueFactory<StringTableFirst, String>("BuhOtmetki"));
        editStringCell(buhOtmetkiColumn,"BuhOtmetki");

        getEditForExcessAndDeficit(sumFactOst,sumDeficit,sumExcess,"sumFactOst", "sumTotal");
        getEditForExcessAndDeficit(stoimostFactOst,stoimostDeficit,stoimostExcess,"stoimostFactOst","stoimostTotal");
        getEditForExcessAndDeficit(productFactOst,productDeficit,productExcess,"productFactOst","productTotal");
        getEditForExcessAndDeficit(speciiAndSoltFactOst,speciiAndSoltDeficit,speciiAndSoltExcess,"speciiAndSoltFactOst","speciiAndSoltTotal");
        getEditForExcessAndDeficit(taraFactOst,taraDeficit,taraExcess,"taraFactOst", "taraTotal");
        getEditForExcessAndDeficit(steklotaraFactOst,steklotaraDeficit,steklotaraExcess,"steklotaraFactOst","steklotaraTotal");
    }


    private void getEditForExcessAndDeficit(final TextField textField, final Label labelDeficit, final Label labelExcess, final String name, final String total){
        textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER && textField.getText().length() > 0) {
                    Integer factOst = getFactOst(getTotal(total), name);
                    Integer raznica = factOst - Integer.parseInt(textField.getText());
                    if (raznica >= 0) {
                        labelDeficit.setText(raznica.toString());
                        labelExcess.setText("0");
                    } else {
                        labelExcess.setText(String.valueOf(Math.abs(raznica)));
                        labelDeficit.setText("0");
                    }
                }
            }
        });
    }

    private Integer getTotal(String name){
        int sum = 0;
        for(int i = 0; i < stringData.size(); i++){
            if ("sumTotal".equals(name)) {
                sum += stringData.get(i).getSumFactRealize();
            } else if ("stoimostTotal".equals(name)) {
                sum += stringData.get(i).getSumUchot();
            } else if ("productTotal".equals(name)) {
                sum += stringData.get(i).getProduct();
            } else if ("speciiAndSoltTotal".equals(name)) {
                sum += stringData.get(i).getSpeciiAndSolt();
            } else if ("taraTotal".equals(name)) {
                sum += stringData.get(i).getTara();
            } else if ("steklotaraTotal".equals(name)) {
                sum += stringData.get(i).getSteklotara();
            }
        }
        return sum;
    }

    private Integer getFactOst(int total,String name){
        int ost = total;
        for(int i = 0; i < stringDataForRash.size(); i++){
            if ("sumFactOst".equals(name)) {
                ost -= stringDataForRash.get(i).getSumFactRealize();
            } else if ("stoimostFactOst".equals(name)) {
                ost -= stringDataForRash.get(i).getSumUchot();
            } else if ("productFactOst".equals(name)) {
                ost -= stringDataForRash.get(i).getProduct();
            } else if ("speciiAndSoltFactOst".equals(name)) {
                ost -= stringDataForRash.get(i).getSpeciiAndSolt();
            } else if ("taraFactOst".equals(name)) {
                ost -= stringDataForRash.get(i).getTara();
            } else if ("steklotaraFactOst".equals(name)) {
                ost -= stringDataForRash.get(i).getSteklotara();
            }
        }
        return ost;
    }

    private void initData(){

        stringData.add(new StringTableFirst(1,"Остаток на начало дня", 22500,formatForDateNow.format(dateNow),53,33400,22403,10200,9000,4532,""));
        count++;

        stringDataForRash.add(new StringTableFirst(1,"",0,formatForDateNow.format(dateNow),null,0,0,0,0,0,""));
        countRash++;

        sumTotal.setText(getTotal("sumTotal").toString());
        dateTotal.setText(formatForDateNow.format(dateNow));
        stoimostTotal.setText(getTotal("stoimostTotal").toString());
        productTotal.setText(getTotal("productTotal").toString());
        speciiAndSoltTotal.setText(getTotal("speciiAndSoltTotal").toString());
        taraTotal.setText(getTotal("taraTotal").toString());
        steklotaraTotal.setText(getTotal("steklotaraTotal").toString());

        dateFactOst.setText(formatForDateNow.format(dateNow));

        sumExcess.setText("0");
        dateExcess.setText(formatForDateNow.format(dateNow));
        stoimostExcess.setText("0");
        productExcess.setText("0");
        speciiAndSoltExcess.setText("0");
        taraExcess.setText("0");
        steklotaraExcess.setText("0");

        sumDeficit.setText("0");
        dateDeficit.setText(formatForDateNow.format(dateNow));
        stoimostDeficit.setText("0");
        productDeficit.setText("0");
        speciiAndSoltDeficit.setText("0");
        taraDeficit.setText("0");
        steklotaraDeficit.setText("0");
    }

    //Делаем редактор для строк
    private void editStringCell(TableColumn<StringTableFirst, String> column, String name){
        column.setCellFactory(TextFieldTableCell.<StringTableFirst>forTableColumn());
        column.setOnEditCommit((TableColumn.CellEditEvent<StringTableFirst,String> event)-> {
            TablePosition<StringTableFirst, String> pos = event.getTablePosition();
            String newName = event.getNewValue();
            int row = pos.getRow();
            StringTableFirst stringTableFirst = event.getTableView().getItems().get(row);
            if ("Name".equals(name)) {
                stringTableFirst.setName(newName);
                if (newName.length() != 0) {
                    if (numTable == 1 && row == stringData.size() - 1) {
                        stringData.add(new StringTableFirst(count, "", 0, formatForDateNow.format(dateNow), null, 0, 0, 0, 0, 0, ""));
                        count++;
                    } else if (numTable == 2 && row == stringDataForRash.size() - 1) {
                        stringDataForRash.add(new StringTableFirst(countRash, "", 0, formatForDateNow.format(dateNow), null, 0, 0, 0, 0, 0, ""));
                        countRash++;
                    }
                }
            } else if ("BuhOtmetki".equals(name)) {
                stringTableFirst.setBuhOtmetki(newName);
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
            if ("SumFactRealize".equals(name)) {
                stringTableFirst.setSumFactRealize(newName);
                sumTotal.setText(getTotal("sumTotal").toString());
            } else if ("DocumentNumber".equals(name)) {
                stringTableFirst.setDocumentNumber(newName);
            } else if ("SumUchot".equals(name)) {
                stringTableFirst.setSumUchot(newName);
                stoimostTotal.setText(getTotal("stoimostTotal").toString());
            } else if ("Product".equals(name)) {
                stringTableFirst.setProduct(newName);
                productTotal.setText(getTotal("productTotal").toString());
            } else if ("SpeciiAndSolt".equals(name)) {
                stringTableFirst.setSpeciiAndSolt(newName);
                speciiAndSoltTotal.setText(getTotal("speciiAndSoltTotal").toString());
            } else if ("Tara".equals(name)) {
                stringTableFirst.setTara(newName);
                taraTotal.setText(getTotal("taraTotal").toString());
            } else if ("Steklotara".equals(name)) {
                stringTableFirst.setSteklotara(newName);
                steklotaraTotal.setText(getTotal("steklotaraTotal").toString());
            }
        });
    }

}
