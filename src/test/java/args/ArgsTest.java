package args;

import org.junit.Test;

import java.util.Map;

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
    public void should_be_null_when_get_undefined_arg() {
        Args args = new Args("l", "-l");
        assertEquals(null, args.get("g"));
    }

    @Test
    public void should_parse_more_boolean_args() {
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

    @Test
    public void should_parse_another_number_arg() {
        Args args = new Args("g#", "-g 80");
        assertEquals(80, args.get("g"));
    }

    @Test
    public void should_parse_string_arg() {
        Args args = new Args("d*", "-d /var/logs/");
        assertEquals("/var/logs/", args.get("d"));
    }

    @Test
    public void should_parse_string_arg_defautl_value() {
        Args args = new Args("d*", "");
        assertEquals("", args.get("d"));
    }

    @Test
    public void should_parse_more_string_arg() {
        Args args = new Args("s*", "-s abc");
        assertEquals("abc", args.get("s"));
    }
}

class Args {
    private final Schema schema2;
    private String schema;
    private final String args;

    public Args(String schema, String args) {
        this.schema = schema;
        this.schema2 = parseSchema(schema);

        this.args = args;
    }

    private Schema parseSchema(String raw) {
        String schemaName = raw.substring(0, 1);
        String schemaType = raw.substring(1);
        return new Schema(schemaName, schemaType);
    }

    public Object get(String name) {
        String schemaType = this.schema2.getType();
        if (this.schema2.getName().equals(name)) {
            switch (schemaType) {
                case "":
                    if (this.args.length() == 2) {
                        return true;
                    } else if ("".equals(this.args)) {
                        return false;
                    }
                    break;
                case "#": {
                    String[] tokens = this.args.split(" ");
                    if (tokens.length == 2) {
                        return Integer.parseInt(tokens[1]);
                    } else if (tokens.length == 1) {
                        return -1;
                    }
                    break;
                }
                case "*": {
                    String[] tokens = this.args.split(" ");
                    if (tokens.length == 2) {
                        return tokens[1];
                    } else if (tokens[0].equals("")) {
                        return "";
                    }
                    break;
                }
            }
        } else {
            return null;
        }
        throw new RuntimeException();
    }
}

class Schema {

    private final String name;
    private final String type;

    public Schema(String name, String type) {

        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
