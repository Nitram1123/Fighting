package team.fighter.model;

import java.util.ArrayList;
import java.util.Collections;
public class DeckOfCards extends Deck {
	
	private int id_cards;
	private ArrayList<Card> discards;
	public DeckOfCards () {
		super();
		discards = new ArrayList<Card>();
		init();
	}
	
	
	private void newCard(String description, int vitality, int mana, TypeCard type) {
    	Card c = new Card();
		c.setId(id_cards++);
    	c.setDescription(description);
		c.setVitality(vitality);
		c.setMana(mana);
    	c.type = type;
    	cards.add(c);
    }
	
	private void init() {
		cards = new ArrayList<Card>();
		discards = new ArrayList<Card>();
		id_cards = 0;
		newCard("Un couteau rouillé", -4, 0, TypeCard.weapon);
		newCard("Un poignard courbé", -6, 0, TypeCard.weapon);
		newCard("Une dague affilée", -8, 0, TypeCard.weapon);
		newCard("Une épée noire", -10, 0, TypeCard.weapon);
		newCard("Une épée d’argent", -12, 0, TypeCard.weapon);
		newCard("Un sabre tranchant", -14, 0, TypeCard.weapon);
		newCard("Une hache incrustée de pierres précieuses", -16, 0, TypeCard.weapon);
		newCard("Une double hache tranchante vénéneuse de la mort", -20, 0, TypeCard.weapon);
		
		for(int i=0; i<3; i++) {
			newCard("Une boule de terre", -4, -2, TypeCard.mana);
			newCard("Une boule de glace", -8, -4, TypeCard.mana);
			newCard("Une boule de feu", -12, -6, TypeCard.mana);
			newCard("Une boule d’ombre qui aspire l’énergie vitale", -16, -8, TypeCard.mana);
			newCard("Une flèche de lumière aveuglante", -20, -10, TypeCard.mana);
			newCard("Un éclair foudroyant", -24, -12, TypeCard.mana);
			newCard("Un cyclone glacial", -28, -14, TypeCard.mana);
			newCard("Une énorme vague de lave", -32, -16, TypeCard.mana);
		}
		
		for(int i=0; i<4; i++) {
			newCard("Une potion jaune", 5, 0, TypeCard.potion);
			newCard("Une potion rouge", 10, 0, TypeCard.potion);
			newCard("Une potion transparente", 0, 10, TypeCard.potion);
			newCard("Une potion noire", 0, 20, TypeCard.potion);
		}
		
		for(int i=0; i<20; i++) {
			newCard("Attaque", 0, 0, TypeCard.attack);
		}
	}
	
	public void push(Card card) {
		discards.add(card);
	}
	
	public Card pull() {
		if (cards.size() == 0) {
			cards.addAll(discards);
			discards = new ArrayList<Card>();
			this.shuffle();
		}
		Card card = cards.get(cards.size()-1);
		cards.remove(cards.size()-1);
		return card;
	}
	
	public void shuffle() {
		Collections.shuffle(cards);
	}
}