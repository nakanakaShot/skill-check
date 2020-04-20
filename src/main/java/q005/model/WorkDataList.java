package q005.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class WorkDataList {

    private List<WorkData> list;

    public WorkDataList(List<WorkData> list) {
        this.list = list;
    }

    public List<WorkTimeSummary<Position>> summarizeByPosition() {
        List<WorkTimeSummary<Position>> summaries = new ArrayList<>();

        Arrays.stream(Position.values())
            .forEach(position -> {
                int workTimeSum = list.stream()
                    .filter(workData -> position == workData.getPosition())
                    .mapToInt(workData -> workData.getWorkTime().getAsSeconds()).sum();

                summaries.add(
                    new WorkTimeSummary<>(position, new WorkTime(workTimeSum))
                );
            });

        return summaries;
    }

    public List<WorkTimeSummary<PCode>> summarizeByPCode() {
        List<WorkTimeSummary<PCode>> summaries = new ArrayList<>();

        Set<PCode> pCodes = list.stream()
            .map(WorkData::getpCode)
            .sorted(Comparator.comparing(PCode::toString))
            .collect(Collectors.toCollection(LinkedHashSet::new));

        pCodes
            .forEach(pCode -> {
                int workTimeSum = list.stream()
                    .filter(workData -> pCode.equals(workData.getpCode()))
                    .mapToInt(workData -> workData.getWorkTime().getAsSeconds()).sum();

                summaries.add(
                    new WorkTimeSummary<>(pCode, new WorkTime(workTimeSum))
                );
            });

        return summaries;
    }

    public List<WorkTimeSummary<EmployeeNumber>> summarizeByEmployeeNumber() {
        List<WorkTimeSummary<EmployeeNumber>> summaries = new ArrayList<>();

        Set<EmployeeNumber> employeeNumbers = list.stream()
            .map(WorkData::getNumber)
            .sorted(Comparator.comparing(EmployeeNumber::toString))
            .collect(Collectors.toCollection(LinkedHashSet::new));

        employeeNumbers
            .forEach(employeeNumber -> {
                int workTimeSum = list.stream()
                    .filter(workData -> employeeNumber.equals(workData.getNumber()))
                    .mapToInt(workData -> workData.getWorkTime().getAsSeconds()).sum();

                summaries.add(
                    new WorkTimeSummary<>(employeeNumber, new WorkTime(workTimeSum))
                );
            });

        return summaries;
    }

}