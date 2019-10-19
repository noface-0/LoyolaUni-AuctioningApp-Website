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
    console.log("Connected to AnimDB Successful!");
});

exports.index = function (req, res) {
    res.setHeader('Content-Type', 'text/plain');
    res.write('Animals: http://localhost:3000/animals\n');
    con.query("SELECT Name FROM Animals", function (err, result) {
        if (err) throw err;
        let json = JSON.parse(JSON.stringify(result));
        for (let i = 0; i < json.length; i++) {
            res.write("\n" + json[i].Name);
            res.write(": http://localhost:3000/animals/" + i);
        };
        res.end();
    });

};

exports.animList = function (req, res) {
    res.setHeader('Content-Type', 'text/plain');
    con.query("SELECT * FROM Animals", function (err, result) {
        if (err) throw err;
        let json = JSON.parse(JSON.stringify(result));
        res.json(json);
    });
};

exports.anim = function (req, res) {
    res.setHeader('Content-Type', 'text/plain');
    con.query("SELECT * FROM Animals WHERE id = " + req.params.id, function (err, result) {
        if (err) throw err;
        let json = JSON.parse(JSON.stringify(result));
        res.json(json);
    });
};