package team.fighter.model;

public class Fighter {
	private String name;
	private String description;
	private int vitality;
	private int mana;
	private Card weapon;
	private Deck cards;
	private Deck synchronizeCards;
	public Fighter() { super(); }
	public Fighter(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
	public void init() {
		weapon = new Card("Un couteau à beur", -4, 0, TypeCard.weapon);
		vitality = 100;
		mana = 50;
		cards = new Deck();
		synchronizeCards = new Deck();
	}
	
	public void play(Card card, Fighter opponent, Messages messages, DeckOfCards cards) {
		Message msg = new Message();
		Message score = null;
		msg.setPseudo(this.name);
		switch (card.type) {
		case weapon:
			this.weapon = card;
			msg.setBody("s'arme avec "+weapon.getDescription()+".");
			messages.add(msg);
			return;
		case mana:
			msg.setBody("lance "+card.getDescription()+" sur "+opponent.getName()+".");
			opponent.adjustScore(card.getVitality(), 0);
			this.mana += card.getMana();
			score = opponent.scoreToMessage();
			break;
		case potion:
			msg.setBody("bois "+card.getDescription()+".");
			adjustScore(card.getVitality(), card.getMana());
			score = scoreToMessage();
			break;
		case attack:
			msg.setBody("attaque "+opponent.getName()+" avec "+weapon.getDescription()+".");
			
			opponent.adjustScore(weapon.getVitality(), 0);
			score = opponent.scoreToMessage();
			break;
		default:
			break;
		}
		messages.add(msg);
		messages.add(score);
		this.cards.getList().remove(card);
		cards.push(card);
	}
	
	public void adjustScore(int vitality, int mana) {
		this.mana += mana;
		if(this.mana < 0) { this.mana = 0; }
		this.vitality += vitality;
		if(this.vitality < 0) { this.vitality = 0; }
	}
	
	public Message scoreToMessage() {
		return new Message(this.name, "(Vitality : "+vitality+", Mana : "+mana+")");
	}
	
	public Card getCard(int id_card) {
		for(Card card : cards.getList()) {
			if (card.getId() == id_card) { return card; }
		}
		return null;
	}
	
	synchronized public void takeCard(Card card) {
		cards.getList().add(card);
		synchronizeCards.getList().add(card);
	}
	
	synchronized public String getSynchronizeCards() {
		return synchronizeCards.toXML();
	}
	
	public boolean isDeath() {
		if(vitality > 0) { return false; }
		return true;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getVitality() {
		return vitality;
	}
	public void setVitality(int vitality) {
		this.vitality = vitality;
	}
	public int getMana() {
		return mana;
	}
	public void setMana(int mana) {
		this.mana = mana;
	}
	public Card getWeapon() {
		return weapon;
	}
	public void setWeapon(Card weapon) {
		this.weapon = weapon;
	}
	
	
}