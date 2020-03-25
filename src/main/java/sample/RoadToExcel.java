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
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.formula.functions.T;
import sample.Information.InfoAboutCompany;

import javax.swing.text.TabableView;
import java.io.*;
import java.text.SimpleDateFormat;

public class RoadToExcel {

    public static HSSFWorkbook workbook;
    public static HSSFSheet spreadSheet;
    public static SimpleDateFormat formatForDateNow;
    public static int[]masIndexCells;
    public static File file;
    public static FileInputStream inputStream;


    public RoadToExcel() throws IOException {

        file = new File("t-144.XLS");
        inputStream = new FileInputStream(file);
        workbook = new HSSFWorkbook(inputStream);
        spreadSheet = workbook.getSheetAt(0);
        formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");
        masIndexCells = new int[]{0,10,14,18,22,28,32,36,39,42};
    }


    public HSSFFont createCellFont(HSSFWorkbook hssfWorkbook){
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short)12);
        font.setFontName("Times New Roman");
        return (font);
    }

    public void editToCell(int indexRow, int indexColumn, String string){
        HSSFCell cell = spreadSheet.getRow(indexRow).getCell(indexColumn);
        if(cell == null){
            cell = spreadSheet.createRow(indexRow).createCell(indexColumn);
        }
        cell.setCellValue(string);
    }

    public void editToCellTableColumn(int indexRow, int indexColumn, TableView<T> tableView, int indexTableRow, int indexTableColumn){
        editToCell(indexRow,indexColumn,tableView.getColumns().get(indexTableColumn).getCellObservableValue(indexTableRow).getValue().toString());
    }

    public void exportToExcelInfoAboutComp(InfoAboutCompany infoAboutCompany){
        editToCell(6,0,infoAboutCompany.getNameOrganization());
        editToCell(6,40,infoAboutCompany.getOkpo());

        editToCell(8,0,infoAboutCompany.getStructurePodr());

        editToCell(9,40,infoAboutCompany.getOkdp());

        editToCell(10,40,infoAboutCompany.getTypeOperation());

        editToCell(13,31,infoAboutCompany.getNumberDocument().toString());

        editToCell(13,40,formatForDateNow.format(infoAboutCompany.getDateSost()));

        editToCell(17,16,infoAboutCompany.getMaterialDolgnost());

        editToCell(17,25,infoAboutCompany.getMaterialFIO());

        editToCell(17,45,infoAboutCompany.getTableNum());
    }

    public void exportToExcelTable(TableView<T> tableView, int tableNum){
        if(tableNum == 1) {
            /**
             * Остаток на начало дня
             */
            editToCellTableColumn(25, 0, tableView, 0, 1);
            editToCellTableColumn(25, 10, tableView, 0, 2);
            editToCellTableColumn(25, 14, tableView, 0, 3);
            editToCellTableColumn(25, 18, tableView, 0, 4);
            editToCellTableColumn(25, 22, tableView, 0, 5);
            editToCellTableColumn(25, 28, tableView, 0, 6);
            editToCellTableColumn(25, 32, tableView, 0, 7);
            editToCellTableColumn(25, 36, tableView, 0, 8);
            editToCellTableColumn(25, 39, tableView, 0, 9);
            editToCellTableColumn(25, 42, tableView, 0, 10);

            /**
             * Приход
             */
            for (int i = 1; i < tableView.getColumns().size(); i++) {
                for (int j = 1; j < tableView.getItems().size() - 2; j++) {
                    editToCellTableColumn(j + 27, masIndexCells[i - 1], tableView, j, i);
                }
            }

            /**
             * Итого по приходу
             */
            for(int i = 2; i < tableView.getColumns().size(); i++){
                editToCellTableColumn(48,masIndexCells[i-1],tableView,tableView.getItems().size()-1,i);
            }

            /**
             * Итого с остатком(оборотная сторона)
             */
            for(int i = 2; i < tableView.getColumns().size(); i++){
                editToCellTableColumn(57,masIndexCells[i-1],tableView,tableView.getItems().size()-1,i);
            }

        }
        else{

            /**
             * Расход
             */
            for (int i = 1; i < tableView.getColumns().size(); i++) {
                for (int j = 0; j < tableView.getItems().size()-2; j++) {
                    editToCellTableColumn(j + 59, masIndexCells[i - 1], tableView, j, i);
                }
            }

            /**
             * Остаток на конец дня
             */

            for(int i = 2; i < tableView.getColumns().size(); i++){
                editToCellTableColumn(74,masIndexCells[i-1],tableView,tableView.getItems().size()-1,i);
            }
        }
    }

/*
    public void exportToExcelInfoAboutCompany(InfoAboutCompany infoAboutCompany){


        HSSFRow hssfRow = spreadSheet.createRow(rowCount++);
        hssfRow.createCell(9).setCellValue("Униицированная форма № ОП-14");
        hssfRow = spreadSheet.createRow(rowCount++);
        hssfRow.createCell(9).setCellValue("Утверждена постоновлением Госкомстата");
        hssfRow = spreadSheet.createRow(rowCount++);
        hssfRow.createCell(9).setCellValue("Росии от 25.12.98 № 132");
        rowCount++;
        hssfRow = spreadSheet.createRow(rowCount++);
        hssfRow.createCell(10).setCellValue("Код");
        rowCount++;
        hssfRow = spreadSheet.createRow(rowCount++);
        hssfRow.createCell(8).setCellValue("Форма");
        hssfRow.createCell(9).setCellValue("по ОКУД");
        hssfRow.createCell(11).setCellValue("0330514");
        hssfRow = spreadSheet.createRow(rowCount++);
        CellRangeAddress region = new CellRangeAddress(rowCount-1,rowCount-1,1,8);
        hssfRow.createCell(1).setCellValue(infoAboutCompany.getNameOrganization());
        hssfRow.createCell(9).setCellValue("по ОКПО");
        hssfRow.createCell(11).setCellValue(infoAboutCompany.getOkpo());
        spreadSheet.addMergedRegion(region);
        hssfRow = spreadSheet.createRow(rowCount++);
        region = new CellRangeAddress(rowCount-1,rowCount-1,1,8);
        spreadSheet.addMergedRegion(region);
        hssfRow.createCell(1).setCellValue(infoAboutCompany.getStructurePodr());
        hssfRow = spreadSheet.createRow(rowCount++);
        hssfRow.createCell(7).setCellValue("Вид деятельности по ОКДП");
        hssfRow.createCell(11).setCellValue(infoAboutCompany.getOkdp());
        hssfRow = spreadSheet.createRow(rowCount++);
        hssfRow.createCell(8).setCellValue("Вид операции");
        hssfRow.createCell(11).setCellValue(infoAboutCompany.getTypeOperation());
        rowCount++;
        hssfRow = spreadSheet.createRow(rowCount++);
        hssfRow.createCell(8).setCellValue("Номер документа");
        hssfRow.createCell(10).setCellValue("Дата составления");
        region = new CellRangeAddress(rowCount,rowCount,3,7);
        spreadSheet.addMergedRegion(region);
        hssfRow = spreadSheet.createRow(rowCount++);
        hssfRow.createCell(3).setCellValue("ВЕДОМОСТЬ УЧЕТА");

    }
*/

    public void closeFiles() throws IOException {

        inputStream.close();
        FileOutputStream out = new FileOutputStream(file);
        workbook.write(out);
        out.close();

    }

}
