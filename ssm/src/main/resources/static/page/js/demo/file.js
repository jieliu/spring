var dfd = $.Deferred();
var wait = function (dfd) {
    var tasks = function () {
        alert("执行完毕");
        dfd.resolve();
    };

    setTimeout(tasks, 3000);
};
dfd.promise(wait);

wait.done(function () {
    alert("成功了！");
}).fail(function () {
    alert("出错了!");
});

wait(dfd);


function handleFiles(file) {
    var fileList = file.files;
    var result = new Array();
    if(fileList && fileList.length) {
        for ( var i = 0; i < fileList.length; i++) {
            var temp_result;
            temp_result=uploadfile(fileList[i],fileList[i].name);
            result.push(temp_result);
        }
        return result;
    } else {
        return null;
    }
}

function uploadfile(file, fileName) {
    var formData = new FormData();
    formData["enctype"] = "multipart/form-data";
    formData.append("file", file);
    formData.append("file_name", fileName);
    var result=sendFormData(formData);
    return result;
}

function sendFormData(formData) {
    console.log("enter send form data!!");
    var result="";
    $.ajax({
        url: "/api/upload.html",
        type: 'post',
        async : false,
        contentType : false,
        processData : false,
        data: formData,
        dataType: 'text',

        success: function(data){
            console.log(new Date() + ", log info [ " + data + "]");
            var new_arr=data.split(" ");
            var max_len=new_arr.length-1;
            console.log(new_arr[max_len]);
            result=new_arr[max_len];
        },

        error : function(json) {
            result="failure";
            var filename = json.responseText;
            var obj = JSON.parse(filename);
            var mediaType = obj.mediaType;

            if(filename!='undefined' || filename!="" || filename!=undefined) {
                console.log("未选择文件！");
            }
        }
    });
    return result;
}

function downloadFiles(file, url) {
    var fileList = file.files;
    var result = new Array();
    if(fileList && fileList.length) {
        for ( var i = 0; i < fileList.length; i++) {
            var temp_result;
            temp_result=downloadFile(url,fileList[i].name);
            result.push(temp_result);
        }
        return result;
    } else {
        return null;
    }
}

function downloadFile(url, fileId) {
    return url + fileId;
}