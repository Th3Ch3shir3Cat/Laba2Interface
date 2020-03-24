package sample.Table;

public class StringTableWorkers {

    private Integer id;
    private String fio;

    public StringTableWorkers(Integer id, String fio) {
        this.id = id;
        this.fio = fio;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Integer getId() {
        return id;
    }

    public String getFio() {
        return fio;
    }
}
