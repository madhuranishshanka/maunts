<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<div class="row" id="form_area_step1">
    <div class="row right_header" style="margin-bottom:40px;"><p
            style="padding-top:8px;font-size:14px;color:#951509;padding-left:10px;font-weight:bold;">Add New Doctor</p>
    </div>

    <form:form method="post" action="./create" modelAttribute="roomType">


        <div class="row" style="padding-bottom:10px;">
            <div class="col " style="width:30%;"><p>RoomType Name</p></div>
            <div class="col " style="width:70%;">
                <form:input path="roomTypeName" name="roomTypeName" id="roomTypeName" class="form_input_box_cel"/>
                <span class="mandotary">*</span>
            </div>
        </div>

        <div class="row" style="padding-bottom:10px;">
            <div class="col " style="width:30%;"><p>Description</p></div>
            <div class="col " style="width:70%;">
                <form:textarea path="description" name="description" id="description" cols="30" rows="6"/>
            </div>
        </div>


        <div class="row" style="padding-top:20px;">
            <div class="col " style="width:30%;"><span class="mandotary">* Mandotary Fields</span>
                <input type="hidden" name="hospitalId" id="hospitalId" value=""/>
            </div>
            <div class="col " style="width:70%;">
                <input type="submit" id="form_submit_btn" name="Submit" class="form_submit_btn" value="Submit"
                       style="position:relative;left:90px;"/>
                <input type="reset" id="form_submit_btn" name="Clear" class="form_submit_btn" value="Clear"
                       style="position:relative;left:110px;"/>
            </div>
        </div>


    </form:form>

</div>

