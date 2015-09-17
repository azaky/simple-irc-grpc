namespace java if4031

service UserService {

	// Get token
	string getToken(),

	// Get the nickname
	string getNickname(1:string token),

	// Set nickname
	bool setNickname(1:string token, 2:string nickname),

	// Join a channel
	void joinChannel(1:string token, 2:string channelName),

	// Leave a channel
	void leaveChannel(1:string token, 2:string channelName),

	// Send a message
	void sendMessage(1:string token, 2:string message),

	// Send a message to a particular channel
	void sendMessageToChannel(1:string token, 2:string message, 3:string channelName),

	// Get messages
	list<string> getMessage(1:string token),

	// Exit from the application
	void exit(1:string token),
}