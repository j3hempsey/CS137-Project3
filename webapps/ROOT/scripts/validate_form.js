//Timer in case user uses autofill or some other weird format.
function updateChecks() 
{
    if(!isDocumentElementEmpty("first-name"))
    {
        checkFirstNameWhileTyping();
    }
    if(!isDocumentElementEmpty("last-name"))
    {
    	checkLastNameWhileTyping();
    }
	if(!isDocumentElementEmpty("credit-card"))
    {
		checkCreditCardWhileTyping();
	}
	if(!isDocumentElementEmpty("address"))
    {
		checkAddressWhileTyping();
	}
	if(!isDocumentElementEmpty("zip-code"))
    {
		checkZipCodeWhileTyping();
	}
	if(!isDocumentElementEmpty("state"))
    {
		checkStateWhileTyping();
	}
	if(!isDocumentElementEmpty("quantity"))
    {
		checkQuantityWhileTyping();
	}
	if(!isDocumentElementEmpty("phone"))
    {
		checkPhoneWhileTyping();
	}
}

var run = setInterval(function() { updateChecks() }, 500); //Set interval.

function isDocumentElementEmpty(name)
{
    if(document.getElementById(name).value == "")
        return true;
    else
        return false;
}

//Input listeners.
function checkFirstNameWhileTyping()
{
  if(checkName(document.getElementById("first-name").value)) {
	$("#first-name-good").addClass('hidden');
	$("#first-name-bad").removeClass('hidden');
  }
  else {
	$("#first-name-good").removeClass('hidden');
	$("#first-name-bad").addClass('hidden');
  }
}

function checkLastNameWhileTyping()
{
  if(checkName(document.getElementById("last-name").value)) {
  	$("#last-name-good").addClass('hidden');
	$("#last-name-bad").removeClass('hidden');
  }
  else {
    $("#last-name-good").removeClass('hidden');
	$("#last-name-bad").addClass('hidden');
  }
}

function checkCreditCardWhileTyping()
{
  if(checkCard(document.getElementById("credit-card").value)) {
  	$("#credit-card-good").addClass('hidden');
	$("#credit-card-bad").removeClass('hidden');
  }
  else {
    $("#credit-card-good").removeClass('hidden');
	$("#credit-card-bad").addClass('hidden');
  }
}

function checkAddressWhileTyping()
{
  if(checkAddress(document.getElementById("address").value)) {
  	$("#address-good").addClass('hidden');
	$("#address-bad").removeClass('hidden');
  }
  else {
    $("#address-good").removeClass('hidden');
	$("#address-bad").addClass('hidden');
  }
}

function checkPhoneWhileTyping()
{
  if(checkPhone(document.getElementById("phone").value)) {
  	$("#phone-good").addClass('hidden');
	$("#phone-bad").removeClass('hidden');
  }
  else {
    $("#phone-good").removeClass('hidden');
	$("#phone-bad").addClass('hidden');
  }
}

function checkZipCodeWhileTyping()
{
  if(checkZip(document.getElementById("zip-code").value)) {
  	$("#zip-code-good").addClass('hidden');
	$("#zip-code-bad").removeClass('hidden');
  }
  else {
    $("#zip-code-good").removeClass('hidden');
	$("#zip-code-bad").addClass('hidden');
  }
}

function checkStateWhileTyping()
{
  if(checkState(document.getElementById("state").value)) {
    $("#state-good").addClass('hidden');
	$("#state-bad").removeClass('hidden');
  }
  else {
    $("#state-good").removeClass('hidden');
	$("#state-bad").addClass('hidden');
  }
}

function checkQuantityWhileTyping()
{
  if(checkQuantity(document.getElementById("quantity").value)) {
  	$("#quantity-good").addClass('hidden');
	$("#quantity-bad").removeClass('hidden');
  }
  else {
    $("#quantity-good").removeClass('hidden');
	$("#quantity-bad").addClass('hidden');
  }
}

//Input Regex.
function checkEmail(email)
{
	var regex = /^([a-zA-Z0-9_.-])+@(a-zA-Z0-9_.-)+\.(a-zA-Z])/;
	return regex.test(email);
}

function checkName(name)
{
	var regex = /^([a-zA-Z])/;
	return regex.test(name);
}

function checkCard(creditCard)
{
	var regex = /^([0-9])/;
	return (regex.test(creditCard) && creditCard.length == 16);
}

function checkPhone(phoneNumber)
{
	var regex = /^([0-9\--\-])/;
	return (regex.test(phoneNumber) && (phoneNumber.length == 10 || phoneNumber.length == 12));
}

function checkAddress(address)
{
	var regex = /^([0-9])+ +([a-zA-Z])*/;
	return regex.test(address);
}

function checkZip(zip)
{
	var regex = /^([0-9])/;
	return (regex.test(zip) && zip.length < 6); //Turns out there are shorter zip codes in US.
}

function checkState(state)
{
	var regex = /^([a-zA-Z])/;
	return (regex.test(state) && state.length == 2);
}

function checkQuantity(quantity)
{
	var regex = /^([0-9])/;
	return regex.test(quantity);
}

//Main Check.
function processForm()
{
	var first_name = document.getElementById("first-name");
	var last_name = document.getElementById("last-name");
	var credit_card = document.getElementById("credit-card");
	var address = document.getElementById("address");
	var zip = document.getElementById("zip-code");
	var state = document.getElementById("state");
	var quantity = document.getElementById("quantity");
	var phoneNumber = document.getElementById("phone");
	var error_message = "Errors in the following fields: \n\n";
	var error = false;
	if(!checkName(first_name.value))
	{
		error_message += "First name\n";
		error = true;
	}
	if(!checkName(last_name.value))
	{
		error_message += "Last name\n";
		error = true;
	}
	if(!checkCard(credit_card.value))
	{
		error_message += "Credit Card\n";
		if(credit_card.length < 16) {
			error_message += " Please enter at least 16 digits.\n";
		}
		error = true;
	}
	if(!checkAddress(address.value))
	{
		error_message += "Address\n";
		error = true;
	}
	if(!checkZip(zip.value))
	{
		error_message += "Zip\n";
		error = true;
	}
	if(!checkState(state.value))
	{
		error_message += "State\n";
		error = true;
	}
	if(!checkQuantity(quantity.value))
	{
		error_message += "Quantity\n";
		error = true;
	}
	if(!checkPhone(phoneNumber.value))
	{
		error_message += "Phone number, formatted xxx-xxx-xxxx or 0000000000\n";
		error = true;
	}
	if(error)
	{
		alert(error_message);
        return false;
	}
	
	var data = $('#order-form').serialize();
	$.ajax({
  type: "POST",
  url: "/php/form.php",
  data: data,
  success: function(id){
			if(id != -1){
				window.location.href = "/php/confirm.php?id=" + id;
			} else {
			}
		},
});

return false;
 /* Keeping this as reference in case php needs it.	
	var pepperName = document.getElementById("pepper-name");
	window.location.href = "mailto:peterspepper@peterspepper.com?subject=" + "My Pepper Order" + 
	"&body=Get me " + quantity.value + " " + pepperName.innerHTML + "!%0A%0A Send To: %0A" + first_name.value + " " + last_name.value + "%0A" + address.value + "%0A" + state.value + " " + zip.value;
	$('#order-form').submit();
    */

}

var form = $("#order-form");
form.onSubmit = processForm;
