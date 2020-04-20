package q005;

import q005.model.Department;
import q005.model.EmployeeNumber;
import q005.model.PCode;
import q005.model.Position;
import q005.model.WorkData;
import q005.model.WorkTime;

class WorkDataFactory {

    static WorkData create(
        String number,
        String department,
        String position,
        String pCode,
        String workTime
    ) {
        try{
            Integer.parseInt(workTime);
        } catch (NumberFormatException e) {
            throw new RuntimeException();
        }

        return new WorkData(
            new EmployeeNumber(number),
            new Department(department),
            Position.from(position),
            new PCode(pCode),
            new WorkTime(Integer.parseInt(workTime))
        );
    }

}
