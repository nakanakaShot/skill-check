package q005.model;

public class Department {
    private String department;

    public Department(String department) {
        this.department = department;
    }

    boolean equals(Department obj) {
        return department.equals(obj.department);
    }

    @Override
    public String toString() {
        return department;
    }
}
