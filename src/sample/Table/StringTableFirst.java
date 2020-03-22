package sample.Table;

import java.util.Date;

public class StringTableFirst {
    private Integer id;
    private String Name;            //Наименование
    private Integer SumFactRealize;  //Сумма фактической реализации
    private Date DocumentDate;      //Дата документа
    private Long DocumentNumber;    //Номер документа
    private Integer SumUchot;        //Стоимость по учетным ценам
    private Integer Product;         //Продукты
    private Integer SpeciiAndSolt;   //Специи и соль
    private Integer Tara;            //Тара
    private Integer Steklotara;      //Стеклотара
    private String BuhOtmetki;      //Отметки бухгалтерии

    public StringTableFirst(Integer id, String name, Integer sumFactRealize,
                            Date documentDate, Long documentNumber, Integer sumUchot, Integer product,
                            Integer speciiAndSolt, Integer tara, Integer steklotara, String buhOtmetki) {
        this.id = id;
        Name = name;
        SumFactRealize = sumFactRealize;
        DocumentDate = documentDate;
        DocumentNumber = documentNumber;
        SumUchot = sumUchot;
        Product = product;
        SpeciiAndSolt = speciiAndSolt;
        Tara = tara;
        Steklotara = steklotara;
        BuhOtmetki = buhOtmetki;
    }

    public StringTableFirst(Integer id, String name){
        this.id = id;
        this.Name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setSumFactRealize(Integer sumFactRealize) {
        SumFactRealize = sumFactRealize;
    }

    public void setDocumentDate(Date documentDate) {
        DocumentDate = documentDate;
    }

    public void setDocumentNumber(Long documentNumber) {
        DocumentNumber = documentNumber;
    }

    public void setSumUchot(Integer sumUchot) {
        SumUchot = sumUchot;
    }

    public void setProduct(Integer product) {
        Product = product;
    }

    public void setSpeciiAndSolt(Integer speciiAndSolt) {
        SpeciiAndSolt = speciiAndSolt;
    }

    public void setTara(Integer tara) {
        Tara = tara;
    }

    public void setSteklotara(Integer steklotara) {
        Steklotara = steklotara;
    }

    public void setBuhOtmetki(String buhOtmetki) {
        BuhOtmetki = buhOtmetki;
    }

    public Integer getId() {
        return id;
    }
    
    public Integer getSumFactRealize() {
        return SumFactRealize;
    }

    public Date getDocumentDate() {
        return DocumentDate;
    }

    public Long getDocumentNumber() {
        return DocumentNumber;
    }

    public Integer getSumUchot() {
        return SumUchot;
    }

    public Integer getProduct() {
        return Product;
    }

    public Integer getSpeciiAndSolt() {
        return SpeciiAndSolt;
    }

    public Integer getTara() {
        return Tara;
    }

    public Integer getSteklotara() {
        return Steklotara;
    }

    public String getBuhOtmetki() {
        return BuhOtmetki;
    }
}
