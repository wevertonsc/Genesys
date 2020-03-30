/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
 * Genesys Assessment 
 * App: Connect Five
 * Candidate: Weverton de Souza Castanho
 * Date: March 29th, 2020
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
 */

var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/connectfive', function (greeting) {
            showDashboard(JSON.parse(greeting.body).content);
        });
    });
}

function disconnect() {
    stompClient.send("/app/disconnect", {}, JSON.stringify({'name': $("#name").val()}));
    stompClient.subscribe('/topic/connectfive', function (greeting) {
        showDashboard(JSON.parse(greeting.body).content);
    });
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
    $("#name").prop("disabled",true);
}

function sendPlay() {
	stompClient.send("/app/play", {}, JSON.stringify({'position': $("#position").val(), 'name': $("#name").val()}));
}

function showDashboard(message) {
	$("#dashboard").empty();
    $("#dashboard").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
    $( "#play" ).click(function() { sendPlay(); });
});

