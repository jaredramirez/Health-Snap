package objects;

import java.io.Serializable;

/**
 * @author Jared
 */
public class WaterEntry implements Serializable {

    int month, day, year, fluidOZ;

    public WaterEntry(int day, int month, int year, int fluidOZ) {
        this.month = month;
        this.day = day;
        this.year = year;
        this.fluidOZ = fluidOZ;
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

    public int getFluidOZ() {
        return fluidOZ;
    }

    public void setDate(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public void setFluidOZ(int fluidOZ) {
        this.fluidOZ = fluidOZ;
    }

}
