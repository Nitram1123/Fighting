package team.fighter.model;

import java.util.ArrayList;

public class Game {
	// Conteneur des messages de l'historique de partie et des messages de chat
	private Messages messages;
	// Le jeu de cartes
	private DeckOfCards cards;
	private ArrayList<Fighter> fighters;
	// Jeton permettant de savoir à qui est le tour
	private int token;
	private int turn;

	public Game(ArrayList<Fighter> fighters) {
		this.fighters = fighters;
		messages = new Messages();
		for(Fighter fighter : fighters) {
			Message msg = new Message();
			msg.setPseudo(fighter.getName());
			msg.setBody("est entré dans la salle de jeu.");
			messages.add(msg);
		}
		init();
	}
	
	synchronized public void init() {
		token = turn = 0;
		cards = new DeckOfCards();
		messages.add(new Message(fighters.get(token).getName(),
				"mélange et distribut les cartes."));
		cards.shuffle();
		for(Fighter fighter : fighters) {
			fighter.init();
		}
		// Distribution de 6 cartes pour chaque joueurs
		for (int i=0; i<6; i++) {
			for(Fighter fighter : fighters) {
				fighter.takeCard(cards.pull());
			}
		}
		incrementToken();
		messages.add(new Message(fighters.get(token).getName(), " pioche une carte."));
		fighters.get(token).takeCard(cards.pull());
	}

	synchronized public boolean play(Fighter fighter, int id_card) {
		// Un joueur essait de jouer une carte
		if (fighter != fighters.get(token)) {
			// Si ce n'est pas son tour
			return false;
		}
		Card card = null;
		if ((card = fighter.getCard(id_card)) == null) {
			// Si la carte n'existe pas dans le jeu du joueur
			return false;
		}
		incrementToken();
		Fighter opponent = fighters.get(token);
		fighter.play(card, opponent, messages, this.cards);
		if(opponent.isDeath()) {
			messages.add(new Message(opponent.getName(), "est mort."));
			messages.add(new Message(fighter.getName(), "est le grand vainqueur."));
			// Il faudrait rediriger les joueur sur l'accueil et détruire les objet en mémoire
			return true;
		}
		opponent.takeCard(cards.pull());
		messages.add(new Message(opponent.getName(), " pioche une carte."));
		return true;
	}
	
	private void incrementToken() {
		if(++token == fighters.size()) {
			token = 0;
			turn++;
		}
	}
	
	public Messages getMessages() {
		return messages;
	}

	public void setMessages(Messages messages) {
		this.messages = messages;
	}
}