package com.app.inptreservationux;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {

    public static HashMap<String , List<String>> getData() {

        HashMap<String, List<String>> expandableListDetail = new HashMap<>();

        List<String> cricket = new ArrayList<>();
        cricket.add("India");
        cricket.add("Pakistan");
        cricket.add("Australia");
        cricket.add("Viet Nam");
        cricket.add("South Africa");

        List<String> football = new ArrayList<>();
        football.add("Brazil");
        football.add("Spain");
        football.add("Germany");
        football.add("Netherlands");
        football.add("Italy");



        expandableListDetail.put("CRICKET TEAMS",cricket);
        expandableListDetail.put("FOOTBALL TEAMS",football);

        return expandableListDetail;
    }

}
