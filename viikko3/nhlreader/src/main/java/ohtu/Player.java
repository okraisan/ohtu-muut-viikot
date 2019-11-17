
package ohtu;

public class Player {
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
    
    
    public void setGoals(int goals) {
      this.goals = goals;
    }
    
    public void setAssists(int assists) {
      this.assists = assists;
    }
    
    public String getName() {
      return name;
    }
    
    public String getNationality() {
      return nationality;
    }
    
    @Override
    public String toString() {
        return String.format("%-19s (%s) goals %2d assists %2d", name, team, goals, assists);
    }
      
}
