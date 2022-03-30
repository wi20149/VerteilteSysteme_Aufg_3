package pubsub.subscriber;

import java.util.ArrayList;
import java.util.List;
import pubsub.Message;
import pubsub.service.PubSubService;
 
public abstract class Subscriber {	
	//in dieser Array Liste werden alle Nachrichten gespeichert
	private List<Message> subscriberMessages = new ArrayList<Message>();
	
	public List<Message> getSubscriberMessages() {
		return subscriberMessages;
	}
	public void setSubscriberMessages(List<Message> subscriberMessages) {
		this.subscriberMessages = subscriberMessages;
	}
	
	//Subscriber wird hinzugefügt zum PubSubService für ein Thema 
	public abstract void addSubscriber(String topic, PubSubService pubSubService);
	
	//Subscriber wird entfernt 
	public abstract void unSubscribe(String topic, PubSubService pubSubService);
	
	//Besondere Anfragen für ein Thema an den Service
	public abstract void getMessagesForSubscriberOfTopic(String topic, PubSubService pubSubService);
	
	//auf der Konsole werden die Nachrichten alle ausgedruckt 
	public void printMessages(){
		for(Message message : subscriberMessages){
			System.out.println("Message Topic -> "+ message.getTopic() + " : " + message.getPayload());
		}
	}
}