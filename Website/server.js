var express = require('./config/express');
var pubFiles = require('express');
var app = express();
app.use(pubFiles.static('public'));
app.listen(3000);
module.exports = app;
console.log('Server running at http://localhost:3000/');