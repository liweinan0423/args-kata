import args.Schema;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArgsTest {
    @Test
    public void boolean_flag() {
        Schema schema = new Schema();
        schema.addFlag("l", "boolean", false);
        assertEquals("boolean", schema.getFlag("l").getType());
    }

    @Test
    public void unknown_flag() {
        Schema schema = new Schema();
        schema.addFlag("l", "boolean", false);
        assertEquals(null, schema.getFlag("x"));
    }

    @Test
    public void string_flag() {
        Schema schema = new Schema();
        schema.addFlag("p", "string", "");
        assertEquals("string", schema.getFlag("p").getType());
    }

    @Test
    public void boolean_default_value() {
        Schema schema = new Schema();
        schema.addFlag("l", "boolean", false);
        assertEquals(false, schema.getFlag("l").getDefaultValue());
    }

    @Test
    public void integer_flag() {
        Schema schema = new Schema();
        schema.addFlag("p", "integer", 0);
        assertEquals("integer", schema.getFlag("p").getType());
    }

    @Test
    public void integer_flag_default_value() {
        Schema schema = new Schema();
        schema.addFlag("p", "integer", 0);
        assertEquals(0, schema.getFlag("p").getDefaultValue());
    }
}
