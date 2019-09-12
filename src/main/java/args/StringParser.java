package args;

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
