package it.unibo.nestedenum;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    private static final int LONG_MONTH = 31;
    private static final int USUAL_MONTH = 30;
    private static final int SHORT_MONTH = 28;

    public enum Month {
        JANUARY("january", LONG_MONTH),
        FEBRUARY("february", SHORT_MONTH),
        MARCH("march", LONG_MONTH),
        APRIL("april", USUAL_MONTH),
        MAY("may", LONG_MONTH),
        JUNE("june", USUAL_MONTH),
        JULY("july", LONG_MONTH),
        AUGUST("august", LONG_MONTH),
        SEPTEMBER("september", USUAL_MONTH),
        OCTOBER("october", LONG_MONTH),
        NOVEMBER("november", USUAL_MONTH),
        DECEMBER("december", LONG_MONTH);

        
        private int days;
        private String name;

        Month(String name, int days) {
            this.days = days;
            this.name = name;
        }

        static Month fromString(String name) {
            List<Month> matches = new ArrayList<>();

            for (Month i : Month.values()) {
                if ( i.name.startsWith(name.toLowerCase())) {
                    matches.add(i);
                }
            }

            if(matches.isEmpty() || matches.size() > 1){
                throw new IllegalArgumentException();
            }

            return matches.get(0);
        }
    };
        
    @Override
    public Comparator<String> sortByDays() {
        return new SortByDays();
    }

    @Override
    public Comparator<String> sortByOrder() {
        return  new SortByMonthOrder();
    }

    private static class SortByDays implements Comparator<String>{
        @Override
        public int compare(String o1, String o2) { 
            return Month.fromString(o1).days - Month.fromString(o2).days;  
        }
    }

    private static class SortByMonthOrder implements Comparator<String>{
        @Override
        public int compare(String o1, String o2) {
            return Month.fromString(o1).ordinal() - Month.fromString(o2).ordinal();
        }
            
    }
}

