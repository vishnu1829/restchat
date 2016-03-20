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

How client works?

Client should keep poling the server by calling get on /message for every some fixed interval of time to know if new messages are available.
For group chat, first client needs to call /subscribe and server returns the current index of the group. and the client needs to keep poling 
with the index to fetch messages new messages after the index.
