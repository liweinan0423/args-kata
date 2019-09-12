package args;

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
