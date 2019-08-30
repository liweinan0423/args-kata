package args;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArgsTest {

    @Test
    public void should_parse_boolean_arg() {
        Args args = new Args("l", "-l");
        assertEquals(true, args.get("l"));
    }

    @Test
    public void should_parse_boolean_arg_default_value() {
        Args args = new Args("l", "");
        assertEquals(false, args.get("l"));
    }

    @Test
    public void should_paerse_more_boolean_args() {
        Args args = new Args("g", "-g");
        assertEquals(true, args.get("g"));
    }


    @Test
    public void should_parse_number_arg() {
        Args args = new Args("p#", "-p 8080");
        assertEquals(8080, args.get("p"));
    }

    @Test
    public void should_parse_number_arg_default_value() {
        Args args = new Args("p#", "");
        assertEquals(-1, args.get("p"));
    }
}
