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
  private final Parser parser;
  private final String args;

  public Args(String schema, String args) {
    this.parser = createParser(schema);
    this.args = args;
  }

  private Parser createParser(String raw) {
    String schemaName = raw.substring(0, 1);
    String schemaType = raw.substring(1);
    return Parser.create(schemaName, schemaType);
  }

  public Object get(String name) {
    return parser.get(name, args);
  }

}

class Parser {

  private final String name;
  private final String type;

  static Parser create(String name, String type) {
    switch (type) {
      case "#":
        return new NumberParser(name, type);
      case "*":
        return new StringParser(name, type);
      default:
        return new BooleanParser(name, type);
    }
  }

  public Parser(String name, String type) {

    this.name = name;
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  Object get(String name, String args) {
    if (getName().equals(name)) {
      switch (this.getType()) {
        case "":
          return parseBoolean(args);
        default:
          return null;
      }
    } else {
      return null;
    }
  }

  private Object parseBoolean(String args) {
    if (args.startsWith("-")) {
      return true;
    } else {
      return false;
    }
  }

  Object parseString(String args) {
    String[] tokens = args.split(" ");
    if (tokens[0].startsWith("-")) {
      return tokens[1];
    } else {
      return "";
    }
  }
}

class StringParser extends Parser {

  public StringParser(String name, String type) {
    super(name, type);
  }

  @Override
  Object get(String name, String args) {
    return super.parseString(args);
  }
}

class NumberParser extends Parser {

  public NumberParser(String name, String type) {
    super(name, type);
  }

  @Override
  Object get(String name, String args) {
    String[] tokens = args.split(" ");
    if (tokens[0].startsWith("-")) {
      return Integer.parseInt(tokens[1]);
    } else {
      return -1;
    }
  }
}

class BooleanParser extends Parser {

  public BooleanParser(String name, String type) {
    super(name, type);
  }
}
