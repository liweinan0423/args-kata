package args;


import java.util.ArrayList;
import java.util.List;

public class Schema {
    private List<Flag> flags = new ArrayList<Flag>();

    public void addFlag(String flag, String type) {
        this.flags.add(new Flag(flag, type));
    }

    public Flag getFlag(String flag) {
        return flags.stream().filter(f -> f.getName().equals(flag)).findFirst().get();
    }
}
