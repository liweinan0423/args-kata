package args;

public class Flag {
    private final String flag;
    private final String type;

    public Flag(String flag, String type) {

        this.flag = flag;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return flag;
    }

    public boolean getDefaultValue() {
        return false;
    }
}
