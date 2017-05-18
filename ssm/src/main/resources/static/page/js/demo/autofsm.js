Auto = function() {

    var output = document.getElementById('autooutput'),
        demo   = document.getElementById('autodemo'),
        panic  = document.getElementById('autopanic'),
        warn   = document.getElementById('autowarn'),
        calm   = document.getElementById('autocalm'),
        clear  = document.getElementById('autoclear'),
        count  = 0,
        redLightTime = 13*1000,
        redLightTimeout,
        greentLightTime = 21*1000,
        greenLightTimeout,
        yellowLightTime = 3*1000,
        yellowLightTimeout;

    var log = function(msg, separate) {
        count = count + (separate ? 1 : 0);
        output.value = count + ": " + msg + "\n" + (separate ? "\n" : "") + output.value;
        demo.className = fsm.current;
        panic.disabled = fsm.cannot('panic');
        warn.disabled  = fsm.cannot('warn');
        calm.disabled  = fsm.cannot('calm');
        clear.disabled = fsm.cannot('clear');
    };

    var fsm = StateMachine.create({

        events: [
            { name: 'start', from: 'none',   to: 'green'  },
            { name: 'warn',  from: 'green',  to: 'yellow' },
            { name: 'panic', from: 'green',  to: 'red'    },
            { name: 'panic', from: 'yellow', to: 'red'    },
            { name: 'calm',  from: 'red',    to: 'yellow' },
            { name: 'clear', from: 'red',    to: 'green'  },
            { name: 'clear', from: 'yellow', to: 'green'  },
        ],

        callbacks: {
            onbeforestart: function(event, from, to) { log("STARTING UP"); },
            onstart:       function(event, from, to) { log("READY");       },

            onbeforewarn:  function(event, from, to) { log("START   EVENT: warn!",  true);  },
            onbeforepanic: function(event, from, to) { log("START   EVENT: panic!", true);  },
            onbeforecalm:  function(event, from, to) { log("START   EVENT: calm!",  true);  },
            onbeforeclear: function(event, from, to) { log("START   EVENT: clear!", true);  },

            onwarn:        function(event, from, to) { log("FINISH  EVENT: warn!");         },
            onpanic:       function(event, from, to) { log("FINISH  EVENT: panic!");        },
            oncalm:        function(event, from, to) { log("FINISH  EVENT: calm!");         },
            onclear:       function(event, from, to) { log("FINISH  EVENT: clear!");        },

            onleavegreen:  function(event, from, to) { log("LEAVE   STATE: green");  },
            onleaveyellow: function(event, from, to) { log("LEAVE   STATE: yellow"); },
            onleavered:    function(event, from, to) { log("LEAVE   STATE: red"); },

            ongreen:       function(event, from, to) {
                                log("ENTER   STATE: green");
                                greenLightTimeout = setTimeout(function() {
                                    log("Green Light Timeout, Transfer to Yellow Light!");
                                    fsm.warn();
                                }, greentLightTime);
                                clearOtherTimeout("green");
                            },
            onyellow:      function(event, from, to) {
                                log("ENTER   STATE: yellow");
                                yellowLightTimeout = setTimeout(function() {
                                    log("Yellow Light Timeout, Transfer to Red Light!");
                                    fsm.panic();
                                }, yellowLightTime);
                                clearOtherTimeout("yellow");
                            },

            onred:         function(event, from, to) {
                                log("ENTER   STATE: red");
                                redLightTimeout = setTimeout(function() {
                                    log("Red Light Timeout, Transfer to Green Light!");
                                    fsm.clear();
                                }, redLightTime);
                                clearOtherTimeout("red");
                            },

            onchangestate: function(event, from, to) {
                log("CHANGED STATE: " + from + " to " + to);
            }
        },

        error: function(eventName, from, to, args, errorCode, errorMessage, originalException) {
            return 'event ' + eventName + ' was naughty :- ' + errorMessage;
        }
    });

    var clearOtherTimeout = function(light) {
        switch(light) {
            case "green":
                if(redLightTimeout) {
                    clearTimeout(redLightTimeout);
                }
                if(yellowLightTimeout) {
                    clearTimeout(yellowLightTimeout);
                }
                break;
            case "red":
                if(greenLightTimeout) {
                    clearTimeout(greenLightTimeout);
                }
                if(yellowLightTimeout) {
                    clearTimeout(yellowLightTimeout);
                }
                break;
            case "yellow":
                if(redLightTimeout) {
                    clearTimeout(redLightTimeout);
                }
                if(greenLightTimeout) {
                    clearTimeout(greenLightTimeout);
                }
            default:
                console.log('Invalid State!');
                break;
        }
    };

    fsm.start();
    return fsm;

}();