package args;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Schema {
    private Map<String, Flag> flagMap = new HashMap<>();

    public void addFlag(String flag, String type, Object defaultValue) {
        this.flagMap.put(flag, new Flag(flag, type, defaultValue));
    }

    public Flag getFlag(String flag) {
        return flagMap.get(flag);
    }

    public Collection<Flag> flags() {
        return flagMap.values();
    }
}
