package cz.zcu.kiv.eitm.facecomparisonbe.model;

import java.util.List;

public class Result {

    private String fieldName;
    private boolean result;
    private List<ResultItem> options;

    public Result(String fieldName, boolean result) {
        this.fieldName = fieldName;
        this.result = result;
    }

    public Result(String fieldName, boolean result, List<ResultItem> options) {
        this.fieldName = fieldName;
        this.result = result;
        this.options = options;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public List<ResultItem> getOptions() {
        return options;
    }

    public void setOptions(List<ResultItem> options) {
        this.options = options;
    }
}
