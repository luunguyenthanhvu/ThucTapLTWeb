<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 27/01/2024
  Time: 4:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>404 HTML Template by Colorlib</title>

    <style id="" media="all">/* vietnamese */
    @font-face {
        font-family: 'Cabin';
        font-style: normal;
        font-weight: 400;
        font-stretch: 100%;
        font-display: swap;
        src: url(/fonts.gstatic.com/s/cabin/v27/u-4i0qWljRw-PfU81xCKCpdpbgZJl6XvptnsBXw.woff2) format('woff2');
        unicode-range: U+0102-0103, U+0110-0111, U+0128-0129, U+0168-0169, U+01A0-01A1, U+01AF-01B0, U+0300-0301, U+0303-0304, U+0308-0309, U+0323, U+0329, U+1EA0-1EF9, U+20AB;
    }
    /* latin-ext */
    @font-face {
        font-family: 'Cabin';
        font-style: normal;
        font-weight: 400;
        font-stretch: 100%;
        font-display: swap;
        src: url(/fonts.gstatic.com/s/cabin/v27/u-4i0qWljRw-PfU81xCKCpdpbgZJl6Xvp9nsBXw.woff2) format('woff2');
        unicode-range: U+0100-02AF, U+0304, U+0308, U+0329, U+1E00-1E9F, U+1EF2-1EFF, U+2020, U+20A0-20AB, U+20AD-20CF, U+2113, U+2C60-2C7F, U+A720-A7FF;
    }
    /* latin */
    @font-face {
        font-family: 'Cabin';
        font-style: normal;
        font-weight: 400;
        font-stretch: 100%;
        font-display: swap;
        src: url(/fonts.gstatic.com/s/cabin/v27/u-4i0qWljRw-PfU81xCKCpdpbgZJl6Xvqdns.woff2) format('woff2');
        unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+0304, U+0308, U+0329, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;
    }
    /* vietnamese */
    @font-face {
        font-family: 'Cabin';
        font-style: normal;
        font-weight: 700;
        font-stretch: 100%;
        font-display: swap;
        src: url(/fonts.gstatic.com/s/cabin/v27/u-4i0qWljRw-PfU81xCKCpdpbgZJl6XvptnsBXw.woff2) format('woff2');
        unicode-range: U+0102-0103, U+0110-0111, U+0128-0129, U+0168-0169, U+01A0-01A1, U+01AF-01B0, U+0300-0301, U+0303-0304, U+0308-0309, U+0323, U+0329, U+1EA0-1EF9, U+20AB;
    }
    /* latin-ext */
    @font-face {
        font-family: 'Cabin';
        font-style: normal;
        font-weight: 700;
        font-stretch: 100%;
        font-display: swap;
        src: url(/fonts.gstatic.com/s/cabin/v27/u-4i0qWljRw-PfU81xCKCpdpbgZJl6Xvp9nsBXw.woff2) format('woff2');
        unicode-range: U+0100-02AF, U+0304, U+0308, U+0329, U+1E00-1E9F, U+1EF2-1EFF, U+2020, U+20A0-20AB, U+20AD-20CF, U+2113, U+2C60-2C7F, U+A720-A7FF;
    }
    /* latin */
    @font-face {
        font-family: 'Cabin';
        font-style: normal;
        font-weight: 700;
        font-stretch: 100%;
        font-display: swap;
        src: url(/fonts.gstatic.com/s/cabin/v27/u-4i0qWljRw-PfU81xCKCpdpbgZJl6Xvqdns.woff2) format('woff2');
        unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+0304, U+0308, U+0329, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;
    }
    </style>
    <style id="" media="all">/* cyrillic-ext */
    @font-face {
        font-family: 'Montserrat';
        font-style: normal;
        font-weight: 900;
        font-display: swap;
        src: url(/fonts.gstatic.com/s/montserrat/v26/JTUHjIg1_i6t8kCHKm4532VJOt5-QNFgpCvC73w0aXpsog.woff2) format('woff2');
        unicode-range: U+0460-052F, U+1C80-1C88, U+20B4, U+2DE0-2DFF, U+A640-A69F, U+FE2E-FE2F;
    }
    /* cyrillic */
    @font-face {
        font-family: 'Montserrat';
        font-style: normal;
        font-weight: 900;
        font-display: swap;
        src: url(/fonts.gstatic.com/s/montserrat/v26/JTUHjIg1_i6t8kCHKm4532VJOt5-QNFgpCvC73w9aXpsog.woff2) format('woff2');
        unicode-range: U+0301, U+0400-045F, U+0490-0491, U+04B0-04B1, U+2116;
    }
    /* vietnamese */
    @font-face {
        font-family: 'Montserrat';
        font-style: normal;
        font-weight: 900;
        font-display: swap;
        src: url(/fonts.gstatic.com/s/montserrat/v26/JTUHjIg1_i6t8kCHKm4532VJOt5-QNFgpCvC73w2aXpsog.woff2) format('woff2');
        unicode-range: U+0102-0103, U+0110-0111, U+0128-0129, U+0168-0169, U+01A0-01A1, U+01AF-01B0, U+0300-0301, U+0303-0304, U+0308-0309, U+0323, U+0329, U+1EA0-1EF9, U+20AB;
    }
    /* latin-ext */
    @font-face {
        font-family: 'Montserrat';
        font-style: normal;
        font-weight: 900;
        font-display: swap;
        src: url(/fonts.gstatic.com/s/montserrat/v26/JTUHjIg1_i6t8kCHKm4532VJOt5-QNFgpCvC73w3aXpsog.woff2) format('woff2');
        unicode-range: U+0100-02AF, U+0304, U+0308, U+0329, U+1E00-1E9F, U+1EF2-1EFF, U+2020, U+20A0-20AB, U+20AD-20CF, U+2113, U+2C60-2C7F, U+A720-A7FF;
    }
    /* latin */
    @font-face {
        font-family: 'Montserrat';
        font-style: normal;
        font-weight: 900;
        font-display: swap;
        src: url(/fonts.gstatic.com/s/montserrat/v26/JTUHjIg1_i6t8kCHKm4532VJOt5-QNFgpCvC73w5aXo.woff2) format('woff2');
        unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+0304, U+0308, U+0329, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;
    }
    </style>

    <style>
        * {
            -webkit-box-sizing: border-box;
            box-sizing: border-box
        }

        body {
            padding: 0;
            margin: 0
        }

        #notfound {
            position: relative;
            height: 100vh
        }

        #notfound .notfound {
            position: absolute;
            left: 50%;
            top: 50%;
            -webkit-transform: translate(-50%,-50%);
            -ms-transform: translate(-50%,-50%);
            transform: translate(-50%,-50%)
        }

        .notfound {
            max-width: 520px;
            width: 100%;
            line-height: 1.4;
            text-align: center
        }

        .notfound .notfound-404 {
            position: relative;
            height: 240px
        }

        .notfound .notfound-404 h1 {
            font-family: montserrat,sans-serif;
            position: absolute;
            left: 50%;
            top: 50%;
            -webkit-transform: translate(-50%,-50%);
            -ms-transform: translate(-50%,-50%);
            transform: translate(-50%,-50%);
            font-size: 252px;
            font-weight: 900;
            margin: 0;
            color: #262626;
            text-transform: uppercase;
            letter-spacing: -40px;
            margin-left: -20px
        }

        .notfound .notfound-404 h1>span {
            text-shadow: -8px 0 0 #fff
        }

        .notfound .notfound-404 h3 {
            font-family: cabin,sans-serif;
            position: relative;
            font-size: 16px;
            font-weight: 700;
            text-transform: uppercase;
            color: #262626;
            margin: 0;
            letter-spacing: 3px;
            padding-left: 6px
        }

        .notfound h2 {
            font-family: cabin,sans-serif;
            font-size: 20px;
            font-weight: 400;
            text-transform: uppercase;
            color: #000;
            margin-top: 0;
            margin-bottom: 25px
        }

        @media only screen and (max-width: 767px) {
            .notfound .notfound-404 {
                height:200px
            }

            .notfound .notfound-404 h1 {
                font-size: 200px
            }
        }

        @media only screen and (max-width: 480px) {
            .notfound .notfound-404 {
                height:162px
            }

            .notfound .notfound-404 h1 {
                font-size: 162px;
                height: 150px;
                line-height: 162px
            }

            .notfound h2 {
                font-size: 16px
            }
        }

    </style>


    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <meta name="robots" content="noindex, follow">
    <script nonce="e162acb3-339a-4f5d-9201-6409410c5e84">try { (function(w,d){!function(b$,ca,cb,cc){b$[cb]=b$[cb]||{};b$[cb].executed=[];b$.zaraz={deferred:[],listeners:[]};b$.zaraz.q=[];b$.zaraz._f=function(cd){return async function(){var ce=Array.prototype.slice.call(arguments);b$.zaraz.q.push({m:cd,a:ce})}};for(const cf of["track","set","debug"])b$.zaraz[cf]=b$.zaraz._f(cf);b$.zaraz.init=()=>{var cg=ca.getElementsByTagName(cc)[0],ch=ca.createElement(cc),ci=ca.getElementsByTagName("title")[0];ci&&(b$[cb].t=ca.getElementsByTagName("title")[0].text);b$[cb].x=Math.random();b$[cb].w=b$.screen.width;b$[cb].h=b$.screen.height;b$[cb].j=b$.innerHeight;b$[cb].e=b$.innerWidth;b$[cb].l=b$.location.href;b$[cb].r=ca.referrer;b$[cb].k=b$.screen.colorDepth;b$[cb].n=ca.characterSet;b$[cb].o=(new Date).getTimezoneOffset();if(b$.dataLayer)for(const cm of Object.entries(Object.entries(dataLayer).reduce(((cn,co)=>({...cn[1],...co[1]})),{})))zaraz.set(cm[0],cm[1],{scope:"page"});b$[cb].q=[];for(;b$.zaraz.q.length;){const cp=b$.zaraz.q.shift();b$[cb].q.push(cp)}ch.defer=!0;for(const cq of[localStorage,sessionStorage])Object.keys(cq||{}).filter((cs=>cs.startsWith("_zaraz_"))).forEach((cr=>{try{b$[cb]["z_"+cr.slice(7)]=JSON.parse(cq.getItem(cr))}catch{b$[cb]["z_"+cr.slice(7)]=cq.getItem(cr)}}));ch.referrerPolicy="origin";ch.src="/cdn-cgi/zaraz/s.js?z="+btoa(encodeURIComponent(JSON.stringify(b$[cb])));cg.parentNode.insertBefore(ch,cg)};["complete","interactive"].includes(ca.readyState)?zaraz.init():b$.addEventListener("DOMContentLoaded",zaraz.init)}(w,d,"zarazData","script");})(window,document) } catch (err) {
        console.error('Failed to run Cloudflare Zaraz: ', err)
        fetch('/cdn-cgi/zaraz/t', {
            credentials: 'include',
            keepalive: true,
            method: 'GET',
        })
    };</script></head>
<body>
<div id="notfound">
    <div class="notfound">
        <div class="notfound-404">
            <h3>Trang không tìm thấy</h3>
            <h1><span>4</span><span>0</span><span>4</span></h1>
        </div>
        <h2>chúng tôi xin lỗi, nhưng trang bạn yêu cầu không được tìm thấy</h2>
    </div>
</div>

<script async src="https://www.googletagmanager.com/gtag/js?id=UA-23581568-13"></script>
<script>
    window.dataLayer = window.dataLayer || [];
    function gtag(){dataLayer.push(arguments);}
    gtag('js', new Date());

    gtag('config', 'UA-23581568-13');
</script>
<script defer src="https://static.cloudflareinsights.com/beacon.min.js/v84a3a4012de94ce1a686ba8c167c359c1696973893317" integrity="sha512-euoFGowhlaLqXsPWQ48qSkBSCFs3DPRyiwVu3FjR96cMPx+Fr+gpWRhIafcHwqwCqWS42RZhIudOvEI+Ckf6MA==" data-cf-beacon='{"rayId":"84bbf5d7cba740e9","version":"2024.1.0","token":"cd0b4b3a733644fc843ef0b185f98241"}' crossorigin="anonymous"></script>
</body>
</html>

