// Require the packages we will use:
var http = require("http"),
socketio = require("socket.io"),
fs = require("fs");

// Listen for HTTP connections.  This is essentially a miniature static file server that only serves our one file, client.html:
var app = http.createServer(function(req, resp){
	// This callback runs when a new connection is made to our HTTP server.

	fs.readFile("client.html", function(err, data){
		// This callback runs when the client.html file has been read from the filesystem.

		if(err) return resp.writeHead(500);
		resp.writeHead(200);
		resp.end(data);
	});
});
app.listen(3456);
// var usernames = {};
class user {
	constructor(username, room) {
		this.username = username;
		this.room = room;
	}
}
var userinfo = [new user()];
// var sockets = []; // holds socket infromation  for each user
var rooms =['Public', 'Room1', 'Room2'];
var passwords = ['', '', '']; // current rooms have no password

// var rooms = [room1['user1', 'user2', 'user3'],'room2','room3', 'room4'];
// or have a user class that has a room param
//
// Do the Socket.IO magic:
var io = socketio.listen(app);
io.sockets.on("connection", function(socket){
	// sockets.push(socket);

	// for(){ if(sockets[i].room ==  "" //)   }
	io.sockets.emit("updateRoom", rooms); // send the rooms array to client
	// io.sockets.emit("showUsers", userinfo, room[0]); // send username list and current room to client

	// console.log(userinfo[0].username); //returns root
	// console.log(userinfo); //returns [ user { username: 'root', room: 'public' } ]


	// socket.join(rooms[0]); // set room to default room

	socket.on("newuser", function(username){
		socket.join(rooms[0]); // set room to default room

		// store the username in the socket session for this client
		// io.sockets.join(rooms[0]);
		// add the client's username to the global list
		// usernames[username] = username; // add to the usernames list
		this.username = username; // set username to socket variable.
		this.room= rooms[0]; // set room to default room public
		userinfo.push(new user(this.username, this.room));

		//usernames[username] = this.username; // add username to the list users
		io.sockets.to(this.room).emit("showUsers", userinfo, this.room); // send username list and current room to client


		console.log(this.username + " has connected to room: " + this.room); // messages for connecting and disconnecting
		io.sockets.to(this.room).emit("message_to_client", "", {message: this.username  + " has connected to room: " + this.room});

		socket.on('disconnect', function(){ // disp when someone disconnects
			console.log(username + ' has disconnected');
			io.sockets.to(this.room).emit("message_to_client", "", {message: this.username  + " has disconnected from room: " + this.room});

		});


	});

	socket.on("changeRoom", function(room, password) {
		for (i = 0; i< rooms.length; i ++) {
			if (this.room != room) {
				if ((rooms[i] == room) && (passwords[i] == password)) {  // if both the room and password are correct
					socket.leave(this.room);
					console.log(this.username + ' has disconnected from room: ' + this.room);
					io.sockets.to(this.room).emit("message_to_client", "", {message: this.username  + " has disconnected from room: " + this.room});
					for (i=0; i < userinfo.length; i ++){ // update room for the user
						if (userinfo[i].username == this.username) { // set room to undefined when they leave
							userinfo[i].room = undefined;
						}
					}
					io.sockets.to(this.room).emit("showUsers", userinfo, this.room); // send username list and current room to client



					socket.join(room);
					this.room = room;

					console.log(this.username + " has connected to room: " + this.room); // messages for connecting and disconnecting
					io.sockets.to(this.room).emit("message_to_client", "", {message: this.username  + " has connected to room: " + this.room});


					for (i=0; i < userinfo.length; i ++){ // update room for the user
						if (userinfo[i].username == this.username) {
							userinfo[i].room = this.room;
							// console.log(userinfo[i].username);
						}
					}
					// console.log(userinfo);
					io.sockets.to(this.room).emit("showUsers", userinfo, this.room); // send username list and current room to client

				}
			}
		}



	});

	socket.on("addRoom", function(room, password) {
		rooms.push(room); // add new room to rooms list
		passwords.push(password);
		io.sockets.emit("updateRoom", rooms); // send the rooms array to client
	});

	// This callback runs when a new Socket.IO connection is established.
	socket.on('message_to_server', function(data) {
		// This callback runs when the server receives a new message from the client.
		console.log(this.username+ ": "+data["message"]); // log it to the Node.JS output
		// console.log(data["message"][startIndex] + data["message"][startIndex+1]);

		io.sockets.to(this.room).emit("message_to_client",this.username, {message:data["message"] }) // broadcast the message to other users
	});


});
