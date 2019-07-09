<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>记忆单词</title>
    <link href="static/tabulator/css/tabulator.min.css" rel="stylesheet">
    <link href="static/style/form.css" rel="stylesheet">
    <style >
    </style>
</head>
<script type="text/javascript" src="static/tabulator/js/tabulator.min.js"></script>
<script type="text/javascript" src="static/javascript/jquery.js"></script>
<script type="text/javascript" src="static/layer/src/layer.js"></script>

<body>

    <div class="table-tools-search">
        <div class="table-search-field">
            <input type="text" name="keyword" class="table-serach-Keyword">
            <button class="table-search-button btn" id="search-key">搜索</button>
            <button class="table-search-button btn" id="search-clear">重置</button>
        </div>
        <div class="table-search-field">
            <input type="text" name="newLimit" class="table-serach-Keyword generate-newLimit">
            <button class="table-search-button btn" id="memoryword-generate">生成今日计划</button>
        </div>
    </div>

    <div id="memoryRule-table"></div>

</body>

<script >

    var table = new Tabulator("#memoryRule-table", {
        //data:tabledata,           //load row data from array
        ajaxURL:"/memoryRuleList",
        paginationDataSent:{
            "page":"pageNum", //change page request parameter to "pageNo"
            "size":"pageSize" //change page request parameter to "pageNo"
        },
        paginationDataReceived:{
            "last_page":"pages",
        },
        ajaxFiltering: true,
        layout:"fitColumns",      //fit columns to width of table
        responsiveLayout:"hide",  //hide columns that dont fit on the table
        tooltips:true,            //show tool tips on cells
        addRowPos:"top",          //when adding a new row, add it to the top of the table
        history:true,             //allow undo and redo actions on the table
        pagination:"remote",       //paginate the data
        paginationSize:20,         //allow 7 rows per page of data
        movableColumns:true,      //allow column order to be changed
        resizableRows:true,       //allow row order to be changed
        initialSort:[             //set the initial sort order of the data
            {column:"id", dir:"asc"},
        ],
        columns:[                 //define the table columns
            /* {title:"id", field:"id",width:95},*/
            {title:"单词", field:"word",width:95, align:"left"},
            {title:"音标", field:"phoneticSymbol", width:95},
            {title:"词性", field:"type",  width:200, align:"center"},
            {title:"翻译", field:"translate", width:130},
            {title:"记忆时间", field:"memoryTime", width:130},
            {title:"下次时间", field:"nextReview", width:130},
        ]
    });


    $("#search-key").click(function () {

        if(!$("input[name*=\"keyword\"]").val()){

            layer.msg("关键字不能为空");
            return
        }

        table.setData("/memoryRuleList", {keyword:$("input[name*=\"keyword\"]").val()});

    })

    $("#search-clear").click(function () {
        $("input[name*=\"keyword\"]").val("");
        table.setData("/memoryRuleList",{keyword:""});
    })

    $("#memoryword-generate").click(function () {

        $.ajax({
            type: "POST",
            url: "/generateMemoryWord",
            contentType: 'application/x-www-form-urlencoded;charset=utf-8',
            data: {newLimit:$("input[name*=\"newLimit\"]").val()},
            dataType: "json",
            success: function(data){
                layer.alert(0 === data ? "生成成功" : "生成失败", function(index){
                    //do something
                    layer.close(index);
                    /*var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index); //再执行关闭*/
                });
            },
            error:function(e){
                console.log(e)
            }
        })
    })


</script>

</html>
