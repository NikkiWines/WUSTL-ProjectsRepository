package student.studio.mapreduce.apps.cards;

import static edu.wustl.cse231s.rice.classic.HabaneroClassic.launchHabaneroApp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import edu.wustl.cse231s.card.Card;
import edu.wustl.cse231s.card.Rank;
import edu.wustl.cse231s.card.Suit;
import edu.wustl.cse231s.mapreduce.MapReduceFramework;
import instructor.solution.mapreduce.InstructorUtils;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class TestCardMapReduceApplication {
	public static void main(String[] args) {
		
		List<Card>[] decks = new List[1_000];
		for( int deckIndex=0; deckIndex<decks.length; deckIndex ++ ) {
			decks[deckIndex] = new ArrayList<>(52);
			for( Suit suit : Suit.values() ) {
				for( Rank rank : Rank.values() ) {
					decks[deckIndex].add( new Card(suit, rank) );
				}
			}
			
			Collections.shuffle(decks[deckIndex]);
		}
		
		launchHabaneroApp(() -> {
			MapReduceFramework<List<Card>, Suit, Integer> mapReduceFramework = InstructorUtils.newMatrixMapReduceFramework();
			Map<Suit, Integer> mapSuitToCount = mapReduceFramework.mapReduceAll(decks, new CardMapper(), new NumericValueReducer());
			System.out.println(mapSuitToCount);
			
			if( mapSuitToCount.size() != 4 ) {
				throw new RuntimeException( mapSuitToCount.size() + " != 4" );
			}
			
			for( Entry<Suit,Integer> entry : mapSuitToCount.entrySet() ) {
				if( entry.getValue() != 54*decks.length ) {
					throw new RuntimeException( entry.toString() );
				}
			}
		});
	}
}
