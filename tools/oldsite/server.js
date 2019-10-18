const express = require('express');
const app = express();

var mysql = require('mysql');
var http = require('http');
var fs = require('fs');

var con = mysql.createConnection({
  host: "cs-database.cs.loyola.edu",
  user: "jkitson",
  password: "1736438",
  database: "jajeimo",
  port: 3306
});

con.connect(function(err) {
 if (err) throw err;
 console.log("Connected to Database");
});
global.con = con;

app.use(express.static('public'));

app.get('/', (req, res) => {
    res.send('up and running');
});

app.listen(3000, () => console.log('listening on localhost port 3000!'));
