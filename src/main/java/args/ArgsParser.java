package args;

import java.util.Arrays;
import java.util.List;

public class ArgsParser {
    public ArgsParser(Schema schema) {
    }

    public List<Arg> parse(String s) {
        if ("-l".equals(s)) {
            return Arrays.asList(new Arg(true));
        } else {
            return Arrays.asList(new Arg(false));

        }
    }
}
