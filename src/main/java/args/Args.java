package args;

import java.util.HashMap;
import java.util.Map;

class Args {
  private final Parser parser;
  private final String args;
  private final Map<String, Parser> parsers = new HashMap<>();

  Args(String schema, String args) {
    String[] tokens = schema.split(",");
    for (String token : tokens) {
      Parser parser = createParser(token);
      this.parsers.put(parser.getName(), parser);
    }
    this.parser = createParser(schema);
    this.args = args;
  }

  private Parser createParser(String raw) {
    String schemaName = raw.substring(0, 1);
    String schemaType = raw.substring(1);
    return Parser.create(schemaName, schemaType);
  }

  Object get(String name) {
    Parser parser = parsers.get(name);
    if (parser == null) {
      return null;
    }
    return parser.get(name, args);
  }

}
