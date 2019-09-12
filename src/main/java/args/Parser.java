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

  Object get(String args) {
    String[] tokens = args.split(" ");
    for (int i = 0; i < tokens.length; i++) {
      if (tokens[i].startsWith("-") && tokens[i].substring(1).equals(getName())) {
        return doGet(args);
      }
    }
    return doGetDefaultVal();
  }

  protected abstract Object doGetDefaultVal();

  abstract Object doGet(String args);

}
