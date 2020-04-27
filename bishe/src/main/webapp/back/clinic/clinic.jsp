<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<script>
    $(function () {
        function upload(guruId) {
            $.ajaxFileUpload({
                url:"${pageContext.request.contextPath}/guru/upload",
                datatype:"json",
                type:"post",
                data:{'guruId':guruId},
                // 指定的上传input框的id
                fileElementId:"photo",
                success:function (data) {
                    // 输出上传成功
                    // jqgrid重新载入
                    $("#gurus").trigger("reloadGrid");
                }
            })
        }
        $('#clinic').jqGrid({
            styleUI:'Bootstrap',
            url:'${path}/clinic/show',
            datatype:'json',
            mtype:'get',
            colNames:["编号","名字","照片","评分","地址","状态","操作"],
            colModel:[
                {name:'id',align:'center',search:false,hidden:true},
                {name:'name',align:'center',editable:true},
                {name:'img',align:'center',editable:true,edittype:"file",editoptions:{enctype:"multipart/form-data"},formatter:function (data) {
                        return "<img style='width: 100%' src='"+data+"'>";
                    }},
                {name:'star',align:'center',editable:true},
                {name:'address',align:'center',editable:true},
                {name:'status',align:'center',editable:true,formatter:function (data) {
                        if(data == 1){
                            return "正常营业";
                        }
                        return "歇业整顿";
                    }},
                {name:'option',align:'center',editable:true,formatter:function (cellvalue, options, rowObject) {
                        return "<button class='btn btn-primary' onclick=\"changeStatus('" + rowObject.id + "','"+rowObject.status+"')\">修改状态</button>"
                    }}
            ],
            pager:"#pager",
            rowNum:5,
            rowList:[5,10,15],
            viewrecords:true,
            mtype : "post",
            caption:'轮播图',
            autowidth:true,
            height: '100%',
            width:'100%',
            multiselect:true
        }).navGrid('#pager');
    });

    //更改状态
    function changeStatus(id,status) {
        /*        console.log(id);
                console.log(status);*/
        $.ajax({
            url:'${path}/clinic/changeStatus',
            type:'GET',
            dateType:'text',
            data:{
                id:id,
                status:status
            },
            success:function () {
                $("#clinic").trigger("reloadGrid");
            }
        })
    }
</script>
<div class="row">
    <div class="col-sm-12">
        <table id="clinic"></table>
        <div id="pager" style="height:30px">
        </div>
    </div>
</div>

