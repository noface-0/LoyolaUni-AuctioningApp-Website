var mysql = require('mysql');
var db_config = require('../config/dbaccess.js');

var loggedin;
var username;

var con = mysql.createConnection({
    host: db_config.host,
    user: db_config.user,
    password: db_config.password,
    database: db_config.database,
    port: db_config.port
});

con.connect(function (err) {
    if (err){
       throw err;
    }
    console.log("Connection to JAJEIMO Successful!");
});

exports.admin = function (res) {
    res.sendFile('admin.html', {root:'public'});
};


exports.auth = function (req, res) {
    console.log("USER " + req.body.username + " LOGGED IN!");
    username = req.body.username;
	let password = req.body.password;
	if (username && password) {
		con.query('SELECT * FROM admins WHERE username = ? AND password = ?', [username, password], function(error, results, fields) {
			if (results.length > 0) {
				loggedin = true;
				res.redirect('/home');
			} else {
				res.send('Incorrect Username and/or Password!');
			}			
			res.end();
		});
	} else {
		res.send('Please enter Username and Password!');
		res.end();
	}
};


exports.home = function (req, res) {
    if (loggedin) {
        console.log('Welcome back, ' + username + '!');
        res.sendFile('home.html', {root:'public'});
    } else {
        res.send('Please login to view this page!');
        res.end();
    }
};

exports.newpass = function (req, res) {
    res.send('new password');
};
