package com.messedup.messeduptry;

import android.icu.text.DateFormatSymbols;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by tanmaysinghal98 on 18/08/17.
 */

public class TabsPagerAdapter extends FragmentPagerAdapter {
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new day0();
            case 1:
                return new day1();
            case 2:
                return new day2();
            case 3:
                return new day3();
            case 4:
                return new day4();
            case 5:
                return new day5();
            case 6:
                return new day6();
        }

        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public CharSequence getPageTitle(int position) {
        Calendar cal = Calendar.getInstance();
        switch (position) {
            case 0:
                int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
                String weekday = new DateFormatSymbols().getShortWeekdays()[dayOfWeek];
                int date = cal.get(Calendar.DATE);
                return weekday + "   " + date;
            case 1:
                cal.add(Calendar.DATE, 1);
                int dayOfWeek1 = cal.get(Calendar.DAY_OF_WEEK);
                String weekday1 = new DateFormatSymbols().getShortWeekdays()[dayOfWeek1];
                int date1 = cal.get(Calendar.DATE);
                return weekday1 + "   " + date1;
            case 2:
                cal.add(Calendar.DATE, 2);
                int dayOfWeek2 = cal.get(Calendar.DAY_OF_WEEK);
                String weekday2 = new DateFormatSymbols().getShortWeekdays()[dayOfWeek2];
                int date2 = cal.get(Calendar.DATE);
                return weekday2 + "   " + date2;
            case 3:
                cal.add(Calendar.DATE, 3);
                int dayOfWeek3 = cal.get(Calendar.DAY_OF_WEEK);
                String weekday3 = new DateFormatSymbols().getShortWeekdays()[dayOfWeek3];
                int date3 = cal.get(Calendar.DATE);
                return weekday3 + "   " + date3;
            case 4:
                cal.add(Calendar.DATE, 4);
                int dayOfWeek4 = cal.get(Calendar.DAY_OF_WEEK);
                String weekday4 = new DateFormatSymbols().getShortWeekdays()[dayOfWeek4];
                int date4 = cal.get(Calendar.DATE);
                return weekday4 + "   " + date4;
            case 5:
                cal.add(Calendar.DATE, 5);
                int dayOfWeek5 = cal.get(Calendar.DAY_OF_WEEK);
                String weekday5 = new DateFormatSymbols().getShortWeekdays()[dayOfWeek5];
                int date5 = cal.get(Calendar.DATE);
                return weekday5 + "   " + date5;
            case 6:
                cal.add(Calendar.DATE, 6);
                int dayOfWeek6 = cal.get(Calendar.DAY_OF_WEEK);
                String weekday6 = new DateFormatSymbols().getShortWeekdays()[dayOfWeek6];
                int date6 = cal.get(Calendar.DATE);
                return weekday6 + "   " + date6;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 7;
    }
}
