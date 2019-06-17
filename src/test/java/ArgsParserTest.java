import args.Arg;
import args.ArgsParser;
import args.Schema;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ArgsParserTest {
    @Test
    public void parse_boolean_arg() {
        Schema schema = new Schema();
        schema.addFlag("l", "boolean", false);
        List<Arg> parsedArgs = new ArgsParser(schema).parse("-l");
        assertEquals(true, parsedArgs.get(0).getValue());
    }

    @Test
    public void parse_implicit_boolean_flag() {
        Schema schema = new Schema();
        schema.addFlag("l", "boolean", false);
        List<Arg> parsedArgs = new ArgsParser(schema).parse("");
        assertEquals(false, parsedArgs.get(0).getValue());
    }
}
