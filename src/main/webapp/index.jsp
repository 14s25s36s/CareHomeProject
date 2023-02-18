<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        html,
        body {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        #captchabox {
            position: relative;
            margin: 0 auto;
            width: 260px;
            height: 45px;
        }

        .input {
            margin: 0;
            padding: 0;
            width: 100%;
            height: 100%;
        }

        #captcha {
            position: absolute;
            display: inline-block;
            margin-left: 60%;
            width: 90px;
            height: 47px;
            transform: translate(17px, -48px);
            border-radius: 4px;
            line-height: 40px;
            text-align: center;
            vertical-align: middle;
            background-color: #ddd;
            cursor: pointer;
        }
    </style>
</head>

<body>
<div id="captchabox">
    <input id="codetext" type="text" class="input" maxlength="4" required>
    <div id="captcha"></div>
</div>

</body>
<script>
    /* 验证码的js */
    var div = document.querySelector('#captcha');
    var characters = "QWETYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890";
    var str;

    function getRandom(l, r) {
        return parseInt(l + Math.random() * (r - l + 1));
    }

    function run() {
        str = '';
        while (div.hasChildNodes()) {
            div.removeChild(div.firstChild);
        }
        for (var i = 0; i < 4; i++) {
            var span = document.createElement('span');
            span.innerHTML = characters[getRandom(0, characters.length - 1)];
            span.style.display = 'inline-block';
            span.style.fontSize = getRandom(16, 32) + 'px';
            span.style.color = 'rgb(' + getRandom(0, 200) + ',' + getRandom(0, 200) + ',' + getRandom(0, 200) + ')';
            span.style.transform = 'translate(' + getRandom(-5, 5) + 'px, ' + getRandom(-5, 5) + 'px) rotate(' + getRandom(-20, 20) + 'deg)';
            str += span.innerHTML;
            div.appendChild(span);
        }
    }

    run(); //每次点击都刷新
    div.addEventListener('click', run);
</script>
</html>