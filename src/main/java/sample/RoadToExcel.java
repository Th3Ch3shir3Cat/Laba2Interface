package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
public class RoadToExcel {

    private static FileOutputStream fileOut;
    private static HSSFWorkbook workbook;
    private static HSSFSheet spreadSheet;
    private static int rowCount;

    RoadToExcel(){
        rowCount = 0;
        workbook = new HSSFWorkbook();
        spreadSheet = workbook.createSheet("sample");
    }

    public void createXMLFile() throws FileNotFoundException {
        fileOut = new FileOutputStream("workbook.xls");
    }

    public void exportToExcel(TableView<T> tableView) throws IOException {
        for(int i = 0; i < tableView.getItems().size()-1; i++){
            HSSFRow hssfRow = spreadSheet.createRow(rowCount++);
            for(int j = 0; j < tableView.getColumns().size(); j++){
                Object cellValue = tableView.getColumns().get(j).getCellObservableValue(i).getValue();
                hssfRow.createCell(j).setCellValue(cellValue.toString());
            }

        }


    }

    public void closeFiles() throws IOException {

        workbook.write(fileOut);
        fileOut.close();
        workbook.close();
    }

}
