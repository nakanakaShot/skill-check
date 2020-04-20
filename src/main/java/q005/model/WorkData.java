package q005.model;

/**
 * 作業時間管理クラス 自由に修正してかまいません
 */
public class WorkData {

    /**
     * 社員番号
     */
    private final EmployeeNumber number;

    /**
     * 部署
     */
    private final Department department;

    /**
     * 役職
     */
    private final Position position;

    /**
     * Pコード
     */
    private final PCode pCode;

    /**
     * 作業時間(分)
     */
    private final WorkTime workTime;

    public WorkData(
        EmployeeNumber number,
        Department department,
        Position position,
        PCode pCode,
        WorkTime workTime
    ) {
        this.number = number;
        this.department = department;
        this.position = position;
        this.pCode = pCode;
        this.workTime = workTime;
    }

    EmployeeNumber getNumber() {
        return number;
    }

    Department getDepartment() {
        return department;
    }

    Position getPosition() {
        return position;
    }

    PCode getpCode() {
        return pCode;
    }

    WorkTime getWorkTime() {
        return workTime;
    }
}
