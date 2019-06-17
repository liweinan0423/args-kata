package args;

import java.util.Arrays;
import java.util.List;

public class ArgsParser {
    private Schema schema;

    public ArgsParser(Schema schema) {
        this.schema = schema;
    }

    public List<Arg> parse(String s) {
        if ("-l".equals(s)) {
            return Arrays.asList(new Arg(true));
        } else if (s.startsWith("-p")) {
            return Arrays.asList(new Arg(Integer.valueOf(s.split(" ")[1])));
        } else {
            Flag integerFlag = schema.getFlag("p");
            if (integerFlag != null) {
                return Arrays.asList(new Arg(integerFlag.getDefaultValue()));
            } else {
                return Arrays.asList(new Arg(false));
            }
        }
    }
}
