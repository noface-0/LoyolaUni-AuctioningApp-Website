var express = require('express');

module.exports = function() {
 var app = express();
 require('../routes/router.js')(app);
 return app;
};
