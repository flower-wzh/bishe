<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<c:set var="path">${pageContext.request.contextPath}</c:set>
    <div class="container-fluid page-body-wrapper full-page-wrapper" style="align-content: center;text-align: center">
            <div class="row w-100">
                <div class="col-lg-4 mx-auto">
                    <div class="auth-form-light text-left p-5">
                        <div class="brand-logo">
                            <h2>诊所详细信息</h2>
                        </div>
                        <h4>你好!</h4>
                            <input type="hidden" id="id" name="id">
                        <div class="form-group">
                            <input type="text" class="form-control form-control-lg" name="name" id="name" placeholder="名字">
                        </div>
                        <div class="form-group">
                            <img src="../../images/门诊01.jpg" width="145" height="120" id="uploadimg">
                            <input type="file" class="form-control form-control-lg" id="file_upload" name="file" placeholder="图片" onchange="upload_review()">
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control form-control-lg" id="city" name="city" placeholder="城市">
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control form-control-lg" id="province" name="province" placeholder="省">
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control form-control-lg" id="country" name="country" placeholder="国家">
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control form-control-lg" id="address" name="address" placeholder="详细地址">
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control form-control-lg" id="phone" name="phone" placeholder="电话">
                        </div>
                        <div class="form-group">
                            类别：
                            <select id="category" name="categoryId">

                            </select>
                        </div>
                        <div class="mt-3">
                            <a class="btn btn-primary btn-gradient-primary btn-lg font-weight-medium auth-form-btn" href="javascript:void(0);" id="change">修改</a>
                        </div>
                        <span style="color: red"><a id="error"></a></span>
                        <script>
                            var categoryId;
                            $(function () {
                                $('#change').on('click',function () {
                                    $.ajaxFileUpload({
                                        url:"${path}/clinic/modify",
                                        type:"post",
                                        datatype:"json",
                                        fileElementId:"file_upload",
                                        data:{
                                            id:$("#id").val(),
                                            name:$("#name").val(),
                                            city:$("#city").val(),
                                            province:$("#province").val(),
                                            country:$("#country").val(),
                                            address:$("#address").val(),
                                            phone:$("#phone").val(),
                                            categoryId:$("#category").val()
                                        },
                                        success:function (data) {
                                            console.log("修改成功");
                                        }
                                    });
                                });

                                $.ajax({
                                    url: '${path}/clinic/detail?clinicId=${admin.clinicId}',
                                    type: 'GET',
                                    dataType: 'JSON',
                                    success: function (result){
                                        $('#id').attr('value',result.id);
                                        $('#name').attr('value',result.name);
                                        $('#uploadimg').attr('src',result.img);
                                        $('#city').attr('value',result.city);
                                        $('#province').attr('value',result.province);
                                        $('#country').attr('value',result.country);
                                        $('#address').attr('value',result.address);
                                        $('#phone').attr('value',result.phone);
                                        $('#category').attr('value',result.categoryId);
                                        categoryId = result.categoryId;
                                    }
                                });

                                $.ajax({
                                    url: '${path}/category/all',
                                    type: 'GET',
                                    dataType: 'JSON',
                                    success: function (result){
                                        $.each(result,function (i,category) {
                                            if(category.id == categoryId){
                                                $('#category').append($('<option value="'+category.id+'" selected>'+category.name+'</option>'));
                                            }else {
                                                $('#category').append($('<option value="'+category.id+'">'+category.name+'</option>'));
                                            }
                                        })
                                    }
                                })
                            })
                        </script>
                        <script>
                            function upload_review() {
                                var img = document.getElementById("uploadimg");
                                var input = document.getElementById("file_upload");
                                var file = input.files.item(0);
                                var freader = new FileReader();
                                freader.readAsDataURL(file);
                                freader.onload = function (e) {
                                    img.src = e.target.result;
                                }
                            }
                        </script>
                    </div>
                </div>
            </div>
    </div>
</div>


