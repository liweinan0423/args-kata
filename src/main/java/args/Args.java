package args;

public class Args {
    public Args(String schema, String args) {

    }

    public Object get(String name) {
        if ("l".equals(name)) {
            return true;
        }
        return null;
    }
}
