var isLoadingGuide = false;

function Sleep(milliseconds) {
    return new Promise(resolve => setTimeout(resolve, milliseconds));
}

async function showHashMessage(){
    if(window.location.hash.substr(1) !== ""){
        await Sleep(1500);
        document.getElementById("status").classList.remove("hidden");
        document.getElementById("status").classList.add("status");
        document.getElementById("statustext").innerHTML = "Switched to " + window.location.hash.substr(1).toUpperCase();
    }
}

async function insertGuide() {
    if (!isLoadingGuide) {
        isLoadingGuide = true;
        try {
            document.getElementsByClassName("loading")[0].classList.remove("hidden");
        } catch (exception)
        {
            //continue
        }
        var content;
        var xmlhttp;
        if (window.XMLHttpRequest) { // code for IE7+, Firefox, Chrome, Opera, Safari
            xmlhttp = new XMLHttpRequest();
        } else { // code for IE6, IE5
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlhttp.onreadystatechange = function () {
            if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                content = xmlhttp.responseText;
            }
        }
        xmlhttp.open("GET", "fullguide", true);
        xmlhttp.send();
        do {
            await Sleep(1000)
        } while (content == null);
        document.getElementById("fullguide").innerHTML = content;
        isLoadingGuide = false;
    }
}

document.addEventListener("DOMContentLoaded", function(event) {

    if(document.getElementById("guidecontent").innerText.length !== 0){
        document.getElementById("guide").classList.remove("hidden");
        document.getElementById("guide").classList.add("guide");
    }

    showHashMessage();

    window.addEventListener('hashchange', function() {
        showHashMessage();
    }, false);

    document.getElementById("killerbutton").addEventListener("click", function (e) {
        if (confirm('Do you really want to shut down the application and the system?')) {
            alert('Shutting down...');
        } else {
           e.preventDefault();
        }
    });

    document.getElementById("openguide").addEventListener("click", function (ev) {
        document.getElementById("fullguide").classList.remove("hidden");
        document.getElementsByClassName("blackbox")[0].classList.remove("hidden");
        insertGuide();
    });

    document.getElementById("close").addEventListener("click", function (ev) {
        document.getElementById("status").classList.add("hidden");})

    document.getElementById("refresh").addEventListener("click", function (ev) {
       document.getElementById("refreshform").submit();
    });

    document.addEventListener("click", function (ev) {
        if(ev.target.classList.contains("closeguide") || ev.target.parentElement.classList.contains("closeguide") || ev.target.classList.contains("blackbox")){
            document.getElementById("fullguide").classList.add("hidden");
            document.getElementsByClassName("blackbox")[0].classList.add("hidden");
        }
    });
});
