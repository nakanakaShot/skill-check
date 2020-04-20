package q005.model;

public class PCode {
    private String code;

    public PCode(String code) {
        this.code = code;
    }

    boolean equals(PCode obj) {
        return code.equals(obj.code);
    }

    @Override
    public String toString() {
        return code;
    }
}
