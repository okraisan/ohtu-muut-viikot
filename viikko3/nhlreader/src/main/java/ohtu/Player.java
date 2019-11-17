
package ohtu;

public class Player implements Comparable<Player> {
    private String name;
    private String team;
    private String nationality;
    private String birthdate;
    private int goals;
    private int assists;
    private int penalties;

    public void setName(String name) {
        this.name = name;
    }
    
    public void setNationality(String nationality) {
      this.nationality = nationality;
    }
    
    public String getName() {
      return name;
    }
    
    public String getNationality() {
      return nationality;
    }
    
    public int getGoals() {
      return goals;
    }
    
    public int getAssists() {
      return assists;
    }
    
    public int compareTo(Player o) {
        return (o.goals + o.assists) - (this.goals + this.assists);
    }
    
    @Override
    public String toString() {
        return String.format("%-19s (%s) %2d + %2d = %2d", name, team, goals, assists, goals + assists);
    }
      
}
