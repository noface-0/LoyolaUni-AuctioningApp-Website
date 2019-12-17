$('#homeClick').on('click', function () {
    if ($('#about').css('display') != 'none' || $('#admin').css('display') != 'none' || $('#event').css('display') != 'none' || $('#winners').css('display') != 'none') {
        document.getElementById("winnersClick").className = "inactive";
        document.getElementById("eventClick").className = "inactive";
        document.getElementById("adminClick").className = "inactive";
        document.getElementById("aboutClick").className = "inactive";
        document.getElementById("homeClick").className = "active";
        $('#home').show().siblings('div').hide();
    }
});

$('#adminClick').on('click', function () {
    if ($('#about').css('display') != 'none' || $('#home').css('display') != 'none' || $('#event').css('display') != 'none' || $('#winners').css('display') != 'none') {
        document.getElementById("homeClick").className = "inactive";
        document.getElementById("eventClick").className = "inactive";
        document.getElementById("winnersClick").className = "inactive";
        document.getElementById("aboutClick").className = "inactive";
        document.getElementById("adminClick").className = "active";
        $('#admin').show().siblings('div').hide();
    }
});

$('#aboutClick').on('click', function () {
    if ($('#home').css('display') != 'none' || $('#admin').css('display') != 'none' || $('#event').css('display') != 'none' || $('#winners').css('display') != 'none') {
        document.getElementById("homeClick").className = "inactive";
        document.getElementById("eventClick").className = "inactive";
        document.getElementById("adminClick").className = "inactive";
        document.getElementById("winnersClick").className = "inactive";
        document.getElementById("aboutClick").className = "active";
        $('#about').show().siblings('div').hide();
    }
});

$('#eventClick').on('click', function () {
    if ($('#home').css('display') != 'none' || $('#admin').css('display') != 'none' || $('#about').css('display') != 'none' || $('#winners').css('display') != 'none') {
        document.getElementById("homeClick").className = "inactive";
        document.getElementById("winnersClick").className = "inactive";
        document.getElementById("adminClick").className = "inactive";
        document.getElementById("aboutClick").className = "inactive";
        document.getElementById("eventClick").className = "active";
        $('#event').show().siblings('div').hide();
    }
});

$('#winnersClick').on('click', function () {
    if ($('#home').css('display') != 'none' || $('#admin').css('display') != 'none' || $('#about').css('display') != 'none' || $('#event').css('display') != 'none') {
        document.getElementById("homeClick").className = "inactive";
        document.getElementById("eventClick").className = "inactive";
        document.getElementById("adminClick").className = "inactive";
        document.getElementById("aboutClick").className = "inactive";
        document.getElementById("winnersClick").className = "active";
        $('#winners').show().siblings('div').hide();
    }
});



function add() {
    modal.style.display = "block";
}

function edit() {
    modal1.style.display = "block";
}

function del() {
    modal2.style.display = "block";
}

function editEvent() {
    modal3.style.display = "block";
}

function delEvent() {
    modal4.style.display = "block";
}

function addAdmin() {
    modal5.style.display = "block";
}

function delAdmin() {
    modal6.style.display = "block";
}

function editAdmin() {
    modal7.style.display = "block";
}

// Items
var modal = document.getElementById("add");
var modal1 = document.getElementById("edit");
var modal2 = document.getElementById("delete");
// Events
var modal3 = document.getElementById("eventEdit");
var modal4 = document.getElementById("eventDelete");
// Admins
var modal5 = document.getElementById("adminAdd");
var modal6 = document.getElementById("adminDelete");
var modal7 = document.getElementById("adminEdit");

window.onclick = function (event) {
    if (event.target == modal || event.target == modal1 || event.target == modal2 || event.target == modal3 || event.target == modal4 || event.target == modal5 || event.target == modal6 || event.target == modal7) {
        modal.style.display = "none";
        modal1.style.display = "none";
        modal2.style.display = "none";
        modal3.style.display = "none";
        modal4.style.display = "none";
        modal5.style.display = "none";
        modal6.style.display = "none";
        modal7.style.display = "none";
    }
}