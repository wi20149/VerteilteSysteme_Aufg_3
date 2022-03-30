package pubsub.publisher;


import pubsub.Message;
import pubsub.service.PubSubService;
 
public interface Publisher {	
	//veröffentlicht neue Nachrichten im PubSubService
	void publish(Message message, PubSubService pubSubService);
}