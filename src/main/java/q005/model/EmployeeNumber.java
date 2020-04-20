package q005.model;

public class EmployeeNumber {
    private String number;

    public EmployeeNumber(String number) {
        this.number = number;
    }

    boolean equals(EmployeeNumber obj) {
        return number.equals(obj.number);
    }

    @Override
    public String toString() {
        return number;
    }
}
