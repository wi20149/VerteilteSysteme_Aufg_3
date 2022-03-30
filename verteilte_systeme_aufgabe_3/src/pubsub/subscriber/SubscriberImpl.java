package pubsub.subscriber;

import pubsub.service.PubSubService;
 
public class SubscriberImpl extends Subscriber{
	//Fügt Abonnement mit einem Thema dem Servie hinzu
	public void addSubscriber(String topic, PubSubService pubSubService){
		pubSubService.addSubscriber(topic, this);
	}
	
	//Löscht den Abonnement von der Liste, sodass keine weiteren Nachrichten an diesen zu diesem Thema geschickt werden
	public void unSubscribe(String topic, PubSubService pubSubService){
		pubSubService.removeSubscriber(topic, this);
	}
 
	//Besondere Anfragen für eine Nachricht zu einem Thema an den PubSubService
	public void getMessagesForSubscriberOfTopic(String topic, PubSubService pubSubService) {
		pubSubService.getMessagesForSubscriberOfTopic(topic, this);
		
	}	
}