package args;

public class Args {
    private final String args;

    public Args(String schema, String args) {

        this.args = args;
    }

    public Object get(String name) {
        if ("l".equals(name)) {
            if ("-l".equals(this.args)) {
                return true;
            } else if ("".equals(this.args)) {
                return false;
            }
        }
        return null;
    }
}
