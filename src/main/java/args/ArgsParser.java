package args;

import java.util.Arrays;
import java.util.List;

public class ArgsParser {
    private Schema schema;

    public ArgsParser(Schema schema) {
        this.schema = schema;
    }

    public List<Arg> parse(String args) {
        if ("-l".equals(args)) {
            return Arrays.asList(new Arg(true));
        } else if (args.startsWith("-p")) {
            return Arrays.asList(new Arg(Integer.valueOf(args.split(" ")[1])));
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
