function alphanumeric_only(id) {
	var title = document.getElementById(id).value;
	var titlePattern = new RegExp("([{}\\(\\)\\^$&_%#!@=<>\":;,~`'\\’\\*\\?\\/\\+\\|\\[\\\\]|\\])","")
	var validPattern = new RegExp("(([a-zA-Z0-9])+(([\\s|\\-])?([a-zA-Z])*([0-9])?([\\.])?([0-9])?)?)");
	if(title !=null && title!="") {
		 if(!titlePattern.test(title) && validPattern.test(title)) {
			return true;
		} else {
			alert("Sorry You can not insert Special Character");
			document.getElementById(id).value = "";
			return false;
		}
	} return false;
}

function updateQueryString(s,param,value){
	var url = document.URL;
	var newAdditionalURL = "";
	var tempArray = url.split("?");
	var baseURL = tempArray[0];
	var regularepression = /save$/;
	baseURL = baseURL.replace(regularepression, 'list');
	var aditionalURL = tempArray[1]; 
	var temp = "";
	if(aditionalURL)
	{
	var tempArray = aditionalURL.split("&");
	for ( var i in tempArray ){
	    if(tempArray[i].indexOf(param) == -1){
	            newAdditionalURL += temp+tempArray[i];
	                temp = "&";
	            }
	        }
	}
	var rows_txt = temp+param+"="+value;
	var finalURL = baseURL+"?"+newAdditionalURL+rows_txt;
	return finalURL;
}