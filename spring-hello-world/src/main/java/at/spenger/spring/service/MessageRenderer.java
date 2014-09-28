package at.spenger.spring.service;

// A so called: business interface
public interface MessageRenderer {
	void render();

	// Setters used solely for injection shouldn't be here! 
	void setMessageProvider(MessageProvider provider);
	MessageProvider getMessageProvider();
}
