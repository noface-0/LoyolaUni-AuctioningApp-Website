module.exports = function (app) {
    var controller = require('../controllers/controller');
    var bodyParser = require('body-parser')
    // create application/x-www-form-urlencoded parser
    var urlencodedParser = bodyParser.urlencoded({ extended: true })

    app.post('/auth', urlencodedParser, controller.auth);

    app.get('/home', controller.home);

    app.get('/newpass', controller.newpass);
};