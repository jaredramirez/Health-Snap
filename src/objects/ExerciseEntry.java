package objects;

import java.io.Serializable;

/**
 * @author Jared
 */
public class ExerciseEntry implements Serializable {

    int month, day, year;
    Double time;

    public ExerciseEntry(int day, int month, int year, Double time) {
        this.month = month;
        this.day = day;
        this.year = year;
        this.time = time;
    }

    public String getFullDate() {
        String s = month + "/" + day + "/" + year;
        return s;
    }

    public String getmonth() {
        String s = Integer.toString(month);
        return s;
    }

    public String getDay() {
        String s = Integer.toString(day);
        return s;
    }

    public String getYear() {
        String s = Integer.toString(year);
        return s;
    }

    public Double getTime() {
        return time;
    }

    public void setDate(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public void setTime(Double time) {
        this.time = time;
    }
}
