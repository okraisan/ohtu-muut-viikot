package ohtuesimerkki;

import org.junit.*;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class StatisticsTest {
	
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void searchLöytääJossOnOlemassa() {
    	Player player = stats.search("Kurri");
        assertEquals("EDM", player.getTeam());
        player = stats.search("Häkkinen");
        assertEquals(null, player);
    }
    
    @Test
    public void palauttaaTiimin() {
    	List<Player> tiimi = stats.team("EDM");
    	assertEquals(3, tiimi.size());
    }

    @Test
    public void palauttaaHuiput() {
    	List<Player> huiput = stats.topScorers(3);
    	
    	assertEquals(3, huiput.size());
    	assertEquals(124, huiput.get(0).getPoints());
        
    }
}
