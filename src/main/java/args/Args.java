package args;

public class Args {
    public Args(String schema, String args) {

    }

    public Object get(String l) {
        if ("l".equals(l)) {
            return true;
        }
        return null;
    }
}
