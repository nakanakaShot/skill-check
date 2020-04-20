package q005.model;

public class WorkTime {

    private static final int HOUR_AS_SECONDS = 3600;
    private static final int MINUTE_AS_SECONDS = 60;

    private int workTime;

    public WorkTime(int workTime) {
        this.workTime = workTime;
    }

    int getAsSeconds(){
        return this.workTime;
    }

    String getFormattedWorkTime(){
        int hours = this.workTime / HOUR_AS_SECONDS;
        int minutes = this.workTime % MINUTE_AS_SECONDS;
        return String.format("%02d時間%02d分", hours, minutes);
    }
}
