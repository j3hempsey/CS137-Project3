var QUERY_LEN_THRESH = 1;
//Autofill zipcode.
$(document).ready(function() {
    $("#zip-code").keyup(function() {
        var query_val = $("#zip-code").val();
            if(query_val.length >= QUERY_LEN_THRESH) {
                var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function() {
                    if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                        document.getElementById("zip-results").innerHTML = xmlhttp.responseText;
                    }
                };
                xmlhttp.open("GET", "../php/auto-complete-zip.php?q=" + query_val, true);
                xmlhttp.send();
            }
    });
});          
//Autocomplete state.
$(document).ready(function() {
    $("#state").keyup(function() {
        var query_val = $("#state").val();
            if(query_val.length >= QUERY_LEN_THRESH) { //Dont autocomplete unless user hasnt entered anything.
                var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function() {
                    if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                        document.getElementById("state-results").innerHTML = xmlhttp.responseText;
                    }
                };
                xmlhttp.open("GET", "../php/auto-complete-state.php?q=" + query_val, true);
                xmlhttp.send();
            }
    });
});     
     
//Autofill state
$(document).ready(function() {
    $("#zip-code").keyup( function() { //Aka the user has unselected the zip-code field.
		var zip_val = $("#zip-code").val()
		if(checkZip(zip_val))
		{
			var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function() {
                    if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
						document.getElementById("state").value = xmlhttp.responseText;
						checkStateWhileTyping();
					}
				};
				xmlhttp.open("GET", "../php/auto-fill-state.php?q=" + zip_val, true);
                xmlhttp.send();
		}
	});
});
