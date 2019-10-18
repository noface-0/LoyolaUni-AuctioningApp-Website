var mysql = require('mysql');

var con = mysql.createConnection({
    host: "cs-database.cs.loyola.edu",
    user: "jkitson",
    password: "1736438",
    database: "jajeimo",
    port: 3306
});

con.connect(function (err) {
    if (err) throw err;
    console.log("Connection to JAJEIMO Successful!");
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