package args;

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
