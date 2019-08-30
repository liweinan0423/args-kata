package args;

public class Args {
    private String schema;
    private final String args;

    public Args(String schema, String args) {
        this.schema = schema;

        this.args = args;
    }

    public Object get(String name) {
        if (name.equals(schema)) {
            if (this.args.length() == 2) {
                return true;
            } else if ("".equals(this.args)) {
                return false;
            }
        }
        return null;
    }
}
