package cz.zcu.kiv.eitm.facecomparisonbe.model;

public class ResultItem {

    private String name;
    private String values;

    public ResultItem(String name, String values) {
        this.name = name;
        this.values = values;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
