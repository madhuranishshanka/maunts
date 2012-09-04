

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>



<div class="row" id="form_area_step1">
    <div class="row right_header" style="margin-bottom:40px;"><p style="padding-top:8px;font-size:14px;color:#951509;padding-left:10px;font-weight:bold;"> Manage RoomTypes</p></div>



 <div class="row mng_rows" style="width:100%;">
 <p style="font-weight: bold;font-size: 13px;width:30%;" class="col">Hospital Name</p>
   <c:forEach var="roomType" items="${roomType}">
     <div class="row mng_rows">
       <p style="width:30%;" class="col"><c:out value="${roomType.roomTypeName }"></c:out></p>
     </div>
   </c:forEach>







 </div>
