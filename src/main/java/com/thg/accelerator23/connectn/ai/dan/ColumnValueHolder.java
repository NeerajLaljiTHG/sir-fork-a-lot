package com.thg.accelerator23.connectn.ai.dan;

public class ColumnValueHolder {
    private int value;
    private int column;

    ColumnValueHolder(int value, int column) {
        this.value = value;
        this.column = column;
    }

    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

}
