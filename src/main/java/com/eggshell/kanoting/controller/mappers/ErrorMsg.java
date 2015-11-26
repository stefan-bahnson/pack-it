package com.eggshell.kanoting.controller.mappers;

/**
 * Write Javadoc...
 */
public class ErrorMsg {
    private String msg;

    public ErrorMsg() {}

    public ErrorMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ErrorMsg)) return false;

        ErrorMsg errorMsg = (ErrorMsg) o;

        return !(getMsg() != null ? !getMsg().equals(errorMsg.getMsg()) : errorMsg.getMsg() != null);

    }

    @Override
    public int hashCode() {
        return getMsg() != null ? getMsg().hashCode() : 0;
    }
}
