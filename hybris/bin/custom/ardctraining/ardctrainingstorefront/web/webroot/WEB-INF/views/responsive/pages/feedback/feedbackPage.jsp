<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="for" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>

<template:page pageTitle="Feedback">

        <c:url value ="/feedback/save" var="saveFeedback"/>
        <form:form id="feedbackForm" action="${saveFeedback}" method="POST" modelAttribute="feedbackForm">
            <div class="form-group">
                <label for="exampleFormControlInput1">Subject</label>
                <form:input type="text" class="form-control" id="exampleFormControlInput1" maxlength="100" placeholder="Enter Subject" path="subject"/>
            </div>
            <div class="form-group">
                <label for="exampleFormControlTextarea1">Message</label>
                <form:textarea class="form-control" id="exampleFormControlTextarea1" rows="3" maxlength="500" placeholder="Message" path="message"/>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
            <button type="button" class="btn btn-danger">Cancel</button>
        </form:form>
        <div>
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col" style="text-align:center">Subject</th>
                        <th scope="col" style="text-align:center">Message</th>
                        <th scope="col" style="text-align:center">Date</th>
                        <th scope="col" style="text-align:center">Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${feedbacks}" var="feedback">
                    <tr>
                        <td style="text-align:center">${feedback.subject}</td>
                        <td style="text-align:center">${feedback.message}</td>
                        <td style="text-align:center">${feedback.submittedDate}</td>
                        <td style="text-align:center">${feedback.status}</td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

</template:page>