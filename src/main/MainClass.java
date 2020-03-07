package main;

import java.util.*;

public class MainClass {
    public static void main(String[] args) {
        URL_API urlapi = new URL_API();
        Parser parser = new Parser();
        
        ArrayList<Team> teams = parser.parse(urlapi.getData("/fixtures/league/2"));
        teams.sort((t1, t2) -> t1.getPoints() == t2.getPoints() ?
                ((t2.getGoals() - t2.getMissed()) - (t1.getGoals() - t1.getMissed())) :
                t2.getPoints() - t1.getPoints());

        for (int i = 0; i < teams.size(); i++)
            teams.get(i).setPosition(i + 1);

        teams.forEach(t -> System.out.println(t.toString()));
    }
}
