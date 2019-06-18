package examples.User;

import annotations.TypescriptClass;

@TypescriptClass
public class PhoneNumber {

    private short prefix;
    private long number;

    public short getPrefix() {
        return prefix;
    }

    public void setPrefix(short prefix) {
        this.prefix = prefix;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "+" + prefix + number;
    }
}
