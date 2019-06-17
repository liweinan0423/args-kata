package args;


import java.util.HashMap;
import java.util.Map;

public class Schema {
    private Map<String, Flag> flagMap = new HashMap<>();

    public void addFlag(String flag, String type) {
        this.flagMap.put(flag, new Flag(flag, type));
    }

    public Flag getFlag(String flag) {
        return flagMap.get(flag);
    }
}
