<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<c:set var="path">${pageContext.request.contextPath}</c:set>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="${path}/static/boot/css/bootstrap.min.css">
    <link rel="stylesheet" href="${path}/static/boot/css/back.css">
    <link rel="stylesheet" href="${path}/static/jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
    <link rel="stylesheet" href="${path}/static/jqgrid/css/jquery-ui.css">
    <script src="${path}/static/boot/js/jquery-2.2.1.min.js"></script>
    <script src="${path}/static/boot/js/bootstrap.min.js"></script>
    <script src="${path}/static/jqgrid/js/trirand/src/jquery.jqGrid.js"></script>
    <script src="${path}/static/jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <script src="${path}/static/boot/js/ajaxfileupload.js"></script>
    <script type="text/javascript" src="${path}/static/editor/kindeditor-all-min.js" ></script>
    <script type="text/javascript" src="${path}/static/editor/lang/zh-CN.js" ></script>
    <link rel="stylesheet" href="${path}/static/editor/themes/default/default.css" />

    <title>诊所预约系统后台</title>
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">诊所预约系统后台 ${login}</a>
        </div>
        <div>
            <!--向右对齐-->
            <ul class="nav navbar-nav navbar-right">
                <li><a href="javascript:void(0)">欢迎:${admin.username}${admin.clinicId}</a></li>
                <li><a href="${path}/admin/logout">退出登录</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-2" style="height: 80%">
            <nav class="sidebar sidebar-offcanvas" id="sidebar">
                <ul class="nav">
                    <shiro:hasRole name="admin">
                        <li class="nav-item">
                            <a class="nav-link" href="#ui-basic" data-toggle="collapse">
                                <span class="menu-title">用户管理</span>
                                <i class="menu-arrow"></i>
                            </a>
                            <div class="collapse" id="ui-basic">
                                <ul class="nav flex-column sub-menu">
                                    <li class="nav-item"> <a style="color: burlywood" class="nav-link" href="javascript:$('#center').load('${path}/back/user/user.jsp')">用户列表</a></li>
                                    <li class="nav-item"> <a style="color: burlywood" class="nav-link" href="javascript:$('#center').load('${path}/back/user/registdistribution.jsp')">注册趋势图</a></li>
                                    <li class="nav-item"> <a style="color: burlywood" class="nav-link" href="javascript:$('#center').load('${path}/back/user/urbandistribution.jsp')">地理分布图</a></li>
                                </ul>
                            </div>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link">
                            <span class="menu-title">
                                <a href="javascript:$('#center').load('${path}/back/clinic/clinic.jsp')">诊所管理</a>
                            </span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link">
                            <span class="menu-title">
                                <a href="javascript:$('#center').load('${path}/back/banner/banner.jsp')">轮播图管理</a>
                            </span>
                            </a>
                        </li>
                    </shiro:hasRole>
                    <shiro:hasRole name="clinic">
                        <li class="nav-item">
                            <a class="nav-link">
                                            <span class="menu-title">
                                                <a href="javascript:$('#center').load('${path}/back/patients/patients.jsp?clinicId=${admin.clinicId}')">预约病人</a></span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link">
                            <span class="menu-title">
                                <a href="javascript:$('#center').load('${path}/back/comment/comment.jsp?clinicId=${admin.clinicId}')">评论列表</a></span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link">
                            <span class="menu-title">
                                <a href="javascript:$('#center').load('${path}/back/clinic/detail.jsp?clinicId=${admin.clinicId}')">修改信息</a>
                            </span>
                            </a>
                        </li>
                    </shiro:hasRole>

                </ul>
            </nav>
        </div>
        <div class="col-sm-10">
            <div id="center">
                <div class="jumbotron">
                    <h2>欢迎使用诊所预约后台管理系统！</h2>
                </div>
                <div id="myCarousel" class="carousel slide">
                    <!-- 轮播（Carousel）指标 -->
                    <ol class="carousel-indicators">
                        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                        <li data-target="#myCarousel" data-slide-to="1"></li>
                        <li data-target="#myCarousel" data-slide-to="2"></li>
                    </ol>
                    <!-- 轮播（Carousel）项目 -->
                    <div class="carousel-inner">
                        <div class="item active">
                            <img style="width: 100%;" src="http://localhost:8080/file/img/20191127113813316d5d04ab4381f43b782a0b0dfe0926d7b.jpg" alt="First slide">
                            <div class="carousel-caption">标题 1</div>
                        </div>
                        <div class="item">
                            <img style="width: 100%;" src="http://localhost:8080/file/img/20191127113832080ab995b8f38b4420fb9f5292901c0a756.jpg" alt="Second slide">
                            <div class="carousel-caption">标题 2</div>
                        </div>
                        <div class="item">
                            <img style="width: 100%;" src="http://localhost:8080/file/img/2019112711384943169b4e2e420c8485bab167623daf8a816.jpg" alt="Third slide">
                            <div class="carousel-caption">标题 3</div>
                        </div>
                    </div>
                    <!-- 轮播（Carousel）导航 -->
                    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
            </div>

        </div>
    </div>
</div>
<div class="panel-footer">
    <h4 align="center">@yxwangzhenhua@163.com</h4>
</div>
</body>
<div class="modal fade" id="addBingLi" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 750px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">病例详情</h4>
            </div>
            <div class="modal-body">
                <form id="case_data">
                    <div class="form-group">
                        <input type="hidden" name="appointmentId" id="app_id" class="form-control">
                    </div>
                    <div class="form-group">
                        <div class="col-sm-12">
                            病例描述：
                            <textarea id="app_description" name="description" style="width:700px;height:300px;"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <script>
                $(function () {
                   $('#sub').on('click',function () {
                       $.ajax({
                           url: '${path}/case/addCase',
                           type: 'POST',
                           data: $('#case_data').serialize(),
                           success: function (result) {
                               alert("添加成功");
                           }
                       })
                   })
                });
            </script>
            <div class="modal-footer" id="modal_foot">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="sub">提交</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</html>

