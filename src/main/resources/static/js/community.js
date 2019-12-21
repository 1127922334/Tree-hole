//展开
function collapseComment(e) {
    //通过获取标签存储的值,来控制那个标签展开
    var id = e.getAttribute("data-id");

    // 获取到需要添加二级而评论的标签区域
    var comments=$("#comment-"+id);
        if (comments.hasClass("in")){
            comments.removeClass("in");
            e.classList.remove("active");
        }else {
            var comment_s=$("#comment-"+id);
            if(comments.children().length!=2){
                comments.addClass("in");
                e.classList.add("active");
            }else {
                $.getJSON( "/comment/"+id, function(data) {
                    // 把获取的数据进行拼接
                    $.each(data.data.reverse(),function (index,comment_1) {
                        var mediaLeftElement=$("<div/>",{
                            "class":"media-left"
                        }).append($("<img/>",{
                            "class":"media-object img-rounded",
                             src:comment_1.user.avatarUrl
                        }));
                        var mediaBodyElement = $("<div/>",{
                            "class":"media-body"
                        }).append( $("<h5/>",{
                            "class":"media-heading",
                            html:comment_1.user.name
                        })).append($("<div/>",{
                            html:comment_1.comment
                        })).append($("<div/>",{
                            "class":"menu"
                        }).append($("<span/>",{
                            "class":"pull-right",
                            "html":moment(comment_1.gmtCreate).format('YYYY-MM-DD')
                        })));
                        var b=$("<hr/>",{
                            "class":"col-lg-12 col-md-12 col-sm-12 col-xs-12",
                            "style":"margin-top: 10px",
                        });
                        var mediaElement =$("<div/>",{
                            "class":"col-lg-12 col-md-12 col-sm-12 col-xs-12"
                        }).append(mediaLeftElement).append(mediaBodyElement).append(b);
                        comments.prepend(mediaElement);
                    });
                    comments.addClass("in");
                    e.classList.add("active");
                });
            }


        }


    // var flag =e.getAttribute("data-flag");
    //     if (flag==0){
    //         var id = e.getAttribute("data-id");
    //         var comments=$("#comment-"+id);
    //         comments.addClass("in");
    //         e.setAttribute("data-flag",1);
    //     }else if (flag==1){
    //         var id = e.getAttribute("data-id");
    //         var comments=$("#comment-"+id);
    //         comments.removeClass("in");
    //         e.setAttribute("data-flag",0);
    //     }

}
function post(){
    var comment = $("#comment").val();
    //问题的id
    var questionID = $("#question_id").val();
    commenttarget2(questionID,1,comment);
}

function commenttarget2(parentId,type,comment) {
    //使用ajax post请求来更新数据库
    if (!comment){
        alert("不能回复空哦~");
        return;
    }

    $.ajax({
        type: 'POST',
        url: "/comment",
        contentType:"application/json",
        data:  JSON.stringify({"parentId":parentId,"comment":comment,"type":type}),
        success: function (response) {
            if (response.code==200){
                window.location.reload();
            }else {
                if (response.code == 2003) {
                    var isAccepted = confirm(response.message);
                    if (isAccepted) {
                        window.open("https://github.com/login/oauth/authorize?client_id=c3a0ea411738c50b7499&redirect_uri=http://localhost:8080/gitlogin&scope=user&state=1")
                        window.localStorage.setItem("closeable", true);
                    }
                } else {
                    alert(response.message);
                }
            }
        },
        dataType: 'json'
    });
}
function comment2(e) {
    var commentId = e.getAttribute("data-id2");
    var comment = $("#input-"+commentId).val();
    commenttarget2(commentId,2,comment);
}
function settag(value) {
    var previous = $("#tag").val();
    if(previous.indexOf(value)==-1){
        if(previous){
            $("#tag").val(previous+","+value);
        }else {
            $("#tag").val(value);
        }
    }


}

function show_tag() {
    $("#select-tag").show();
}