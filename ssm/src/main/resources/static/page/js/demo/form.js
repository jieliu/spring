
$("#saveSkill").click(function () {

    var name = $("#inputSkill").val();
    var skill = $("#inputSkillId").val();

    console.log("name : " + name + ", skill : " + skill);

    if(name && skill) {
        $.ajax({
            url: '/api/saveSkill',
            type: 'GET', //GET
            data: {
                name : name,
                skill: skill,
            },

            dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            success: function (data, textStatus, jqXHR) {
                if (data) {
                    alert(data.msg);
                    window.location.href="/agent.html";
                } else {
                    alert("保存失败，请稍后重试！");
                }
            },
            error: function (xhr, textStatus) {
                alert("保存失败，请稍后重试！");
            }
        });
    } else {
        alert("名称或技能为空");
    }
});

function deleteSkill(event) {
    var e = event || window.event;
    var obj=e.srcElement||e.target;
    agentId=$(obj).parent().parent().parent().find('td').eq(0).text();
    var skill = "";
    nameVal="[name='" + agentId + '' +  "']:checked";
    $(nameVal).each(function(i){
        if(i==0){
            skill+=$(this).val();
        }else {
            skill+=","+$(this).val();
        }
    });
    console.log("agent name is " + agentId + ", skillName is " + skill);

    if(agentId && skill) {
        $.ajax({
            url: '/api/deleteSkill',
            type: 'GET', //GET
            data: {
                skill: skill,
                agentId: agentId
            },

            dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            success: function (data, textStatus, jqXHR) {
                if (data) {
                    alert(data.msg);
                    window.location.href="/agent.html";
                } else {
                    alert("保存失败，请稍后重试！");
                }
            },
            error: function (xhr, textStatus) {
                alert("保存失败，请稍后重试！");
            }
        });
    } else {
        alert("名称或技能为空");
    }
}