package pubsub;


import pubsub.publisher.Publisher;
import pubsub.publisher.PublisherImpl;
import pubsub.service.PubSubService;
import pubsub.subscriber.Subscriber;
import pubsub.subscriber.SubscriberImpl;
 
public class DriverClass {
	public static void main(String[] args) {
		
		//Instanziert publishers, subscribers and PubSubService 
		Publisher APublisher = new PublisherImpl();
		Publisher BPublisher = new PublisherImpl();
		
		Subscriber ASubscriber = new SubscriberImpl();
		Subscriber allLanguagesSubscriber = new SubscriberImpl();
		Subscriber BSubscriber = new SubscriberImpl();
		
		PubSubService pubSubService = new PubSubService();
		
		//Fügte den Publish Messages zum PubSubService hinzu
		Message AMsg1 = new Message("Nachricht A.1");
		Message AMsg2 = new Message("Nachricht A.2");
		Message AMsg3 = new Message("Nachricht A.3");
		
		APublisher.publish(AMsg1, pubSubService);
		APublisher.publish(AMsg2, pubSubService);
		APublisher.publish(AMsg3, pubSubService);
		
		Message BMsg1 = new Message("Nachricht B.1");
		Message BMsg2 = new Message("Nachricht B.2");
		
		BPublisher.publish(AMsg1, pubSubService);
		BPublisher.publish(AMsg2, pubSubService);
		
		//deklariert Subscribers 
		ASubscriber.addSubscriber("A",pubSubService);		//A Subscriber nur zu A Topics
		BSubscriber.addSubscriber("B",pubSubService);   //B Subscriber nur zu B Topics
		allLanguagesSubscriber.addSubscriber("A", pubSubService);	//alle Subscriber
		allLanguagesSubscriber.addSubscriber("B", pubSubService);
		
		//Abbbonement beenden durch: 
		//BSubscriber.unSubscribe("B", pubSubService);
		
		//Broadcast Nachricht an alle Abonnementen, nach dem Broadcast ist die Warteschlange leer im PubSubServie
		pubSubService.broadcast();
		
		//alle Messages werden in der Konsole ausgegeben 
		System.out.println("Messages of A Subscriber are: ");
		ASubscriber.printMessages();
		
		System.out.println("\nMessages of B Subscriber are: ");
		BSubscriber.printMessages();
		
		System.out.println("\nMessages of All Languages Subscriber are: ");
		allLanguagesSubscriber.printMessages();
		
		//nach dem Broadcast ist die Warteschlange leer und es werden neue Nachrichten an den Server gesendet 
		System.out.println("\nPublishing 2 more A Messages...");
		Message AMsg4 = new Message("Nachricht A.4");
		Message AMsg5 = new Message("Nachricht A.5");
		
		APublisher.publish(javaMsg4, pubSubService);
		APublisher.publish(javaMsg5, pubSubService);
		
		ASubscriber.getMessagesForSubscriberOfTopic("A", pubSubService);
		System.out.println("\nMessages of A Subscriber now are: ");
		ASubscriber.printMessages();		
	}
}