package com.dzinevich.leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class AnalyzeUserWebsiteVisitPattern {

    public static class Event implements Comparable<Event>{
        String username;
        int timestamp;
        String website;

        public Event(String username, int timestamp, String website) {
            this.username = username;
            this.timestamp = timestamp;
            this.website = website;
        }

        @Override
        public int compareTo(Event o) {
            if (o.timestamp == this.timestamp) {
                return 0;
            }
            return o.timestamp < this.timestamp ? 1 : -1;
        }
    }

    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        // 1. create events list
        List<Event> unsortedEvents = new ArrayList<>();
        for(int i = 0; i< username.length; i++) {
            unsortedEvents.add(new Event(username[i], timestamp[i], website[i]));
        }

        // 2. group sites by users
        //
        // events are sorted by time!  see Event.compareTo
        // events transformed into a string value == a comma-separated list of sites
         //
        Map<String, String> usersWithSites = new HashMap<>();
        unsortedEvents.stream().collect(Collectors.groupingBy(ev -> ev.username))
                .forEach((us, evs) -> {
                    String sites = evs.stream()
                            .sorted() // by event time
                            .map(e -> e.website)
                            .collect(Collectors.joining(","));
                    usersWithSites.put(us, sites);
                });

        // 3. increment times for each site
        Map<String, Integer> sitesAndTimes = new HashMap<>();
        for(String us : usersWithSites.keySet()) {
            String sites = usersWithSites.get(us);
            sitesAndTimes.put(sites, 1+sitesAndTimes.getOrDefault(sites, 0));
        }

        // 4. maybe there're partial matches like "a->b->c" contains path "b->" , then we should increment "b->c" times count
        for(String sites : sitesAndTimes.keySet()) {

            long count = sitesAndTimes.keySet().stream().filter(k -> k.contains(sites)).count();
            while (count > 1) { // any other matches except for itself?
                sitesAndTimes.put(sites, 1 + sitesAndTimes.get(sites));
                count--;
            }
        }

        // 5. get the max times count
        int max = sitesAndTimes.values().stream().max(Comparator.naturalOrder()).get();//bigger number

        // 5.1. maybe there's more than one path with this times count
        List<String> resultStrings = new ArrayList<>(); // comma-separated strings
        sitesAndTimes.forEach((sites, times) -> {
            if (times == max) {
                resultStrings.add(sites);
            }
        });

        List<String> resultList = Arrays.stream(resultStrings.get(0).split(",")).collect(Collectors.toList()); // list of sites
        if (resultList.size() > 3) {
            resultList = resultList.stream()
                    .sorted() // lexicographically
                    .sorted(Comparator.comparing(String::length)) // by lenght
                    .limit(3) // only 3
                    .collect(Collectors.toList());
        }

        return resultList;
    }

    public static void main(String[] args) {
        AnalyzeUserWebsiteVisitPattern an = new AnalyzeUserWebsiteVisitPattern();

        String[] names = new String[]{"joe", "joe", "joe", "james", "james", "james", "james", "mary", "mary", "mary", "darya", "darya", "darya", "darya"};
        int[] times = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14};
        String[] sites = new String[]{"home","about","career","home","cart","maps","home","home","about","career", "maps","home","about","career"};

        System.out.println(an.mostVisitedPattern(names, times, sites));

        String[] names2 = new String[]{"dowg", "dowg", "dowg"};
        int[] times2 = new int[]{158931262,562600350,148438945};
        String[] sites2 = new String[]{"y","loedo","y"};

        System.out.println(an.mostVisitedPattern(names2, times2, sites2));

        String[] names3 = new String[]{"zkiikgv","zkiikgv","zkiikgv","zkiikgv"};
        int[] times3 = new int[]{436363475,710406388,386655081,797150921};
        String[] sites3 = new String[]{"wnaaxbfhxp","mryxsjc","oz","wlarkzzqht"};

        System.out.println(an.mostVisitedPattern(names3, times3, sites3));

        String[] names4 = new String[]{"ua","ua","ua","ub", "ub", "ub"};
        int[] times4 = new int[]{1,2,3,4,5,6};
        String[] sites4 = new String[]{"a","b","a","a","b","c"};

        System.out.println(an.mostVisitedPattern(names4, times4, sites4));
    }


}
