package args;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
    assertNull(args.get("g"));
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

  Args(String schema, String args) {
    this.parser = createParser(schema);
    this.args = args;
  }

  private Parser createParser(String raw) {
    String schemaName = raw.substring(0, 1);
    String schemaType = raw.substring(1);
    return Parser.create(schemaName, schemaType);
  }

  Object get(String name) {
    return parser.get(name, args);
  }

}

abstract class Parser {

  private final String name;

  static Parser create(String name, String type) {
    switch (type) {
      case "#":
        return new NumberParser(name);
      case "*":
        return new StringParser(name);
      default:
        return new BooleanParser(name);
    }
  }

  Parser(String name) {
    this.name = name;
  }

  private String getName() {
    return name;
  }

  Object get(String name, String args) {
    if (getName().equals(name)) {
      return doGet(args);
    } else {
      return null;
    }
  }

  abstract Object doGet(String args);

  Object parseBoolean(String args) {
    return args.startsWith("-");
  }

}

class StringParser extends Parser {

  StringParser(String name) {
    super(name);
  }

  Object doGet(String args) {
    String[] tokens = args.split(" ");
    if (tokens[0].startsWith("-")) {
      return tokens[1];
    } else {
      return "";
    }
  }
}

class NumberParser extends Parser {

  NumberParser(String name) {
    super(name);
  }

  @Override
  Object doGet(String args) {
    String[] tokens = args.split(" ");
    if (tokens[0].startsWith("-")) {
      return Integer.parseInt(tokens[1]);
    } else {
      return -1;
    }
  }
}

class BooleanParser extends Parser {

  BooleanParser(String name) {
    super(name);
  }

  @Override
  Object doGet(String args) {
    return super.parseBoolean(args);
  }
}
