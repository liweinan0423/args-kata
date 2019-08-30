package args;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArgsTest {

    @Test
    public void should_parse_boolean_arg() {
        Args args = new Args("l", "-l");
        assertEquals(true, args.get("l"));
    }

//    @Test
//    public void should_parse_boolean_arg_default_value() {
//        Args args = new Args("l", "");
////        assertEquals(false, args.get("l"));
//    }
}
