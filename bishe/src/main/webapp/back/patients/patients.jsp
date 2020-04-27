<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<script>
    $(function () {
        $('#patients').jqGrid({
            styleUI:'Bootstrap',
            url:'${path}/appointment/findAll?clinicId='+'01',
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
                {name : "option",align : "center",formatter:function(data) {
                        var result = "";
                        result += "<button  href='javascript:void(0)' onclick=\"weiyue('" + data + "')\" class='btn btn-primary' title='违约'>违约</button >";
                        result += "<button href='javascript:void(0)' onclick=\"bingli('" + data + "')\" class='btn btn-primary' title='填写病例'>填写病例</button>";
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
            autowidth:true,
            multiselect:true
        }).navGrid('#pager',
            {
                sopt:['eq','ne','cn']
            }
        );
    });

    function weiyue() {
        $.ajax({
            url:'${path}/appointment/changeStatus',
            type:'GET',
            dateType:'text',
            data:{
                id:id,
                status:status
            },
            success:function () {
                $("#users").trigger("reloadGrid");
            }
        })
    }

    function bingli() {

    }

</script>
<div class="row">
    <div class="col-sm-12">
        <table id="patients"></table>
        <div id="pager" style="height:30px">
        </div>
    </div>
</div>