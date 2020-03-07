package main;
import com.google.gson.*;
import java.util.*;

//Class Parser converts string containing Json to ArrayList<Team>
public  class Parser {
    public ArrayList<Team> parse(String value) {
        JsonStreamParser jsonStreamParser = new JsonStreamParser(value);
        JsonElement jsonElement = jsonStreamParser.next();
        JsonObject rootObject = jsonElement.getAsJsonObject();
        JsonObject innerObject = rootObject.get("api").getAsJsonObject();
        JsonArray jsonArray = innerObject.get("fixtures").getAsJsonArray();


        ArrayList<Team> teams = new ArrayList<>();
        for (JsonElement object : jsonArray) {
            //Find teamnames
            String  homeTeam = object.getAsJsonObject().get("homeTeam").getAsJsonObject().get("team_name").getAsString();
            String  guestTeam = object.getAsJsonObject().get("awayTeam").getAsJsonObject().get("team_name").getAsString();

            //Find counts of goals
            int homeGoals = object.getAsJsonObject().get("goalsHomeTeam").getAsInt();
            int guestGoals = object.getAsJsonObject().get("goalsAwayTeam").getAsInt();

            //Calculate count of points
            int homePoints = homeGoals > guestGoals ? 3 : 0;
            int guestPoints = homeGoals < guestGoals ? 3 : 0;

            if (guestGoals == homeGoals) {
                homePoints = 1;
                guestPoints = 1;
            }

            //Add results for hometeam
            Team home = teams.stream().filter(team -> homeTeam.equals(team.getName())).findAny().orElse(null);
            if (home == null) {
                teams.add(new Team(homeTeam, homeGoals, guestGoals, homePoints));
            } else {
                home.addGoals(homeGoals);
                home.addMissed(guestGoals);
                home.addPoints(homePoints);
            }

            //Add results for guestteam
            Team guest = teams.stream().filter(team -> guestTeam.equals(team.getName())).findAny().orElse(null);
            if (guest == null) {
                teams.add(new Team(guestTeam, guestGoals, homeGoals, guestPoints));
            } else {
                guest.addGoals(guestGoals);
                guest.addMissed(homeGoals);
                guest.addPoints(guestPoints);
            }
        }

        return teams;
    }
}
