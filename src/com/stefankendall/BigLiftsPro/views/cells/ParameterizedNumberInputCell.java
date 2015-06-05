package com.stefankendall.BigLiftsPro.views.cells;

public abstract class ParameterizedNumberInputCell extends NumberInputCell {
    public String label;
    public String defaultValue;
    public String hint = "0";

    public ParameterizedNumberInputCell(String label, String defaultValue) {
        this.label = label;
        this.defaultValue = defaultValue;
    }

    public ParameterizedNumberInputCell(String label, String defaultValue, String hint) {
        this(label, defaultValue);
        this.hint = hint;
    }

    @Override
    protected void stringValueChanged(String s) {
    }

    @Override
    abstract protected int getLayoutResource();

    @Override
    protected String label() {
        return this.label;
    }

    @Override
    protected String defaultValue() {
        return this.defaultValue;
    }

    @Override
    protected String hint() {
        return this.hint;
    }
}
