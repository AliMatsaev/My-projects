package main;

public class Team {
    private String name;
    private int position;
    private int goals;
    private int missed;
    private int points;

    public Team(String name, int goals, int missed, int points) {
        this.name = name;
        this.goals = goals;
        this.missed = missed;
        this.points = points;
    }



    public String getName() {return this.name;}
    public int getGoals() {return this.goals;}
    public int getMissed() {return this.missed;}
    public int getPoints() {return this.points;}

    public void setName(String name) {this.name = name;}
    public void setGoals(int goals) {this.goals = goals;}
    public void setMissed(int missed) {this.missed = missed;}
    public void setPoints(int points) {this.points = points;}
    public void setPosition(int number) {
        this.position = number;
    }

    public void addGoals(int goals) {
        this.goals += goals;
    }
    public void addMissed(int missed) {
        this.missed += missed;
    }
    public void addPoints(int points) {
        this.points += points;
    }


    @Override
    public String toString() {
        return (position + ". name: " + name + ", points: " + points + ", scored goals: " + goals + ", missed goals: " + missed);
    }
}
