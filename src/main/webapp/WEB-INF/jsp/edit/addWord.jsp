<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="static/style/form.css" rel="stylesheet">
    <script type="text/javascript" src="static/javascript/jquery.js"></script>
    <script type="text/javascript" src="static/layer/src/layer.js"></script>
</head>
<body>

<div class="container-form">

    <div class="input-col-row">
        <input type="hidden" name="id" value="${wordView.id}"/>
        <div class="input-row-col">
            <div class="input-row-label">
                书名
            </div>
            <div class="input-text-row">
                <input type="text" name="fromBook" value="${wordView.fromBook}" class="input-text" />
            </div>
        </div>
        <div class="input-row-col">
            <div class="input-row-label">
                课时
            </div>
            <div class="input-text-row">
                <input type="text" name="lesson" value="${wordView.lesson}" class="input-text" />
            </div>
        </div>
        <div class="input-row-col">
            <div class="input-row-label">
                单词
            </div>
            <div class="input-text-row">
                <input type="text" name="word" value="${wordView.word}" class="input-text" />
            </div>
        </div>
        <div class="input-row-col">
            <div class="input-row-label">
                词性
            </div>
            <div class="input-text-row">
                <input type="text" name="type" value="${wordView.type}" class="input-text" />
            </div>
        </div>
        <div class="input-row-col">
            <div class="input-row-label">
                音标
            </div>
            <div class="input-text-row">
                <input type="text" name="phoneticSymbol" value="${wordView.phoneticSymbol}" class="input-text" />
            </div>
        </div>
        <div class="input-row-col">
            <div class="input-row-label">
                翻译
            </div>
            <div class="input-text-row">
                <input type="text" name="translate" value="${wordView.translate}" class="input-text" />
            </div>
        </div>
        <div class="input-row-col">
            <div class="form-submit-button">
                <button class="btn" id="save">保存</button>
            </div>
        </div>
    </div>

</div>

<script type="application/javascript">
    $("#save").click(function(){

        if(!$("input[name*=\"fromBook\"]").val()){

            layer.msg("书名不能为空");
            return;
        }
        if(!$("input[name*=\"lesson\"]").val()){

            layer.msg("单元不能为空");
            return;
        }
        if(!$("input[name*=\"word\"]").val()){

            layer.msg("单词不能为空");
            return;
        }
        if(!$("input[name*=\"type\"]").val()){

            layer.msg("词性不能为空");
            return;
        }
        if(!$("input[name*=\"phoneticSymbol\"]").val()){

            layer.msg("音标不能为空");
            return;
        }
        if(!$("input[name*=\"translate\"]").val()){

            layer.msg("翻译不能为空");
            return;
        }
        var data = {
            id:$("input[name*=\"id\"]").val(),
            fromBook:$("input[name*=\"fromBook\"]").val(),
            lesson:$("input[name*=\"lesson\"]").val(),
            word:$("input[name*=\"word\"]").val(),
            type:$("input[name*=\"type\"]").val(),
            phoneticSymbol:$("input[name*=\"phoneticSymbol\"]").val(),
            translate:$("input[name*=\"translate\"]").val()
        }

        $.ajax({
            type: "POST",
            url: "/saveWord",
            contentType: 'application/x-www-form-urlencoded;charset=utf-8',
            data: data,
            dataType: "json",
            success: function(data){
                layer.alert(data.msg, function(index){
                    //do something
                    layer.close(index);
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index); //再执行关闭
                });
            },
            error:function(e){
                 console.log(e)
            }
        })

    })

</script>

</body>
</html>
