var ee = new EventEmitter();
var eventName = "create-user";
var onceEvent = "once";

ee.defineEvent(eventName);
ee.addListener(eventName, result);
ee.addOnceListener(onceEvent, onceDeal);

function emitCreateUser() {
     ee.emitEvent(eventName, ["{name:liujie}"]);
}

function emitOnce() {
    ee.emitEvent(onceEvent);
}

function result(name) {
    console.log("receive create user event : " + name);
    $("#result").append(name + "<br/>");
}

function removeEvent() {
    console.log("remove enent!!");
    ee.removeEvent(eventName);
}

function addEvent() {
    console.log("add enent!!");
    ee.addListener(eventName, result);
}

function onceDeal() {
    console.log("receive once user event : ");
    $("#result").append("once event" + "<br/>");
}

$(function () {
    $("#emitEvent").bind("click", emitCreateUser);
    $("#onceEvent").bind("click", emitOnce);
    $("#removeEvent").bind("click", removeEvent);
    $("#addEvent").bind("click", addEvent);
});