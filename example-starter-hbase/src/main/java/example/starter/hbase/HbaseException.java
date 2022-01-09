package example.starter.hbase;

public class HbaseException extends RuntimeException {

    private static final long serialVersionUID = -7124995563744988219L;

    public HbaseException(String message, Throwable e) {
        super(message, e);
    }
}
