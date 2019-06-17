package args;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ArgsParser {
    private Schema schema;

    public ArgsParser(Schema schema) {
        this.schema = schema;
    }

    private List<Arg> parse2(String args) {
        String flagName = args.split(" ")[0].substring(1);
        Flag flag = schema.getFlag(flagName);
        if (flag.getType().equals("boolean")) {
            return Collections.singletonList(new Arg(true));
        } else if (flag.getType().equals("integer")) {
            return Collections.singletonList(new Arg(Integer.valueOf(args.split(" ")[1])));
        } else {
            return Collections.singletonList(new Arg(args.split(" ")[1]));
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
        return schema.flags().stream().map(Flag::getDefaultValue).map(Arg::new).collect(toList());
    }

}
