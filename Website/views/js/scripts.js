$('#homeClick').on('click',function(){
    if($('#about').css('display')!='none' | $('#info').css('display')!='none'){
        $('#home').show().siblings('div').hide();
    }
});

$('#aboutClick').on('click',function(){
    if($('#home').css('display')!='none'| $('#info').css('display')!='none'){
        $('#about').show().siblings('div').hide();
    }
});

$('#infoClick').on('click',function(){
    if($('#about').css('display')!='none' | $('#home').css('display')!='none'){
        $('#info').show().siblings('div').hide();
    }
});

function myFunction() {
        alert("My username is:");
}

