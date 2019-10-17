var mysql = require('mysql');

var con = mysql.createConnection({
    host: 'localhost',
    user: 'test',
    database: 'AnimDB'
});

con.connect(function (err) {
    if (err) throw err;
    console.log("Connected to AnimDB Successful!");
});

exports.index = function (req, res) {
    res.sendFile('index.html', { root:'views'});
};

exports.animList = function (req, res) {
    res.setHeader('Content-Type', 'text/plain');
    con.query("SELECT * FROM Animals", function (err, result) {
        if (err) throw err;
        let json = JSON.parse(JSON.stringify(result));
        res.json(json);
    });
};