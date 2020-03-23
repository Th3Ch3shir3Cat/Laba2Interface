package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import sample.Information.InfoAboutCompany;
import sample.Table.StringTableFirst;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Controller {

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
    private Label numTotal;

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
    private Label sumFactOst;

    @FXML
    private Label dateFactOst;

    @FXML
    private Label numFactOst;

    @FXML
    private Label stoimostFactOst;

    @FXML
    private Label productFactOst;

    @FXML
    private Label speciiAndSoltFactOst;

    @FXML
    private Label taraFactOst;

    @FXML
    private Label steklotaraFactOst;

    /**
     * Излишки
     */

    @FXML
    private Label sumExcess;

    @FXML
    private Label dateExcess;

    @FXML
    private Label numExcess;

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
    private Label numDeficit;

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
    private void onClickRush(){
        //System.out.println(nameOrganizaition.getText());

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

    private Integer getSum(String name){
        int sum = 0;
        for(int i = 0; i < stringData.size(); i++){
            switch(name){
                case("sumTotal"):
                    sum += stringData.get(i).getSumFactRealize();
                    break;
                case("stoimostTotal"):
                    sum += stringData.get(i).getSumUchot();
                    break;
                case("productTotal"):
                    sum += stringData.get(i).getProduct();
                    break;
                case("speciiAndSoltTotal"):
                    sum += stringData.get(i).getSpeciiAndSolt();
                    break;
                case("taraTotal"):
                    sum += stringData.get(i).getTara();
                    break;
                case("steklotaraTotal"):
                    sum += stringData.get(i).getSteklotara();
                    break;
            }
        }
        return sum;
    }

    private void initData(){

        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");
        stringData.add(new StringTableFirst(1,"Остаток на начало дня", 22500,formatForDateNow.format(dateNow),53,33400,22403,10200,9000,4532,""));
        count++;

        sumTotal.setText(getSum("sumTotal").toString());
        dateTotal.setText(formatForDateNow.format(dateNow));
        stoimostTotal.setText(getSum("stoimostTotal").toString());
        productTotal.setText(getSum("productTotal").toString());
        speciiAndSoltTotal.setText(getSum("speciiAndSoltTotal").toString());
        taraTotal.setText(getSum("taraTotal").toString());
        steklotaraTotal.setText(getSum("steklotaraTotal").toString());

        sumFactOst.setText("0");
        dateFactOst.setText(formatForDateNow.format(dateNow));
        stoimostFactOst.setText("0");
        productFactOst.setText("0");
        speciiAndSoltFactOst.setText("0");
        taraFactOst.setText("0");
        steklotaraFactOst.setText("0");

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
        });
    }

}
