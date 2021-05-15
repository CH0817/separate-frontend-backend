$.ajaxSetup({
    contentType: 'application/json',
    // 允許請求帶有驗證訊息
    xhrFields: {withCredentials: true},
    error: function (jqXHR) {
        // 401 未認證，轉至 CAS，service 必須是後端 spring security 的 CAS login URL，renew 不太懂是什麼意思
        if (jqXHR.status === 401) {
            window.location.replace(encodeURI('https://cas.example.org:8443/cas/login?service=http://localhost:8082/backend/login/cas&renew=false/'));
        }
    }
});

async function getUserProfile() {
    // fixme CSRF token 取得方式再改進
    let csrfToken = null;
    try {
        csrfToken = await getCsrfToken();
    } catch (e) {
        console.error(e);
    }

    $.ajax({
        url: 'http://localhost:8082/backend/test/user/profile',
        type: 'post',
        headers: {'X-CSRF-TOKEN': csrfToken},
        cache: false,
        data: JSON.stringify({'username': 'rex'}),
        success: function (data) {
            $('#responseBody').text(data);
        }
    });
}

function getCsrfToken() {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: 'http://localhost:8082/backend/csrf/token',
            type: 'get',
            cache: false,
            error: function () {
                reject(false);
            },
            success: function (data) {
                resolve(data);
            }
        });
    });
}