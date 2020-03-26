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
import org.controlsfx.control.textfield.TextFields;
import sample.Information.InfoAboutCompany;
import sample.Information.TotalPrixod;
import sample.Information.TotalRashod;
import sample.Table.StringTableFirst;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Controller {

    private static int numTable = 1;
    private static final Date dateNow = new Date();
    private static final SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");

    private static InfoAboutCompany infoAboutCompany;
    private static TotalPrixod totalPrixod;
    private static TotalRashod totalRashod;

    private static List<String> list = new ArrayList();
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
    private ObservableList<StringTableFirst> stringData = FXCollections.observableArrayList();
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
    private Label sumPrixodRashod;

    @FXML
    private Label datePrixodRashod;

    @FXML
    private Label stoimostPrixodRashod;

    @FXML
    private Label productPrixodRashod;

    @FXML
    private Label speciiAndSoltPrixodRashod;

    @FXML
    private Label taraPrixodRashod;

    @FXML
    private Label steklotaraPrixodRashod;

    @FXML
    private Label labelForPrixodRashod;
    @FXML
    private void onClickRush() throws IOException, ParseException {

        stringData.remove(stringData.size()-1);

        stringData.add(new StringTableFirst(0,"",Integer.parseInt(totalPrixod.getSumPrixod()),
                dateTotal.getText(),1,Integer.parseInt(totalPrixod.getStoimostPrixod()),
                Integer.parseInt(totalPrixod.getProductPrixod()), Integer.parseInt(totalPrixod.getSpeciiAndSoltPrixod()),
                Integer.parseInt(totalPrixod.getTaraPrixod()), Integer.parseInt(totalPrixod.getSteklotaraPrixod()),""
                ));

        stringData.add(new StringTableFirst(
                0,"",Integer.parseInt(sumTotal.getText()),dateTotal.getText(),1,Integer.parseInt(stoimostTotal.getText()),Integer.parseInt(productTotal.getText()),
                Integer.parseInt(speciiAndSoltTotal.getText()),Integer.parseInt(taraTotal.getText()),Integer.parseInt(steklotaraTotal.getText()),""));

        stringDataForRash.remove(stringDataForRash.size()-1);

        Integer sumFO = getFactOst(Integer.parseInt(sumTotal.getText()),"sumFactOst");
        Integer stoimostFO = getFactOst(Integer.parseInt(stoimostTotal.getText()),"stoimostFactOst");
        Integer productFO = getFactOst(Integer.parseInt(productTotal.getText()),"productTotal");
        Integer speciiAndSoltFO = getFactOst(Integer.parseInt(speciiAndSoltTotal.getText()),"speciiAndSoltFactOst");
        Integer taraFO = getFactOst(Integer.parseInt(taraTotal.getText()),"taraTotal");
        Integer steklotaraFO = getFactOst(Integer.parseInt(steklotaraTotal.getText()),"steklotaraFactOst");

        stringDataForRash.add(new StringTableFirst(0,"",
                Integer.parseInt(totalRashod.getSumRashod()),dateTotal.getText(),
                1, Integer.parseInt(totalRashod.getStoimostRashod()),
                Integer.parseInt(totalRashod.getProductRashod()), Integer.parseInt(totalRashod.getSpeciiAndSoltRashod()),
                Integer.parseInt(totalRashod.getTaraRashod()), Integer.parseInt(totalRashod.getSteklotaraRashod()),""
                ));

        stringDataForRash.add(new StringTableFirst(0,"",sumFO,
                dateFactOst.getText(),1,stoimostFO,
                productFO,
                speciiAndSoltFO,
                taraFO,
                steklotaraFO,""
                ));

        stringDataForRash.add(new StringTableFirst(0,"",
                Integer.parseInt(sumFactOst.getText()),dateFactOst.getText(),1,Integer.parseInt(stoimostFactOst.getText()),
                Integer.parseInt(productFactOst.getText()), Integer.parseInt(speciiAndSoltFactOst.getText()),
                Integer.parseInt(taraFactOst.getText()),Integer.parseInt(steklotaraFactOst.getText()),""));

        stringDataForRash.add(new StringTableFirst(0,"",
                Integer.parseInt(sumExcess.getText()),dateExcess.getText(),1,Integer.parseInt(stoimostExcess.getText()),
                Integer.parseInt(productExcess.getText()), Integer.parseInt(speciiAndSoltExcess.getText()),
                Integer.parseInt(taraExcess.getText()),Integer.parseInt(steklotaraExcess.getText()),""));

        stringDataForRash.add(new StringTableFirst(0,"",
                Integer.parseInt(sumDeficit.getText()),dateDeficit.getText(),1,Integer.parseInt(stoimostDeficit.getText()),
                Integer.parseInt(productDeficit.getText()), Integer.parseInt(speciiAndSoltDeficit.getText()),
                Integer.parseInt(taraDeficit.getText()),Integer.parseInt(steklotaraDeficit.getText()),""));

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/podpisi.fxml"));
        Parent root = fxmlLoader.load();
        ControllerForPodpisi controllerForPodpisi = fxmlLoader.getController();

        controllerForPodpisi.setTableView(tableStrings);
        controllerForPodpisi.setStringData(stringData);
        controllerForPodpisi.setStringDataForRash(stringDataForRash);
        controllerForPodpisi.setMaterialPodpis(fio.getText());

        setInfoAboutCompany();
        controllerForPodpisi.setInfoAboutCompany(infoAboutCompany);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Расшифровка подписей");
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    private void onClickPrixod(){
        tableStrings.setItems(stringData);
        labelForPrixodRashod.setText("Итого по приходу");

        sumPrixodRashod.setText(totalPrixod.getSumPrixod());
        stoimostPrixodRashod.setText(totalPrixod.getStoimostPrixod());
        productPrixodRashod.setText(totalPrixod.getProductPrixod());
        speciiAndSoltPrixodRashod.setText(totalPrixod.getSpeciiAndSoltPrixod());
        taraPrixodRashod.setText(totalPrixod.getTaraPrixod());
        steklotaraPrixodRashod.setText(totalPrixod.getSteklotaraPrixod());

        numTable = 1;
    }

    @FXML
    private void onClickRasxod(){
        tableStrings.setItems(stringDataForRash);
        numTable = 2;
        labelForPrixodRashod.setText("Итого по расходу");

        sumPrixodRashod.setText(totalRashod.getSumRashod());
        stoimostPrixodRashod.setText(totalRashod.getStoimostRashod());
        productPrixodRashod.setText(totalRashod.getProductRashod());
        speciiAndSoltPrixodRashod.setText(totalRashod.getSpeciiAndSoltRashod());
        taraPrixodRashod.setText(totalRashod.getTaraRashod());
        steklotaraPrixodRashod.setText(totalRashod.getSteklotaraRashod());

    }


    public String getNumTable(String string){
        if(string.equals("ООО Вымпел"))
            return "101";
        else if(string.equals("ООО Заказ"))
            return "400";
        else if(string.equals("ООО Едим как дома"))
            return "777";

        return "";
    }

    public String getOkpo(String string){
        if(string.equals("ООО Вымпел"))
            return "22243";
        else if(string.equals("ООО Заказ"))
            return "11999";
        else if(string.equals("ООО Едим как дома"))
            return "9999";

        return "";
    }

    public String getOkdp(String string){
        if(string.equals("ООО Вымпел"))
            return "55.30";
        else if(string.equals("ООО Заказ"))
            return "11.03";
        else if(string.equals("ООО Едим как дома"))
            return "10.02";


        return "";
    }

    public String getTypeOperations(String string){
        if(string.equals("ООО Вымпел"))
            return "01";
        else if(string.equals("ООО Заказ"))
            return "02";
        else if(string.equals("ООО Едим как дома"))
            return "03";

        return "";
    }

    @FXML
    private void initialize(){

        infoAboutCompany = new InfoAboutCompany();
        totalPrixod = new TotalPrixod();
        totalRashod = new TotalRashod();

        list.add("ООО Вымпел");
        list.add("ООО Заказ");
        list.add("ООО Едим как дома");

        TextFields.bindAutoCompletion(nameOrganizaition,list);
        nameOrganizaition.textProperty().addListener((observable, oldValue, newValue) -> {
                numberDocument.setText(getNumTable(nameOrganizaition.getText()));
                OKPO.setText(getOkpo(nameOrganizaition.getText()));
                OKDP.setText(getOkdp(nameOrganizaition.getText()));
                typeOperation.setText(getTypeOperations(nameOrganizaition.getText()));
        });

        nameOrganizaition.setText("");
        OKPO.setText("");
        OKDP.setText("");
        typeOperation.setText("");
        numberDocument.setText("");
        dateSost.setText(formatForDateNow.format(dateNow));
        fio.setText("");
        tableNum.setText("");

        sumPrixodRashod.setText("0");
        datePrixodRashod.setText(dateFactOst.getText());
        stoimostPrixodRashod.setText("0");
        productPrixodRashod.setText("0");
        speciiAndSoltPrixodRashod.setText("0");
        taraPrixodRashod.setText("0");
        steklotaraPrixodRashod.setText("0");

        totalPrixod.setSteklotaraPrixod("0");
        totalPrixod.setTaraPrixod("0");
        totalPrixod.setSpeciiAndSoltPrixod("0");
        totalPrixod.setProductPrixod("0");
        totalPrixod.setStoimostPrixod("0");
        totalPrixod.setSumPrixod("0");

        totalRashod.setSteklotaraRashod("0");
        totalRashod.setTaraRashod("0");
        totalRashod.setSpeciiAndSoltRashod("0");
        totalRashod.setProductRashod("0");
        totalRashod.setStoimostRashod("0");
        totalRashod.setSumRashod("0");


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


    public void setInfoAboutCompany() throws ParseException {
        infoAboutCompany.setNumberDocument(Integer.parseInt(numberDocument.getText()));
        infoAboutCompany.setDateSost(formatForDateNow.parse(dateSost.getText()));
        infoAboutCompany.setNameOrganization(nameOrganizaition.getText());
        infoAboutCompany.setOkpo(OKPO.getText());
        infoAboutCompany.setOkdp(OKDP.getText());
        infoAboutCompany.setTypeOperation(typeOperation.getText());
        infoAboutCompany.setStructurePodr(structPodr.getValue());
        infoAboutCompany.setMaterialDolgnost(dolgnost.getValue());
        infoAboutCompany.setMaterialFIO(fio.getText());
        infoAboutCompany.setTableNum(tableNum.getText());
    }

    public InfoAboutCompany getInfoAboutCompany(){
        return infoAboutCompany;
    }

    private void getEditForExcessAndDeficit(final TextField textField, final Label labelDeficit, final Label labelExcess, final String name, final String total){
        textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER && textField.getText().length() > 0) {
                    Integer factOst = getFactOst(getTotal(stringData,total), name);
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

    private Integer getTotal(ObservableList<StringTableFirst> stringData,String name){
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

        stringData.add(new StringTableFirst(1,"I.Остаток на начало дня", 22500,formatForDateNow.format(dateNow),53,33400,22403,10200,9000,4532,""));
        count++;

        stringDataForRash.add(new StringTableFirst(1,"",0,formatForDateNow.format(dateNow),null,0,0,0,0,0,""));
        countRash++;

        sumTotal.setText(getTotal(stringData,"sumTotal").toString());
        dateTotal.setText(formatForDateNow.format(dateNow));
        stoimostTotal.setText(getTotal(stringData,"stoimostTotal").toString());
        productTotal.setText(getTotal(stringData,"productTotal").toString());
        speciiAndSoltTotal.setText(getTotal(stringData,"speciiAndSoltTotal").toString());
        taraTotal.setText(getTotal(stringData,"taraTotal").toString());
        steklotaraTotal.setText(getTotal(stringData,"steklotaraTotal").toString());

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
                sumTotal.setText(getTotal(stringData,"sumTotal").toString());
                if(numTable == 1){
                    sumPrixodRashod.setText(String.valueOf(getTotal(stringData,"sumTotal") - stringData.get(0).getSumFactRealize()));
                    totalPrixod.setSumPrixod(sumPrixodRashod.getText());
                }
                if(numTable == 2){
                    sumPrixodRashod.setText(String.valueOf(getTotal(stringDataForRash,"sumTotal")));
                    totalRashod.setSumRashod(sumPrixodRashod.getText());
                }
            } else if ("DocumentNumber".equals(name)) {
                stringTableFirst.setDocumentNumber(newName);
            } else if ("SumUchot".equals(name)) {
                stringTableFirst.setSumUchot(newName);
                stoimostTotal.setText(getTotal(stringData,"stoimostTotal").toString());
                if(numTable == 1){
                    stoimostPrixodRashod.setText(String.valueOf(getTotal(stringData,"stoimostTotal") - stringData.get(0).getSumUchot()));
                    totalPrixod.setStoimostPrixod(stoimostPrixodRashod.getText());
                }
                if(numTable == 2){
                    stoimostPrixodRashod.setText(String.valueOf(getTotal(stringDataForRash,"stoimostTotal")));
                    totalRashod.setStoimostRashod(stoimostPrixodRashod.getText());
                }
            } else if ("Product".equals(name)) {
                stringTableFirst.setProduct(newName);
                productTotal.setText(getTotal(stringData,"productTotal").toString());
                if(numTable == 1){
                    productPrixodRashod.setText(String.valueOf(getTotal(stringData,"productTotal") - stringData.get(0).getProduct()));
                    totalPrixod.setProductPrixod(productPrixodRashod.getText());
                }
                if(numTable == 2){
                    productPrixodRashod.setText(String.valueOf(getTotal(stringDataForRash,"productTotal")));
                    totalRashod.setProductRashod(productPrixodRashod.getText());
                }
            } else if ("SpeciiAndSolt".equals(name)) {
                stringTableFirst.setSpeciiAndSolt(newName);
                speciiAndSoltTotal.setText(getTotal(stringData,"speciiAndSoltTotal").toString());
                if(numTable == 1){
                    speciiAndSoltPrixodRashod.setText(String.valueOf(getTotal(stringData,"speciiAndSoltTotal") - stringData.get(0).getSpeciiAndSolt()));
                    totalPrixod.setSpeciiAndSoltPrixod(speciiAndSoltPrixodRashod.getText());
                }
                if(numTable == 2){
                    speciiAndSoltPrixodRashod.setText(String.valueOf(getTotal(stringDataForRash,"speciiAndSoltTotal")));
                    totalRashod.setSpeciiAndSoltRashod(speciiAndSoltPrixodRashod.getText());
                }
            } else if ("Tara".equals(name)) {
                stringTableFirst.setTara(newName);
                taraTotal.setText(getTotal(stringData,"taraTotal").toString());
                if(numTable == 1){
                    taraPrixodRashod.setText(String.valueOf(getTotal(stringData,"taraTotal") - stringData.get(0).getTara()));
                    totalPrixod.setTaraPrixod(taraPrixodRashod.getText());
                }
                if(numTable == 2){
                    taraPrixodRashod.setText(String.valueOf(getTotal(stringDataForRash,"taraTotal")));
                    totalRashod.setTaraRashod(taraPrixodRashod.getText());
                }
            } else if ("Steklotara".equals(name)) {
                stringTableFirst.setSteklotara(newName);
                steklotaraTotal.setText(getTotal(stringData,"steklotaraTotal").toString());
                if(numTable == 1){
                    steklotaraPrixodRashod.setText(String.valueOf(getTotal(stringData,"steklotaraTotal") - stringData.get(0).getSteklotara()));
                    totalPrixod.setSteklotaraPrixod(steklotaraPrixodRashod.getText());
                }
                if(numTable == 2){
                    steklotaraPrixodRashod.setText(String.valueOf(getTotal(stringDataForRash,"steklotaraTotal")));
                    totalRashod.setSteklotaraRashod(steklotaraPrixodRashod.getText());
                }
            }
        });
    }

}
