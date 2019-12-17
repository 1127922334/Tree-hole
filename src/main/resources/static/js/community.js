

function post(){
    var comment = $("#comment").val();
    var questionID = $("#question_id").val();
    //使用ajax post请求来更新数据库
    if (!comment){
        alert("不能回复空哦~");
        return;
    }
    $.ajax({
        type: 'POST',
        url: "/comment",
        contentType:"application/json",
        data:  JSON.stringify({"parentId":questionID,"comment":comment,"type":1}),
        success: function (response) {
            if (response.code==200){
                    window.location.reload();
            }else {
                if(response.code==2003){
                    var isAccepted = confirm(response.message);
                    if (isAccepted){
                        window.open("https://github.com/login/oauth/authorize?client_id=c3a0ea411738c50b7499&redirect_uri=http://localhost:8080/gitlogin&scope=user&state=1")
                        window.localStorage.setItem("closeable",true);
                    }
                }else {
                    alert(response.message);
                }
            }



        },
        dataType: 'json'
    });
}