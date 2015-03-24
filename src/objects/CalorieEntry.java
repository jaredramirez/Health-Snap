package objects;

import java.io.Serializable;

/**
 * @author Jared
 */
public class CalorieEntry implements Serializable {
    private int month, day, year, calories;

    public CalorieEntry(int day, int month, int year, int calories) {
        this.month = month;
        this.day = day;
        this.year = year;
        this.calories = calories;
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

    public int getCalories() {
        return calories;
    }

    public void setDate(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public void getCalories(int calories) {
        this.calories = calories;
    }

}
