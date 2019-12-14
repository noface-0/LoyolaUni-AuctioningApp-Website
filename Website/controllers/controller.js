var mysql = require('mysql');
var db_config = require('../db/dbaccess.js');

var loggedin;
var username;
var result = [];

var con = mysql.createConnection({
    host: db_config.host,
    user: db_config.user,
    password: db_config.password,
    database: db_config.database,
    port: db_config.port
});

con.connect(function (err) {
    if (err) {
        throw err;
    }
    console.log("Connection to JAJEIMO Successful!");
});

exports.admin = function (res) {
    res.sendFile('admin.html', {
        root: 'public'
    });
};


exports.auth = function (req, res) {
    console.log("USER " + req.body.username + " LOGGED IN!");
    username = req.body.username;
    let password = req.body.password;
    if (username && password) {
        con.query('SELECT * FROM admins WHERE username = ? AND password = ?', [username, password], function (error, results, fields) {
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

        con.query("SELECT * FROM items", function (err, result) {
            if (err) throw err;
            json = JSON.parse(JSON.stringify(result));
            con.query("SELECT * FROM admins", function (err, result) {
                if (err) throw err;
                json2 = JSON.parse(JSON.stringify(result));
                con.query("SELECT * FROM event", function (err, result) {
                    if (err) throw err;
                    json3 = JSON.parse(JSON.stringify(result));
                    console.log(json3[0].end);
                    var d = new Date();
                    console.log(d);
                    if(false){
                        res.render('home', {dataItem: json, dataAdmin: json2, dataEvent: json3});
                    }else{
                        con.query("SELECT * FROM users WHERE FirstName IN ('Mollie' AND 'Jennifer')", function (err, result) {
                            if (err) throw err;
                            json4 = JSON.parse(JSON.stringify(result));
                            res.render('home', {dataItem: json, dataAdmin: json2, dataEvent: json3, dataWin: json4});
                        });
                    }
                });
            });
        });
    } else {
        res.send('Please login to view this page!');
        res.end();
    }
};

exports.newpass = function (req, res) {
    res.send('new password');
};

function add() {
    if (err) throw err;
    var sql = "INSERT INTO customers (name, address) VALUES ('Company Inc', 'Highway 37')";
    con.query(sql, function (err, result) {
        if (err) throw err;
        console.log("1 record inserted");
    });
}


// function delete(table, column, info){
//     if (err) throw err;
//     var sql = "DELETE FROM " + table +" WHERE " + column + " = '" + info + "'";
//     con.query(sql, function (err, result) {
//       if (err) throw err;
//       console.log("Number of records deleted: " + result.affectedRows);
//     });
// }

function edit(table, column, newinfo, oldinfo) {
    if (err) throw err;
    var sql = "UPDATE " + table + " SET " + column + " = '" + newinfo + "' WHERE " + column + " = '" + oldinfo + "'";
    con.query(sql, function (err, result) {
        if (err) throw err;
        console.log(result.affectedRows + " record(s) updated");
    });
}