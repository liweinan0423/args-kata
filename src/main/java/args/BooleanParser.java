package args;

class BooleanParser extends Parser {

  BooleanParser(String name) {
    super(name);
  }

  @Override
  Object doGet(String args) {
    return super.parseBoolean(args);
  }
}
