package team.fighter.model;

import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement 
@XmlAccessorType
public class Deck {
	protected ArrayList<Card> cards;

	public Deck() {
		cards = new ArrayList<Card>();
	}
	
	@XmlElement(name="card")
	synchronized public ArrayList<Card> getList() {
		return cards;
	}

	synchronized public void setList(ArrayList<Card> cards) {
		this.cards = cards;
	}
	
	synchronized public String toXML() {
    String xmlString = "";
    if(cards.size() < 1) { return xmlString; }
    try {
        JAXBContext context = JAXBContext.newInstance(Deck.class);
        Marshaller m = context.createMarshaller();

        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        StringWriter sw = new StringWriter();
        m.marshal(this, sw);
        xmlString = sw.toString();
        cards = new ArrayList<Card>();
    } catch (JAXBException e) {
        e.printStackTrace();
    }
    return xmlString;
}
}
