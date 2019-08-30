package args;

public class Args {
    private String schema;
    private final String args;

    public Args(String schema, String args) {
        this.schema = schema;

        this.args = args;
    }

    public Object get(String name) {
        String schemaName = this.schema.substring(0, 1);
        String schemaType = this.schema.substring(1);
        if (schemaType.equals("")) {
            if (this.args.length() == 2) {
                return true;
            } else if ("".equals(this.args)) {
                return false;
            }
        } else if (schemaType.equals("#")) {
            String[] tokens = this.args.split(" ");
            if (tokens.length == 2) {
                return Integer.parseInt(tokens[1]);
            }
        }
        return null;
    }
}
