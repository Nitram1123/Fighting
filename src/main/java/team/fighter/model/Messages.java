package team.fighter.model;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
@XmlAccessorType
public class Messages {
	private ArrayList<Message> items;
	private int meter = 0;
	public Messages() {
		items = new ArrayList<Message>();
	}
	
	public Messages(List<Message> items) {
		this.items = new ArrayList<Message>(items);
	}
	
	public void add(Message msg) {
		items.add(msg);
		msg.setId(++meter);
	}
	
	public int size() {
		return items.size();
	}
	
	public int getLastId() {
		if(items.size() > 0) {
			return items.get(items.size()-1).getId();
		}
		return 0;
	}
	
	public String toXML(int fromIndex) {
			Messages messages = new Messages(items.subList(fromIndex, items.size()));
	    String xmlString = "";
	    try {
	        JAXBContext context = JAXBContext.newInstance(Messages.class);
	        Marshaller m = context.createMarshaller();

	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

	        StringWriter sw = new StringWriter();
	        m.marshal(messages, sw);
	        xmlString = sw.toString();
	    } catch (JAXBException e) {
	        e.printStackTrace();
	    }
	    return xmlString;
	}

	@XmlElement(name="msg")
	public ArrayList<Message> getItems() {
		return items;
	}

	public void setItems(ArrayList<Message> items) {
		this.items = items;
	}
}