package team.fighter.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement 
@XmlAccessorType
public class Card implements Serializable {
	private int id;
	private String description;
	private int vitality;
	private int mana;
	@XmlTransient
	public TypeCard type;
	public Card() { }
	public Card(String description, int vitality, int mana, TypeCard type) {
		this.id = -1;
		this.description = description;
		this.vitality = vitality;
		this.mana = mana;
		this.type = type;
	}
	
	@XmlAttribute
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
}