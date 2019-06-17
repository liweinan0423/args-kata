package args;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Schema {
    private List<Flag> flags = new ArrayList<>();
    private Map<String, Flag> flagMap = new HashMap<>();

    public void addFlag(String flag, String type) {
        this.flags.add(new Flag(flag, type));
        this.flagMap.put(flag, new Flag(flag, type));
    }

    public Flag getFlag(String flag) {
        return flagMap.get(flag);
    }
}
