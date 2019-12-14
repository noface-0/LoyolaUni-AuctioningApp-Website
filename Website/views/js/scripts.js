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
var modal = document.getElementById("add");
var modal1 = document.getElementById("edit");
var modal2 = document.getElementById("delete");


window.onclick = function (event) {
    if (event.target == modal || event.target == modal1 || event.target == modal2) {
        modal.style.display = "none";
        modal1.style.display = "none";
        modal2.style.display = "none";
    }
}

span.onclick = function () {
    modal.style.display = "none";
    // modal1.style.display = "none";
    // modal2.style.display = "none";
}
// // Get the modal
// var modal = document.getElementById("myModal");

// // Get the button that opens the modal
// var btn = document.getElementById("myBtn");

// // Get the <span> element that closes the modal
// var span = document.getElementsByClassName("close")[0];

// // When the user clicks the button, open the modal 
// btn.onclick = function() {
//   modal.style.display = "block";
// }

// // When the user clicks on <span> (x), close the modal
// span.onclick = function() {
//   modal.style.display = "none";
// }

// // When the user clicks anywhere outside of the modal, close it
// window.onclick = function(event) {
//   if (event.target == modal) {
//     modal.style.display = "none";
//   }
// }