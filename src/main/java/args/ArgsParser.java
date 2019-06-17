package args;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ArgsParser {
    private Schema schema;

    public ArgsParser(Schema schema) {
        this.schema = schema;
    }

    private List<Arg> parseArg(String args) {
        String flagName = args.split(" ")[0].substring(1);
        Flag flag = schema.getFlag(flagName);
        if (flag.getType().equals("boolean")) {
            return Collections.singletonList(new Arg(true));
        } else if (flag.getType().equals("integer")) {
            return Collections.singletonList(new Arg(Integer.valueOf(args.split(" ")[1])));
        }
        return Collections.singletonList(new Arg(args.split(" ")[1]));
    }

    public List<Arg> parse(String args) {
        if (args.length() > 0) {
            return parseArg(args);
        } else {
            return schema.defaultValues().stream().map(Arg::new).collect(toList());
        }
    }

}
