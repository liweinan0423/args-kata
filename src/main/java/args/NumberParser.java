package args;

class NumberParser extends Parser {

  NumberParser(String name) {
    super(name);
  }

  @Override
  Object doGet(String args) {
    String[] tokens = args.split(" ");
    for (int i = 0; i < tokens.length; i++) {
      if (tokens[i].startsWith("-") && tokens[i].substring(1).equals(getName())) {
        return Integer.parseInt(tokens[i + 1]);
      }
    }
    return -1;
  }
}
