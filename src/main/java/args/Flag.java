package args;

public class Flag {
    private final String flag;
    private final String type;
    private Object defaultValue;


    public Flag(String flag, String type, Object defaultValue) {

        this.flag = flag;
        this.type = type;
        this.defaultValue = defaultValue;
    }

    public String getType() {
        return type;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }
}
