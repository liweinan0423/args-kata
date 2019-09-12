package args;

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

  public String getName() {
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
