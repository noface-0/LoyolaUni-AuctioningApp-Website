const debug = false;
const menu = document.getElementById("animalmenu");
const Url = 'http://localhost:3000/animals/';

function propagateList(){
    fetch(Url)
    .then(data=>{return data.json()})
    .then(res=>{
        for(let i = 0; i < res.length; i++){
            if(debug){console.log(res[i].Name);}
            let option = document.createElement("option");
            option.text = res[i].Name; //Add the name into list as text
            menu.add(option); //add the text to the list
        }
    });
}

function showJson(){
    let response = document.getElementById("response");
    let selectedValue = menu.selectedIndex;
    if(debug){alert(selectedValue);}
    selectedValue = selectedValue-1;
    fetch(Url)
    .then(data=>{return data.json()})
    .then(res=>{
        if(debug){console.log(JSON.stringify(res[selectedValue]));}
        if(selectedValue < 0){
            response.innerHTML=""; // If one clicks back onto default this is -1 and gives an undefined
        }else{
            response.innerHTML =  JSON.stringify(res[selectedValue]);
        }
    });
}

propagateList();