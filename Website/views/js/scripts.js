$('#homeClick').on('click',function(){
    if($('#about').css('display')!='none' || $('#admin').css('display')!='none'){
        $('#home').show().siblings('div').hide();
    }
});

$('#adminClick').on('click',function(){
    if($('#about').css('display')!='none' || $('#home').css('display')!='none'){
        $('#admin').show().siblings('div').hide();
    }
});

$('#aboutClick').on('click',function(){
    if($('#home').css('display')!='none'|| $('#admin').css('display')!='none'){
        $('#about').show().siblings('div').hide();
    }
});

function myFunction() {
        alert("My username is:");
}

