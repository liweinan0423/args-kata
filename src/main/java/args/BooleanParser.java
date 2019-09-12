package args;

class BooleanParser extends Parser {

  BooleanParser(String name) {
    super(name);
  }

  @Override
  protected Object doGetDefaultVal() {
    return false;
  }

  @Override
  Object doGet(String args) {
    return args.startsWith("-");
  }
}
