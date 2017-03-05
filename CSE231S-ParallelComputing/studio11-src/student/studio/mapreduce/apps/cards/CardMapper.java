package student.studio.mapreduce.apps.cards;

import java.util.List;

import edu.wustl.cse231s.NotYetImplementedException;
import edu.wustl.cse231s.card.Card;
import edu.wustl.cse231s.card.Suit;
import edu.wustl.cse231s.mapreduce.MapContext;
import edu.wustl.cse231s.mapreduce.Mapper;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class CardMapper implements Mapper<List<Card>, Suit, Integer> {
	@Override
	public void map(MapContext<Suit, Integer> context, List<Card> cards) {
		for (Card c : cards )  {
			if (c.getRank().isNumeric()) { 
				context.emit(c.getSuit(), c.getRank().getNumericValue());
			}
		}
	}
}
