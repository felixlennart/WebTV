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

document.addEventListener("DOMContentLoaded", function(event) {

    showHashMessage();

    window.addEventListener('hashchange', function() {
        showHashMessage();
    }, false);

    document.getElementById("killerbutton").addEventListener("click", function (e) {
        if (confirm('Do you really want to shut down the application?')) {
            alert('Shutting down...');
        } else {
           e.preventDefault();
        }
    });

    document.getElementById("close").addEventListener("click", function (ev) {
        document.getElementById("status").classList.add("hidden");})
});

