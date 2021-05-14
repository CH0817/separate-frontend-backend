async function getUserProfile() {
    // console.log(document.cookie);
    // $.each(document.cookie.split(/; */), function () {
    //     let splitCookie = this.split('=');
    //     // name is splitCookie[0], value is splitCookie[1]
    //     console.log(splitCookie[0] + ' : ' + splitCookie[1]);
    // });

    // var token = $('meta[name=\'_csrf\']').attr('content');
    // var header = $('meta[name=\'_csrf_header\']').attr('content');
    // console.log(token);
    // console.log(header);

    // console.info($.cookie('XSRF-TOKEN'));

    // $.each(document.cookie.split(/; */), function () {
    //     let splitCookie = this.split('=');
    //     // name is splitCookie[0], value is splitCookie[1]
    //     console.log(splitCookie[0]);
    //     console.log(splitCookie[1]);
    // });

    // $.ajax({
    //     url: 'http://localhost:8082/cas-backend/csrf/token',
    //     type: 'get',
    //     cache: false,
    //     xhrFields: {withCredentials: true},
    //     error: function (jqXHR) {
    //
    //     },
    //     success: function (data, textStatus, jqXHR) {
    //         console.info('success');
    //         console.info(data);
    //         console.info(textStatus);
    //         console.info(jqXHR);
    //     },
    //     complete: function (jqXHR, textStatus) {
    //         console.info('complete');
    //         console.info(jqXHR);
    //         console.info(textStatus);
    //     }
    // });

    let csrfToken = null;
    try {
        csrfToken = await getCsrfToken();
    } catch (e) {
        console.error(e);
    }

    $.ajax({
        url: 'http://localhost:8082/cas-backend/test/user/profile?_csrf=' + csrfToken,
        type: 'post',
        headers: {'X-XSRF-TOKEN': csrfToken},
        contentType: 'application/json',
        cache: false,
        data: JSON.stringify({
            'username': 'rex',
            '_csrf': csrfToken
        }),
        xhrFields: {withCredentials: true},
        // beforeSend: async function (jqXHR) {
        //     // console.log(jqXHR);
        //     // console.log(settings);
        //     // console.info('beforeSend');
        //     // console.info(jqXHR);
        //     // console.info(settings);
        //     // csrfToken = await getCsrfToken();
        //     console.log(csrfToken);
        //     jqXHR.setRequestHeader('X-XSRF-TOKEN', csrfToken);
        // },
        error: function (jqXHR, textStatus, errorThrown) {
            // console.info('error');
            // console.log(jqXHR.getAllResponseHeaders());
            // console.info(jqXHR);
            // console.info(textStatus);
            // console.info(errorThrown);
            if (jqXHR.status === 401) {
                //     let url = JSON.parse(jqXHR.responseText).url;
                //     // console.info(url);
                window.location.replace(encodeURI('https://cas.example.org:8443/cas/login?service=http://localhost:8082/cas-backend/login/cas&renew=false/'));
            }
        },
        success: function (data, textStatus, jqXHR) {
            // console.info('success');
            // console.info(data);
            // console.info(textStatus);
            // console.info(jqXHR);
            // $('#responseBody').text(data);
        },
        complete: function (jqXHR, textStatus) {
            // console.info('complete');
            // console.info(jqXHR);
            // console.info(textStatus);
        }
    });
}

function getCsrfToken() {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: 'http://localhost:8082/cas-backend/csrf/token',
            type: 'get',
            cache: false,
            xhrFields: {withCredentials: true},
            error: function () {
                reject(false);
            },
            success: function (data) {
                resolve(data);
            }
        });
    });
}

$(function () {
});