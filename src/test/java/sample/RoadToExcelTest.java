package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Test;
import sample.Information.InfoAboutCompany;
import sample.Table.StringTableFirst;

import javax.swing.text.TabableView;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RoadToExcelTest {

    @Test
    public void exportToExcelInfoAboutComp() throws ParseException, IOException {
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");
        Date dateNow = new Date();

        InfoAboutCompany infoAboutCompany = new InfoAboutCompany(
                "ООО Вымпел", 116,formatForDateNow.parse("10.10.2022"),"5567788",
                "2345","01","Что то там такое","Недиректор","Хаустов Г.И.","114"
        );
        TabableView tabableView = null;
        ObservableList<StringTableFirst> observableList = FXCollections.observableArrayList();;
        observableList.add(new StringTableFirst(1,"Остаток на начало дня", 22500,formatForDateNow.format(dateNow),53,33400,22403,10200,9000,4532,""));
        RoadToExcel roadToExcel = new RoadToExcel();
        roadToExcel.exportToExcelInfoAboutComp(infoAboutCompany);

        roadToExcel.closeFiles();
    }
}