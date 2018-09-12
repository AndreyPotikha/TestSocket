var stompClient = null;
var timeResponse = 0;
var res = [];
var lengthOfArr = 0;

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
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {

        stompClient.send("/app/hello", {}, JSON.stringify({
            'name': $("#name").val(),
            'word': $("#word").val()
        }));
}

function showGreeting(message) {

    res = [];
    var i = 0;
    var iter = 0;

    message.forEach(function(value, item) {

        var tempCheck = true;
        var lengthOfTr = $('#greetings').children('tr').length;
        for (i; i < message.length; i++) {
            res.push($('.site-table').children('#site-name' + i).text());
        }

        res.forEach(function (value1) {

            if (value1 === value.siteName) {

                    $('#site-name' + iter).text(value.siteName);
                    $('#server-status' + iter).text(value.serverStatus);
                    $('#response-time' + iter).text(value.time);
                    $('#length-server' + iter).text(value.lengthServerStatus);
                    $('#word' + iter).text(value.word);

                iter++;
                tempCheck = false;
            }
        });

        if (tempCheck && value !== null && value !== undefined) {
            $("#greetings").append("<tr class='site-table' id='site-table'><td id='site-name'>" + value.siteName
                + "</td><td id='server-status'>" + value.serverStatus
                + "</td><td id='response-time'>" + value.time
                + "</td><<td id='length-server'>" + value.lengthServerStatus
                + "</td><<td id='word'>" + value.word + "</td></tr>");

            $('#site-name').attr('id', 'site-name' + timeResponse);
            $('#server-status').attr('id', 'server-status' + timeResponse);
            $('#response-time').attr('id', 'response-time' + timeResponse);
            $('#length-server').attr('id', 'length-server' + timeResponse);
            $('#word').attr('id', 'word' + timeResponse);
            $('#site-table').attr('id', 'site-table' + timeResponse);

            var tempTime = +$('#response-time' + timeResponse).text();
            if (tempTime > 45) {
                $('#response-time' + timeResponse).css('background-color', 'red');
            }

            timeResponse++;
        }

    });

}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});