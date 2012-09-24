<?xml version="1.0" encoding="UTF-8" ?>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
        String setName = "";
        if (request.getAttribute("fullName") != null)
            {
                setName = (String) request.getAttribute("fullName");
            }
        String setEmail = "";

        if (request.getAttribute("email") != null)
            {
                setEmail = (String) request.getAttribute("email");
            }
    %>

<fieldset>
    
        <legend> <strong>Profile</strong> </legend>
        <div style ="display:inline-block; width:30%; height: 180px; padding:5px; margin-top:2px;" align="right">
            <label> <strong>Full Name:</strong> </label> <br/> <br />
            <label> <strong>Date of Birth:</strong> </label> <br/> <br/>
            <label> <strong>E-mail: </strong> </label> <br/> <br/>
            <label> <strong>Phone Number:</strong> </label> <br/> <br/>
        </div>
        

        <div style="display:inline-block; width:40%; height:180px; padding:5px; vertical-align: top" align="left">
            <input class="gradientGrey textBox" type="text" id="Name" value="<%=setName%>" />
            <input class="gradientGrey textBox" type="text" id="DoB" />
            <input style="margin-top:5px;" class="gradientGrey textBox" type="text" id="Email" value="<%=setEmail%>" />
            <input style="margin-top:10px;" class="gradientGrey textBox" type="text" id="PhoneNumber" />
        </div>

        <div onclick="appendDetails()" style="display:inline-block; cursor: pointer;" align="center">
            <strong><u>Transfer Details to CV</u></strong> <br/>
            <img style="margin-bottom: 30px;" src="images/arrow_down.png" title="Transfer Details" />
        </div>
    </fieldset>

    <br/>

    <div class="buttons" align="right">
        <button class="positive" style="margin-right:30%;" type="button" onclick="ajaxSave('saveCoverLetter')" >Save and Continue </button>
        <button class="positive" style="margin-right:5px;" type="button" onclick="ajaxLoad('loadCoverLetter')" >Load</button>
    </div>

    <br/>

    <fieldset>
        <legend> <strong>Cover Letter</strong> </legend>

        <textarea rows="15" cols="50" name="content" class="CVbox"></textarea>

        <br />

        <div align="right">
            <input style="margin-right:30%;" type="button" onclick="ajaxSave('saveCoverLetter')" value="Save and Continue" />
            <input style="margin-right:5px;" type="button" onclick="ajaxLoad('loadCoverLetter')" value="Load CV" />
        </div>
        
    </fieldset>
