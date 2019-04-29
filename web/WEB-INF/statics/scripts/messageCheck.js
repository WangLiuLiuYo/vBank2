function $_(id) {
    return document.getElementById(id);
}
unveristy_pat=/[A-Z]{3,20}/g;
money_pat=/\d{1,15}\.\d{0,2}/g;
cardid_pat=/\d{20}/g;
uid_pat=/\d{18}/g;
valid_username_pat=/[A-Za-z][a-zA-Z0-9_]{1,29}/g;
valid_mail_pat=/[a-zA-Z0-9]{1,15}@[a-zA-Z0-9]{2,6}/g;

function check(element_id,pat) {
    var text=$(element_id).value;
    if(pat.test(text)){
        alert(element_id+" is not valid.");
        $(element_id).value="";
    }
}

