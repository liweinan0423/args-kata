import args.Schema;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArgsTest {
    @Test
    public void boolean_flag() {
        Schema schema = new Schema();
        schema.addFlag("l", "boolean");
        assertEquals("boolean", schema.getFlag("l").getType());
    }

    @Test
    public void unknown_flag() {
        Schema schema = new Schema();
        schema.addFlag("l", "boolean");
        assertEquals(null, schema.getFlag("x"));
    }

    @Test
    public void string_flag() {
        Schema schema = new Schema();
        schema.addFlag("p", "string");
        assertEquals("string", schema.getFlag("p").getType());
    }
}
