package com.kwk.perf;

import com.google.common.base.Joiner;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PerformanceMarker {
    private List<PerformanceEntry> entries = new ArrayList<PerformanceEntry>();
    private String groupName;

    public PerformanceMarker(String groupName) {
        this.groupName = groupName;
    }

    public void mark(String name) {
        PerformanceEntry entry = new PerformanceEntry(name, System.currentTimeMillis());
        entries.add(entry);
    }

    public String end(String name) {
        mark(name);
        return dump();
    }

    public String dump() {
        int size = entries.size();
        if (size < 2) {
            return "";
        }

        List<PerformanceRange> infoList = new ArrayList<PerformanceRange>(entries.size());
        for (int i = 1; i < size; ++i) {
            PerformanceRange range = PerformanceRange.instance(entries.get(i - 1), entries.get(i));
            infoList.add(range);
        }

        Collections.sort(infoList, new Comparator<PerformanceRange>() {
            @Override
            public int compare(PerformanceRange o1, PerformanceRange o2) {
                if (o1.getDurationTime() > o2.getDurationTime()) {
                    return -1;
                }

                if (o1.getDurationTime() == o2.getDurationTime()) {
                    return 0;
                }

                return 1;
            }
        });

        String ret = Joiner.on("\r\n").join(infoList);
        ret = "******" + groupName + "******\r\n" + ret;
        return ret;
    }
}

class PerformanceEntry {
    private String name;
    private long time;

    public PerformanceEntry(String name, long time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public long getTime() {
        return time;
    }
}

class PerformanceRange {
    private String startName;
    private String endName;
    private long durationTime;

    public static PerformanceRange instance(PerformanceEntry start, PerformanceEntry end) {
        PerformanceRange range = new PerformanceRange();
        range.startName = start.getName();
        range.endName = end.getName();
        range.durationTime = end.getTime() - start.getTime();
        return range;
    }

    public String toString() {
        String ret = MessageFormat.format("[{0}-{1}]:{2}", startName, endName, durationTime);
        return ret;
    }

    public long getDurationTime() {
        return durationTime;
    }
}
