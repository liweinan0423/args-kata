package args;

class StringParser extends Parser {

  StringParser(String name) {
    super(name);
  }

  Object doGet(String args) {
    String[] tokens = args.split(" ");
    for (int i = 0; i < tokens.length; i++) {
      if (tokens[i].startsWith("-") && tokens[i].substring(1).equals(getName())) {
        return tokens[i + 1];
      }
    }
    return "";
  }
}
