package sample.Information;

import java.util.Date;

public class InfoAboutCompany {

    private String nameOrganization;
    private Integer numberDocument;
    private Date dateSost;
    private String okpo;
    private String okdp;
    private String typeOperation;

    public InfoAboutCompany(String nameOrganization, Integer numberDocument, Date dateSost, String okpo, String okdp, String typeOperation) {
        this.nameOrganization = nameOrganization;
        this.numberDocument = numberDocument;
        this.dateSost = dateSost;
        this.okpo = okpo;
        this.okdp = okdp;
        this.typeOperation = typeOperation;
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
