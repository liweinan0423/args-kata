package args;

import java.util.Arrays;
import java.util.List;

public class ArgsParser {
    public ArgsParser(Schema schema) {
    }

    public List<Arg> parse(String s) {
        if ("-l".equals(s)) {
            return Arrays.asList(new Arg(true));
        } else if (s.startsWith("-p")) {
            return Arrays.asList(new Arg(Integer.valueOf(s.split(" ")[1])));
        } else {
            return Arrays.asList(new Arg(false));
        }
    }
}
