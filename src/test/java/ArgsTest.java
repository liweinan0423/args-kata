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
}
