package model;

import java.io.Serializable;

public class DateFormat implements Serializable {
 public int date, month, year;

    public DateFormat(String date) {
        String[] dt=date.split("[/-]");
        this.date = Integer.parseInt(dt[0]);
        this.month = Integer.parseInt(dt[1]);
        this.year = Integer.parseInt(dt[2]);
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return  date +
                "/" + month +
                "/" + year;
    }
}
