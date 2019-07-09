<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
    </div>

    <div id="word-table"></div>

</body>
<script>

    function paramLookup(cell){

        //当行数据
        var cellData=cell._cell.row.data;

        var btnAdd="<button class=\"btn btn-table-line\" onclick=\"toolsOpetion('add', "+ cellData.id + ")\">添加</button>";

        var btnEdit="<button class=\"btn btn-table-line\" onclick=\"toolsOpetion('edit',"+ cellData.id + ")\">修改</button>";

        var btnDelete="<button class=\"btn btn-table-line\" onclick=\"toolsOpetion('delete',"+ cellData.id + ")\">删除</button>";

        cell._cell.value = btnAdd + btnEdit + btnDelete;

    }

    var table = new Tabulator("#word-table", {
        //data:tabledata,           //load row data from array
        ajaxURL:"/wordList",
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
            {title:"书名", field:"fromBook",width:95, align:"left"},
            {title:"单元", field:"lesson", width:95},
            {title:"单词", field:"word",  width:200, align:"center"},
            {title:"词性", field:"type", width:130},
            {title:"音标", field:"phoneticSymbol", width:130, align:"center"},
            {title:"翻译", field:"translate", width:200,  align:"center"},
            {title:"操作",formatter:"html",formatterParams : paramLookup},
        ]
    });

    function toolsOpetion(target,id){
        switch(target) {
            case "add":
                layer.open({
                    type: 2,
                    title: '添加单词',
                    maxmin: true,
                    shadeClose: false, //点击遮罩关闭层
                    area : ['800px' , '520px'],
                    content: '/editword',
                    end:function(){

                        table.setData("/wordList");
                    }
                });
                break;
            case "edit":
                layer.open({
                    type: 2,
                    title: '编辑单词',
                    maxmin: true,
                    shadeClose: false, //点击遮罩关闭层
                    area : ['800px' , '520px'],
                    content: '/editword?id=' + id,
                    end:function(){
                        table.setData("/wordList");
                    }
                });
                break;
            case "delete":
                layer.confirm('确定要删除吗', function(index){

                    $.ajax({
                        type: "POST",
                        url: "/deleteWordById",
                        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                        data: {id:id},
                        dataType: "json",
                        success: function(data){
                            layer.alert(data.msg, function(index){
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

                });
                break;
            default:
                break;
        }

    }

    $("#search-key").click(function () {

        if(!$("input[name*=\"keyword\"]").val()){

            layer.msg("关键字不能为空");
            return
        }

        table.setData("/wordList", {keyword:$("input[name*=\"keyword\"]").val()});

    })

    $("#search-clear").click(function () {
        $("input[name*=\"keyword\"]").val("");
        table.setData("/wordList",{keyword:""});
    })



</script>

</html>
