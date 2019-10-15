const express = require('express');
const app = express();

var mysql = require('mysql');
var http = require('http');
var fs = require('fs');

var con = mysql.createConnection({
  host: "localhost",
  user: "yourusername",
  password: "yourpassword"
});

// con.connect(function(err) {
//  if (err) throw err;
//  console.log("Connected!");
// });

// http.createServer(function (req, res) {
//   fs.readFile('demofile1.html', function(err, data) {
//     res.writeHead(200, {'Content-Type': 'text/html'});
//     res.write(data);
//     res.end();
//   });
// }).listen(8080);

app.use(express.static('public'));

app.get('/', (req, res) => {
    res.send('up and running');
});

app.listen(3000, () => console.log('listening on localhost port 3000!'));
