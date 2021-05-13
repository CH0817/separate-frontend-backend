function getUserProfile() {
    let _csrf = $.cookie('XSRF-TOKEN');
    let _csrf2 = $.cookie('JSESSIONID');
    console.log(_csrf);
    console.log(_csrf2);
    $.ajax({
        url: 'http://localhost:8082/cas-backend/test/user/profile/',
        type: 'post',
        contentType: 'application/json',
        cache: false,
        data: JSON.stringify({'username': 'rex'}),
        xhrFields: {withCredentials: true},
        error: function (jqXHR) {
            console.info(jqXHR.status);
            if (jqXHR.status === 401) {
                let url = JSON.parse(jqXHR.responseText).url;
                console.info(url);
                window.location.replace(encodeURI('https://cas.example.org:8443/cas/login?service=http://localhost:8082/cas-backend/login/cas&renew=false/'));
                // window.location.href = 'https://cas.example.org:8443/cas/login?service=http://localhost:8081'; //+ encodeURI(url);
                // window.location.href = 'https://cas.example.org:8443/cas/login?service=http://localhost:8082/cas-backend/login/cas&renew=true'; //+ encodeURI(url);
            }
        },
        success: function (data, textStatus, jqXHR) {
            console.info(data);
            console.info(textStatus);
            console.info(jqXHR);
            $('#responseBody').text(data);
        }
    });
}

function verifyTicket(ticket) {

}

$(function () {
    let urlParams = new URLSearchParams(window.location.search);
    if (urlParams.has('ticket')) {
        verifyTicket(urlParams.get('ticket'));
    }
});