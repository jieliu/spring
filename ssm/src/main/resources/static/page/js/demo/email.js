var Email = {
    init: function() {
        this.initNode();
        this.addEvent();
    },

    initNode: function() {	// 初始化节点
        this.$email = $('#inputEmail');
        this.$theme = $('#inputTheme');
        this.$editor = CKEDITOR.document.getById('editor');
        this.$submit = $('#submit');
        this.$emailForm = $('#emailForm');
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
        var that = this,
            username = $.trim(this.$email.val());

        if(username.length === 0) {
            alert("收件人不能为空");
        } else {
            console.log("send email: " + username + ", " + this.$theme.val() + ", " + this.$editor.getHtml());
            this.$emailForm.submit();
            return;
        }
        return false;
    }
};
Email.init();