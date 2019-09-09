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
  private final Schema schema;
  private final String args;

  public Args(String schema, String args) {
    this.schema = parseSchema(schema);
    this.args = args;
  }

  private Schema parseSchema(String raw) {
    String schemaName = raw.substring(0, 1);
    String schemaType = raw.substring(1);
    return new Schema(schemaName, schemaType);
  }

  public Object get(String name) {
    if (this.schema.getName().equals(name)) {
      return this.schema.parseArgs(args);
    } else {
      return null;
    }
  }

  private Object parseNumber() {
    String[] tokens = this.args.split(" ");
    if (tokens[0].startsWith("-")) {
      return Integer.parseInt(tokens[1]);
    } else {
      return -1;
    }
  }

  private Object parseBoolean() {
    if (this.args.startsWith("-")) {
      return true;
    } else {
      return false;
    }
  }

  private Object parseString() {
    String[] tokens = this.args.split(" ");
    if (tokens[0].startsWith("-")) {
      return tokens[1];
    } else {
      return "";
    }
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

  Object parseArgs(String args) {
    switch (this.getType()) {
      case "":
        return parseBoolean(args);
      case "#": {
        return parseNumber(args);
      }
      case "*": {
        return parseString(args);
      }
      default:
        return null;
    }
  }

  private Object parseNumber(String args) {
    String[] tokens = args.split(" ");
    if (tokens[0].startsWith("-")) {
      return Integer.parseInt(tokens[1]);
    } else {
      return -1;
    }
  }

  private Object parseBoolean(String args) {
    if (args.startsWith("-")) {
      return true;
    } else {
      return false;
    }
  }

  private Object parseString(String args) {
    String[] tokens =args.split(" ");
    if (tokens[0].startsWith("-")) {
      return tokens[1];
    } else {
      return "";
    }
  }

}
