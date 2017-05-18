var login = {
    init: function() {
        this.initNode();
        this.addEvent();
    },

    initNode: function() {	// 初始化节点
        this.$username = $('#userId');
        this.$pwd = $('#password');
        this.$errorMsg = $('#errorMsg');
        this.$submit = $('#submit');
        this.$loginForm = $('#loginForm');
    },

    addEvent: function() {	// 绑定事件
        var that = this;
        this.$submit.on('click', this.validate.bind(this));
        $(document).on('keydown', function(e) {
            var ev = e || window.event;
            if (ev.keyCode === 13) {
                that.validate();
            }
        });
    },

    validate: function() {
        this.$errorMsg.addClass('hide');
        var that = this,
            username = $.trim(this.$username.val()),
            pwd = this.$pwd.val(),
            errorMsg = '';
        if (username.length === 0) {
            errorMsg = '帐号不能为空';
        } else if (!pwd) {
            errorMsg = '密码不能为空';
        } else {
            this.$loginForm.submit();
            return;
        }
        this.$errorMsg.html(errorMsg).removeClass('hide');  // 显示错误信息
        return false;
    }
};
login.init();