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
var creators = ['', '', ''];
var banned = [[''],[''],['']];
var nameToId = {};

// Do the Socket.IO magic:
var io = socketio.listen(app);
io.sockets.on("connection", function(socket){
	// sockets.push(socket);

	io.sockets.emit("updateRoom", {rooms:rooms}); // send the rooms array to client

	socket.on("newuser", function(username){
		socket.join(rooms[0]); // set room to default room
		this.username = username; // set username to socket variable.
		nameToId[this.username] = socket.id;
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

	socket.on("kickAUser", function(data) {
		for (i=0; i < creators.length; i ++){ // update room for the user
			if (creators[i] == this.username) {
				io.sockets.to(this.room).emit("showUsers", userinfo, this.room);
				var userKicked = data['kickusername'];
				var chatRoom = data['chatroom'];
				console.log(userKicked + " was just kicked out of this room");
				io.sockets.to(this.room).emit("message_to_client", "", {message: userKicked + " has been kicked out of this room"});
				chatRoom = rooms[0];
				for (i=0; i < userinfo.length; i ++){ // update room for the user
					if (userinfo[i].username == userKicked) {
						userinfo[i].room = chatRoom;
					}
				}
				io.sockets.to(this.room).emit("showUsers", userinfo, this.room); // send username list and current room to client
				console.log(userinfo);

			}
		}
	});

	socket.on("banAUser", function(data) {
		for (i=0; i < creators.length; i ++){ // update room for the user
			// if (creators[i] == this.username) {
			if(true){
				io.sockets.to(this.room).emit("showUsers", userinfo, this.room);
				var userBanned = data['banusername'];
				var chatRoom = data['chatroom'];
				console.log(userBanned + " was just banned from this room");
				io.sockets.to(this.room).emit("message_to_client", "", {message: userBanned + " has been banned from this room"});
				chatRoom = rooms[0];
				for (j=0; j < rooms.length; j++) {
					if (rooms[j] == this.room) {
						banned[j].push(userBanned);
					}
				}
				for (i=0; i < userinfo.length; i ++){ // update room for the user
					if (userinfo[i].username == userBanned) {
						userinfo[i].room = chatRoom;
					}
				}
				io.sockets.to(this.room).emit("showUsers", userinfo, this.room); // send username list and current room to client
				//console.log(userinfo);
			}
		}
	});

	socket.on("changeRoom", function(room, password) {
		for (i = 0; i< rooms.length; i ++) {
			if ((rooms[i] == room) && (passwords[i] == password)) {  // if both the room and password are correct
				socket.leave(this.room);
				shouldJoin = true;
				for (k=0; k < banned[i].length; k++) {
					if (this.username == banned[i][k]) {
						// alert("Sorry you have been banned.");
						console.log("user", this.username,"is banned");
							shouldJoin = false;
					}
				}
				if (shouldJoin) {
					console.log(this.username,"shouldJoin");
					console.log(this.username + ' has disconnected from room: ' + this.room);
					io.sockets.to(this.room).emit("message_to_client", "", {message: this.username  + " has disconnected from room: " + this.room});
					for (i=0; i < userinfo.length; i ++){ // update room for the user
						if (userinfo[i].username == this.username) { // set room to undefined when they leave
							userinfo[i].room = undefined;
						}
					}
					this.room = room;
					socket.join(room);
					io.sockets.to(this.room).emit("showUsers", userinfo, this.room); // send username list and current room to client

					console.log(this.username + " has connected to room: " + this.room); // messages for connecting and disconnecting
					io.sockets.to(this.room).emit("message_to_client", "", {message: this.username  + " has connected to room: " + this.room});

					for (i=0; i < userinfo.length; i ++){ // update room for the user
						if (userinfo[i].username == this.username) {
							userinfo[i].room = this.room;
						}
					}
					io.sockets.to(this.room).emit("showUsers", userinfo, this.room); // send username list and current room to client

				} else {
					console.log(this.username,"shloud not join");
					io.sockets.to(this.username).emit("banned", "You are banned from this room");
				}

				//
				// shouldJoin = true;
				//
				// console.log("looking for", room);
				// for (j=0; j < rooms.length; j++) {
				// 	if (rooms[j] == room) {
				// 		for (k=0; k < banned[j].length; k++) {
				// 			if (this.username == banned[j]) {
				// 				alert("Sorry you have been banned.");
				// 				console.log("user", this.username,"is banned");
				// 					shouldJoin = false;
				// 			}
				// 		}
				// 	}
				// }
				// if(shouldJoin){
				// 	this.room = room;
				// 	socket.join(room);
				// 	io.sockets.to(this.room).emit("showUsers", userinfo, this.room); // send username list and current room to client
				//
				// 	console.log(this.username + " has connected to room: " + this.room); // messages for connecting and disconnecting
				// 	io.sockets.to(this.room).emit("message_to_client", "", {message: this.username  + " has connected to room: " + this.room});
				//
				// 	for (i=0; i < userinfo.length; i ++){ // update room for the user
				// 		if (userinfo[i].username == this.username) {
				// 			userinfo[i].room = this.room;
				// 		}
				// 	}
				// 	io.sockets.to(this.room).emit("showUsers", userinfo, this.room); // send username list and current room to client
				// }

		}
		}
	});

	socket.on("addRoom", function(room, password) {
		rooms.push(room); // add new room to rooms list
		passwords.push(password);
		creators.push(this.username);
		banned.push(['']);
		io.sockets.emit("updateRoom", {rooms: rooms}); // send the rooms array to client
	});

	// This callback runs when a new Socket.IO connection is established.
	socket.on('message_to_server', function(data) {
		// This callback runs when the server receives a new message from the client.
		console.log(this.username+ ": "+data["message"]); // log it to the Node.JS output
		// console.log(data["message"][startIndex] + data["message"][startIndex+1]);

		io.sockets.to(this.room).emit("message_to_client",this.username, {message:data["message"] }) // broadcast the message to other users
	});

	socket.on('message_to_pm', function(data) {
		console.log(this.username + ": " + data["message"]);
		io.sockets.to(nameToId[data.pm]).emit("message_to_pm",this.username, {sender:this.username, message:data["message"]})
	});


});
