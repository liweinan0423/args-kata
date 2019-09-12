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
  public void should_parse_string_arg_default_value() {
    Args args = new Args("d*", "");
    assertEquals("", args.get("d"));
  }

  @Test
  public void should_parse_more_string_arg() {
    Args args = new Args("s*", "-s abc");
    assertEquals("abc", args.get("s"));
  }

  @Test
  public void should_parse_number_and_string_args() {
    Args args = new Args("p#,d*", "-p 8080 -d /var/logs/");
    assertEquals(8080, args.get("p"));
    assertEquals("/var/logs/", args.get("d"));
  }

  @Test
  public void should_parse_string_and_number_args() {
    Args args = new Args("p#,d*", "-d /var/logs/ -p 8080 ");
    assertEquals(8080, args.get("p"));
    assertEquals("/var/logs/", args.get("d"));
  }

  @Test
  public void should_parse_number_and_default_string_args() {
    Args args = new Args("p#,d*", "-p 8080");
    assertEquals(8080, args.get("p"));
    assertEquals("", args.get("d"));
  }
}
