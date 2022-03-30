package pubsub.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import pubsub.Message;
import pubsub.subscriber.Subscriber;
 
public class PubSubService {
	//Behält die Menge der Abonnententhemen bei, um Duplikate zu vermeiden
	Map<String, Set<Subscriber>> subscribersTopicMap = new HashMap<String, Set<Subscriber>>();
 
	//die Warteschlange speichert die Messages zwischen
	Queue<Message> messagesQueue = new LinkedList<Message>();
 
	//fügt Nachrichten der Message Warteschlange hinzu
	public void addMessageToQueue(Message message){
		messagesQueue.add(message);
	}
 
	//fügt den Subscriber einem Thema hinzu
	public void addSubscriber(String topic, Subscriber subscriber){
 
		if(subscribersTopicMap.containsKey(topic)){
			Set<Subscriber> subscribers = subscribersTopicMap.get(topic);
			subscribers.add(subscriber);
			subscribersTopicMap.put(topic, subscribers);
		}else{
			Set<Subscriber> subscribers = new HashSet<Subscriber>();
			subscribers.add(subscriber);
			subscribersTopicMap.put(topic, subscribers);
		}		
	}
 
	//entfernt den Subscriber von einem Thema
	public void removeSubscriber(String topic, Subscriber subscriber){
 
		if(subscribersTopicMap.containsKey(topic)){
			Set<Subscriber> subscribers = subscribersTopicMap.get(topic);
			subscribers.remove(subscriber);
			subscribersTopicMap.put(topic, subscribers);
		}
	}
	
	//Broadcast neuer Nachrichten die der Warteschlange hinzugefügt worden waren werden allen Subscriber von dem Thema hinzugefügt 
	//Nachrichtenschlange ist nach dem broadcasting leer 
	public void broadcast(){
		if(messagesQueue.isEmpty()){
			System.out.println("No messages from publishers to display");
		}
		else{
			while(!messagesQueue.isEmpty()){
				Message message = messagesQueue.remove();
				String topic = message.getTopic();
 
				Set<Subscriber> subscribersOfTopic = subscribersTopicMap.get(topic);
 
				for(Subscriber subscriber : subscribersOfTopic){
					//fügt broadcasted messages der Warteschlange von dem Subscriber hinzu 
					List<Message> subscriberMessages = subscriber.getSubscriberMessages();
					subscriberMessages.add(message);
					subscriber.setSubscriberMessages(subscriberMessages);
				}			
			}
		}
	}
	
	//sendet Nachrichten zu einem Thema an die Abonnenten 
	public void getMessagesForSubscriberOfTopic(String topic, Subscriber subscriber) {
		if(messagesQueue.isEmpty()){
			System.out.println("No messages from publishers to display");
		}else{
			while(!messagesQueue.isEmpty()){
				Message message = messagesQueue.remove();
 
				if(message.getTopic().equalsIgnoreCase(topic)){
 
					Set<Subscriber> subscribersOfTopic = subscribersTopicMap.get(topic);
 
					for(Subscriber _subscriber : subscribersOfTopic){
						if(_subscriber.equals(subscriber)){
							//fügt die Nachrichten der Warteschlange der Subscriber hinzu
							List<Message> subscriberMessages = subscriber.getSubscriberMessages();
							subscriberMessages.add(message);
							subscriber.setSubscriberMessages(subscriberMessages);
						}
					}
				}
			}
		}
	}
 
}