package args;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class Schema {
    private Map<String, Flag> flagMap = new HashMap<>();

    public void addFlag(String flag, String type, Object defaultValue) {
        this.flagMap.put(flag, new Flag(flag, type, defaultValue));
    }

    public Flag getFlag(String flag) {
        return flagMap.get(flag);
    }

    List<Object> defaultValues() {
        return flagMap.values().stream().map(Flag::getDefaultValue).collect(toList());
    }
}
