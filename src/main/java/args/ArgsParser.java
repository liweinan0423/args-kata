package args;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ArgsParser {
    private Schema schema;

    public ArgsParser(Schema schema) {
        this.schema = schema;
    }

    private List<Arg> parse2(String args) {
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

    public List<Arg> parse(String args) {
        if (args.length() > 0) {
            return parse2(args);
        } else {
            return defaultValue();
        }
    }

    private List<Arg> defaultValue() {
        return schema.flags().stream().map(f -> f.getDefaultValue()).map(Arg::new).collect(toList());
    }

}
