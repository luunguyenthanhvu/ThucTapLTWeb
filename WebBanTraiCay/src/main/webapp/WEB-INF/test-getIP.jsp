<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 15/06/2024
  Time: 4:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>IP Location Lookup</title>
</head>
<body>
<h1>IP Location Lookup</h1>
<form id="ipForm">
    <label for="ipAddress">IP Address:</label>
    <input type="text" id="ipAddress" name="ipAddress" placeholder="Enter IP Address">
    <button type="button" onclick="getLocation()">Get Location</button>
</form>

<div id="locationInfo"></div>

<script>
  function getLocation() {
    var ipAddress = document.getElementById("ipAddress").value;
    var apiKey = "2f4d5b327fe0e99345b42746350bb364";
    var url = "http://api.ipstack.com/" + ipAddress + "?access_key=" + apiKey;

    var xhr = new XMLHttpRequest();
    xhr.open("GET", url, true);
    xhr.onreadystatechange = function () {
      if (xhr.readyState === 4 && xhr.status === 200) {
        var response = JSON.parse(xhr.responseText);
        displayLocation(response);
      }
    };
    xhr.send();
  }

  function displayLocation(data) {
    var locationInfo = document.getElementById("locationInfo");
    locationInfo.innerHTML = "<h2>IP Location Information</h2>" +
        "<p>IP Address: " + data.ip + "</p>" +
        "<p>Country: " + data.country_name + "</p>" +
        "<p>Region: " + data.region_name + "</p>" +
        "<p>City: " + data.city + "</p>" +
        "<p>Latitude: " + data.latitude + "</p>" +
        "<p>Longitude: " + data.longitude + "</p>";
  }
</script>
</body>
</html>

