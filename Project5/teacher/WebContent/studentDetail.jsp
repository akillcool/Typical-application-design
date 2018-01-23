<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <style type="text/css">
        <!--
        body {
            margin-left: 0px;
            margin-top: 0px;
            margin-right: 0px;
            margin-bottom: 0px;
            background-color: #EEF2FB;
        }
        #manageSubject table  td{
            font-size:12px;
        }
        -->
    </style>
     <link href="images/skin.css" rel="stylesheet" type="text/css">
    </head>
<body>
<form action="ClassSearchServlet" name="searchForm" method="post" onclick="checkInf()">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td width="17" valign="top" background="images/mail_leftbg.gif"><img src="images/left-top-right.gif" width="17" height="29" /></td>
            <td valign="top" background="images/content-bg.gif"><table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
                <tr>
                    <td height="31"><div class="titlebt">Student Info</div></td>
                </tr>
            </table></td>
            <td width="16" valign="top" background="images/mail_rightbg.gif"><img src="images/nav-right-bg.gif" width="16" height="29" /></td>
        </tr>
        <tr>
            <td valign="middle" background="images/mail_leftbg.gif">&nbsp;</td>
            <td valign="top" bgcolor="#F7F8F9"><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                    <td width="53%" valign="top" align="center">
                     
                    </td>
                </tr>
                <tr>
                    <td valign="middle"><span class="left_txt"></span>
                        <div id="managestduent" align="center">
                            <table width="95%" cellspacing="10">
                                <tr align="center">
                       
                                    <td>Student ID</td>
                                    <td>Student Name</td>
                                    <td>Student Age</td>
                                </tr>
								<c:forEach items="${StudentList}" var="student">
                                <tr align="center">
                                
                                    <td>${student.studentId}</td>
                                    <td>${student.sName}</td>
                                    <td>${student.age}</td>
                                </c:forEach>
                            </table>
                        </div>
                    </td>
                </tr>

            </table></td>
            <td background="images/mail_rightbg.gif">&nbsp;</td>
        </tr>
        <tr>
            <td valign="bottom" background="images/mail_leftbg.gif"><img src="images/buttom_left2.gif" width="17" height="17" /></td>
            <td background="images/buttom_bgs.gif"><img src="images/buttom_bgs.gif" width="17" height="17"></td>
            <td valign="bottom" background="images/mail_rightbg.gif"><img src="images/buttom_right2.gif" width="16" height="17" /></td>
        </tr>
    </table>
</form>
</body>
</html>