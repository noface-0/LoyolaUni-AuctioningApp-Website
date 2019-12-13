var express = require('./config/express');
var pubFiles = require('express');
var bodyParser = require('body-parser');
var app = express();
var morgan = require('morgan')
app.use(morgan(':method :url :status :res[content-length] - :response-time ms'));
app.set('view engine', 'ejs');
app.use('/',pubFiles.static("views"));
app.use(bodyParser.urlencoded({extended : true}));
app.use(bodyParser.json());
app.listen(3000);
module.exports = app;
console.log('Server running at http://localhost:3000/');