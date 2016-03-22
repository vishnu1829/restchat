# restchat

Currently Supports Group and peer-peer chat
No permanent storage is used as of now
Only basic functionality

postman collection- ChatServer.json.txt

MessageInterface

->getMessage       
->postMessage

MessageBufferImpl
This is used for peer to peer chatting.
We implement it using a hashMap where key is the receiverid and value is a JSONARRAY containing messagedetails like message, senderid etc

MessageGroupBufferImpl
This is used for group chatting.
We implement it using a hashMap where key is the groupid and value is a JSONARRAY containing messagedetails like message, senderid etc

FAQ's



The bellow link contains few recorded demo vidoes of Chat server application. This is the first time I am doing a recorded video, so please dont mind if you find any mistakes :)

https://drive.google.com/folderview?id=0B5TmuYcO1PAob2haRHdXMFZMUzA&usp=sharing


1. How to login
To make it simple, I did not implement register, so for now, user needs to send only the username in header to login.
2. How to create a room/topic
if some one sends a message to a room/topic which don't exists, then we are creating the group.
3  How to subscribe
the user need to send group name in header and he will receive an index.
A client always wants to fetch all newer messages in the group. So starting with the index it received, it keeps polling with the index in header. the server returns all messages after the index.when client receives n messages, it increments the current index by n.
4. Send and receive messages 
for sending a message it just need to give the username to whom it wants to send in the header.
to receive a message, it needs to just call get. the server reads his buffer and if messages are present then it will return those messages and emties the buffer.
5. Log out
not yet implemented, but is simple, we will just remove the sessiontoken from the userSession Map
6. How client works?
Client should keep poling the server by calling get on /message for every some fixed interval of time and server returns new messages if available.
For group chat, first client needs to call /subscribe and server returns the current index of the group. and the client needs to keep poling 
with the index to fetch messages new messages after the index.

we can basically add featues, but to make it simple I just implemented basic functionality.
