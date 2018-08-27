/**
 * Created by xiangshiquan on 2017/4/29.
 */
var mpush = (function () {
    var socket, session = {}, ID_SEQ = 1;
    var mpushConfig = {};
    const Command = {
        HANDSHAKE: 2,
        BIND: 5,
        UNBIND: 6,
        ERROR: 10,
        OK: 11,
        KICK: 13,
        PUSH: 15,
        ACK: 23,
        UNKNOWN: -1
    };

    //私有方法，外面将访问不到
    function Packet(cmd, body, sessionId) {
        return {
            cmd: cmd,
            flags: 16,
            sessionId: sessionId || ID_SEQ++,
            body: body
        }
    }

    function handshake() {
        send(Packet(Command.HANDSHAKE, {
                deviceId: mpushConfig.deviceId,
                osName: mpushConfig.osName,
                osVersion: mpushConfig.osVersion,
                clientVersion: mpushConfig.clientVersion
            })
        );
    }

    function bindUser(userId, tags) {
        if (userId && userId != session.userId) {
            session.userId = userId;
            session.tags = tags;
            send(Packet(Command.BIND, {userId: userId, tags: tags}));
        }
    }

    function ack(sessionId) {
        send(Packet(Command.ACK, null, sessionId));
    }

    function send(message) {
        if (!socket) {
            return;
        }
        if (socket.readyState == WebSocket.OPEN) {
            socket.send(JSON.stringify(message));
        } else {
            mpushConfig.log.error("The socket is not open.");
        }
    }

    function dispatch(packet) {
        switch (packet.cmd) {
            case Command.HANDSHAKE: {
                mpushConfig.log.debug(">>> handshake ok.");
                session.handshakeOk = true;
                if (mpushConfig.listener != null) {
                    mpushConfig.listener.onHandshake();
                }
                if (mpushConfig.userId) {
                    bindUser(mpushConfig.userId, mpushConfig.tags);
                }
                break;
            }
            case Command.OK: {
                if (packet.body.cmd == Command.BIND) {
                    mpushConfig.log.debug(">>> bind user ok.");
                    if (mpushConfig.listener != null) {
                        mpushConfig.listener.onBindUser(true);
                    }
                }
                break;
            }
            case Command.ERROR: {
                if (packet.body.cmd == Command.BIND) {
                    mpushConfig.log.debug(">>> bind user failure.");
                    if (mpushConfig.listener != null) {
                        mpushConfig.listener.onBindUser(false);
                    }
                }
                break;
            }

            case Command.KICK: {
                if (session.userId == packet.body.userId && mpushConfig.deviceId == packet.body.deviceId) {
                    mpushConfig.log.debug(">>> receive kick user.");
                    if (mpushConfig.listener != null) {
                        mpushConfig.listener.onKickUser(packet.body.userId, packet.body.deviceId);
                    }
                    doClose(-1, "kick user");
                }
                break;
            }

            case Command.PUSH: {
                mpushConfig.log.debug(">>> receive push, content=" + packet.body.content);
                var sessionId;
                if ((packet.flags & 8) != 0) {
                    ack(packet.sessionId);
                } else {
                    sessionId = packet.sessionId
                }
                if (mpushConfig.listener != null) {
                    mpushConfig.listener.onReceivePush(packet.body.content, sessionId);
                }
                break;
            }
        }
    }

    function onReceive(event) {
        mpushConfig.log.debug(">>> receive packet=" + event.data);
        dispatch(JSON.parse(event.data))
    }

    function onOpen(event) {
        mpushConfig.log.info("Web Socket opened!");
        if (mpushConfig.listener != null) {
            mpushConfig.listener.onOpened(event);
        }
        handshake();
    }

    function onClose(event) {
        mpushConfig.log.info("Web Socket closed!");
        if (mpushConfig.listener != null) {
            mpushConfig.listener.onClosed(event);
        }
        session = {};
        ID_SEQ = 1;
        socket = null;
    }

    function onError(event) {
        mpushConfig.log.info("Web Socket receive, error");
        doClose();
    }

    function doClose(code, reason) {
        if (socket) socket.close();
        mpushConfig.log.info("try close web socket client, reason=" + reason);
    }

    function doConnect(cfg) {
        mpushConfig = $.extend({},mpushConfig,cfg);
        if(socket){
            doClose(-1,"Close");
        }
        setTimeout(function () {
            socket = new WebSocket(mpushConfig.url);
            socket.onmessage = onReceive;
            socket.onopen = onOpen;
            socket.onclose = onClose;
            socket.onerror = onError;
            mpushConfig.log.debug("try connect to web socket server, url=" + mpushConfig.url);
        }, 500);
    }

    var mpushFun = function (mpushConfig) {

    }

    mpushFun.prototype.init = function (mpushConfig) {
        doConnect(mpushConfig);
        return this;
    };

    mpushFun.prototype.close = function (code, reason) {
        doClose(code, reason);
        return this;
    };
    mpushFun.prototype.bindUser = function (userId, tags) {
        bindUser(userId, tags);
        return this;
    };
    //返回构造函数
    return mpushFun;
})();
export default mpush;

