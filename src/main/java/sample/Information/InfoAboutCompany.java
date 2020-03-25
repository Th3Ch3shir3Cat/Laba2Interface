package sample.Information;

import java.util.Date;

public class InfoAboutCompany {

    private String nameOrganization;
    private Integer numberDocument;
    private Date dateSost;
    private String okpo;
    private String okdp;
    private String typeOperation;
    private String structurePodr;
    private String materialDolgnost;
    private String materialFIO;
    private String tableNum;

    public InfoAboutCompany(){

    }

    public InfoAboutCompany(String nameOrganization, Integer numberDocument, Date dateSost, String okpo, String okdp, String typeOperation, String structurePodr, String materialDolgnost, String materialFIO, String tableNum) {
        this.nameOrganization = nameOrganization;
        this.numberDocument = numberDocument;
        this.dateSost = dateSost;
        this.okpo = okpo;
        this.okdp = okdp;
        this.typeOperation = typeOperation;
        this.structurePodr = structurePodr;
        this.materialDolgnost = materialDolgnost;
        this.materialFIO = materialFIO;
        this.tableNum = tableNum;
    }

    public void setStructurePodr(String structurePodr) {
        this.structurePodr = structurePodr;
    }

    public void setMaterialDolgnost(String materialDolgnost) {
        this.materialDolgnost = materialDolgnost;
    }

    public void setMaterialFIO(String materialFIO) {
        this.materialFIO = materialFIO;
    }

    public void setTableNum(String tableNum) {
        this.tableNum = tableNum;
    }

    public String getStructurePodr() {
        return structurePodr;
    }

    public String getMaterialDolgnost() {
        return materialDolgnost;
    }

    public String getMaterialFIO() {
        return materialFIO;
    }

    public String getTableNum() {
        return tableNum;
    }

    public void setNameOrganization(String nameOrganization) {
        this.nameOrganization = nameOrganization;
    }

    public void setNumberDocument(Integer numberDocument) {
        this.numberDocument = numberDocument;
    }

    public void setDateSost(Date dateSost) {
        this.dateSost = dateSost;
    }

    public void setOkpo(String okpo) {
        this.okpo = okpo;
    }

    public void setOkdp(String okdp) {
        this.okdp = okdp;
    }

    public void setTypeOperation(String typeOperation) {
        this.typeOperation = typeOperation;
    }

    public String getNameOrganization() {
        return nameOrganization;
    }

    public Integer getNumberDocument() {
        return numberDocument;
    }

    public Date getDateSost() {
        return dateSost;
    }

    public String getOkpo() {
        return okpo;
    }

    public String getOkdp() {
        return okdp;
    }

    public String getTypeOperation() {
        return typeOperation;
    }
}
