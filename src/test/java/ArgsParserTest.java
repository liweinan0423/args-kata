import args.ArgsParser;
import args.Schema;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArgsParserTest {
    @Test
    public void parse_boolean_arg() {
        Schema schema = new Schema();
        schema.addFlag("l", "boolean", false);
        assertEquals(true, new ArgsParser(schema).parse("-l"));
    }
}
