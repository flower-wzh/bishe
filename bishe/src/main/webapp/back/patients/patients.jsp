<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<script>
    $(function () {
        $('#patients').jqGrid({
            styleUI:'Bootstrap',
            url:'${path}/appointment/findAll?clinicId=${admin.clinicId}',
            datatype:'json',
            mtype:'get',
            colNames : [ '编号', '预约人','预约时间',"状态","操作" ],
            colModel : [
                {name : "id",hidden : true},
                {name : "userId",align : "center",editable:true},
                {name : "date",align : "center"},
                {name:'status',align:'center',editable:true,formatter:function (data) {
                        if(data == 1 || data == -1){
                            return "已完成";
                        }else if(data==6){
                            return "违约";
                        }
                        return "暂未赴约";
                    }},
                {name : "option",align : "center",formatter:function(cellvalue, options, rowObject) {
                        var result = "";
                        result += "<button  href='javascript:void(0)' onclick=\"weiyue('" + rowObject.id + "')\" class='btn btn-primary' title='违约'>违约</button >";
                        result += "<button href='javascript:void(0)' onclick=\"bingli('" + rowObject.id + "')\" class='btn btn-primary' title='填写病例'>填写病例</button>";
                        return result;
                    }},
            ],
            pager:"#pager",
            rowNum:5,
            rowList:[5,10,15],
            viewrecords:true,
            mtype : "post",
            caption:'预约病人表',
            width:'100%',
            height:'100%',
            autowidth:true
        }).navGrid('#pager',
            {
                sopt:['eq','ne','cn']
            }
        );
    });

    function weiyue(id) {
        $.ajax({
            url:'${path}/appointment/changeStatus',
            type:'GET',
            dateType:'text',
            data:{
                id:id,
                status:status
            },
            success:function () {
                $("#patients").trigger("reloadGrid");
            }
        })
    }

    function bingli(id) {
        var data = $("#patients").jqGrid("getRowData",id);
        console.log(data.id);
        $('#app_id').val(data.id);
        $.ajax({
            url:'${path}/case/findOneCase?appointmentId='+id,
            type:'GET',
            dateType:'JSON',
            success: function (result) {
                $('#app_description').val(result.description);
            }
        })
        $('#addBingLi').modal('show');
    }

</script>
<div class="row">
    <div class="col-sm-12">
        <table id="patients"></table>
        <div id="pager" style="height:30px">
        </div>
    </div>
</div>