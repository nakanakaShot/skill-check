package q005.model;

public class WorkTimeSummary <T> {
    private T key;
    private WorkTime workTime;

    WorkTimeSummary(T key, WorkTime workTime) {
        this.key = key;
        this.workTime = workTime;
    }

    public String formatSummary(){
        return key + ": " + workTime.getFormattedWorkTime();
    }
}
