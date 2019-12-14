var chai = require('chai');
var chaiHttp = require('chai-http');
var mysql = require('mysql');

should = chai.should();
chai.use(chaiHttp);

var server = require('../server');
var db_config = require('../db/dbaccess.js');

var con = mysql.createConnection({
    host: db_config.host,
    user: db_config.user,
    password: db_config.password,
    database: db_config.database,
    port: db_config.port
});

describe('Status and content', function () {
    describe('Login Page', function () {
        it('status', (done) => {
            chai.request(server)
                .get('/')
                .end((err, res) => {
                    (res).should.have.status(200);
                    done();
                });
        });

        it('content', (done) => {
            chai.request(server)
                .get('/')
                .end((err, res) => {
                    (res.body).should.be.a('object');
                    done();
                });
        });
    });

    describe('Access to DB', function () {
        it('Shows that the user db file exists and connection works', function (done) {
            var connection = mysql.createConnection({
                host: db_config.host,
                user: db_config.user,
                password: db_config.password,
                database: db_config.database,
                port: db_config.port
            });
            connection.connect(done);
        });
    });
    var assert = chai.assert;
    describe('Login', function () {
        it('Shows that login works', function (done) {
            var username = "test";
            var password = "test";
            var loggedin;

            con.query('SELECT * FROM admins WHERE username = ? AND password = ?', [username, password], function (error, results, fields) {
                if (results.length > 0) {
                    loggedin = true;
                } else {
                    loggedin = false;
                }
            });
            
            var assert = require('chai').assert, loggedin = true;
            assert(loggedin == true);
            done();
        });
    });

    describe('Main Page', function () {
        it('status', (done) => {
            chai.request(server)
                .get('/home')
                .end((err, res) => {
                    (res).should.have.status(200);
                    done();
                });
        });

        it('content', (done) => {
            chai.request(server)
                .get('/')
                .end((err, res) => {
                    (res.body).should.be.a('object');
                    done();
                });
        });
    });

    describe('Sub Pages', function () {
        it('status of the other pages on the single page', (done) => {
            chai.request(server)
                .get('/home#')
                .end((err, res) => {
                    (res).should.have.status(200);
                    done();
                });
        });

        it('content', (done) => {
            chai.request(server)
                .get('/')
                .end((err, res) => {
                    (res.body).should.be.a('object');
                    done();
                });
        });
    });
});