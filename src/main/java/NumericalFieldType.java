public enum NumericalFieldType {

    INT("int"),
    SHORT("short"),
    INTEGER("integer"),
    DOUBLE("double"),
    LONG("long"),
    FLOAT("float");

    private String name;

    NumericalFieldType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
