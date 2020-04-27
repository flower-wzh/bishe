<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<script>
    $(function () {
        $('#comment').jqGrid({
            styleUI:'Bootstrap',
            url:'${path}/comment/findAll?clinicId='+'01',
            datatype:'json',
            mtype:'get',
            colNames : [ '评论人', '内容','评分',"时间" ],
            colModel : [
                {name : "userId",hidden : true},
                {name : "content",align : "center",editable:true},
                {name : "star",align : "center"},
                {name:'time',align:'center'}
            ],
            pager:"#pager",
            rowNum:5,
            rowList:[5,10,15],
            viewrecords:true,
            mtype : "post",
            caption:' 评论列表',
            width:'100%',
            height:'100%',
            autowidth:true,
            multiselect:true
        }).navGrid('#pager',
            {
                sopt:['eq','ne','cn']
            }
        );
    });



</script>
<div class="row">
    <div class="col-sm-12">
        <table id="comment"></table>
        <div id="pager" style="height:30px">
        </div>
    </div>
</div>