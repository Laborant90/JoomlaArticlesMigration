<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@page import="com.liferay.portal.model.Group"%>
<portlet:defineObjects />
<portlet:resourceURL var="ajaxResourceURL" /> 
<script src="<%=request.getContextPath()%>/js/jquery.min.js"></script> 
<script src="http://cdn.alloyui.com/3.0.1/aui/aui-min.js"></script>
<link href="<%=request.getContextPath()%>/css/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet"></link>
<script type="text/javascript">
	//#sourceURL=dynamicScript.js 
	var dropdown = Object();
	var started = false;
	YUI().use('aui-io-request',
			'aui-dropdown',		  
			  function(Y) {
				  Y.one('#liferay_user').set('value', "<%=PortalUtil.getUser(request).getUserId()%>");
				  Y.one('#content_table_name').set('value', 'dwdlu_content');
				  Y.one('#url').set('value', "jdbc:mysql://localhost/joomla");  
			      Y.one('#driver').set('value', "com.mysql.jdbc.Driver");   
				  Y.one('#params-form-submit').on('click', function() {
					   if(Y.one('#user').get('value') != "" && 
					      Y.one('#password').get('value') != "" &&
					      Y.one('#url').get('value') != "" &&
					      Y.one('#driver').get('value') != "" &&
					      Y.one('#content_table_name').get('value') != "" &&
					      Y.one('#liferay_user').get('value') != "" && 
					      dropdown.value != null &&
					   	  started == false
					   ) {
						    Y.one('#results').set('innerText', "Operation started!!!");
						    started = true;
					   		Y.io.request(
		            		      '<%=ajaxResourceURL.toString()%>',
		            		      {		            		    	  
		                                  data:  {							                                              
		                                             '<portlet:namespace/>user'     : Y.one('#user').get('value'), 
		                                             '<portlet:namespace/>password' : Y.one('#password').get('value'), 
		                                             '<portlet:namespace/>url'      : Y.one('#url').get('value'), 
		                                             '<portlet:namespace/>driver'   : Y.one('#driver').get('value'),
		                                             '<portlet:namespace/>content_table_name'  : Y.one('#content_table_name').get('value'),
		                                             '<portlet:namespace/>liferay_user'        : Y.one('#liferay_user').get('value'), 
		                                             '<portlet:namespace/>group_id' : dropdown.value
		                                         } 
		                                 ,
		                                 method: 'POST',
		                                 on: {
		                                     success: function() {
		                                    		var data = this.get('responseData');			                                        
			                                       	var jsonData = Y.JSON.parse(data);
			                                       	var message = "";
			                                       	if(jsonData["status"] == "SUCCESS") {
			                                       			message += "Operation completed SUCCESSFULLY!!!";
			                                       			message += jsonData["message"]; 
			                                       	}  else {
			                                       		message += "ERROR !!!";
		                                       			message += jsonData["message"];
			                                       	} 	
			                                        Y.one('#results').set('innerText', message);
			                                        started = false;
		                                     },
		                                 	 error : function() {
		                                 		 Y.one('#results').set('innerText', "ERROR !!!");	 
		                                 		started = false;
		                                 	 }
		                                 }
		            		     });
					   } else {
						   Y.one('#results').set('innerText', "Unable to start operation!!! Incorrect parameters!!!");	   
					   }
				   });  
			 
				  dropdown = new Y.Dropdown(
				     {
				        boundingBox: '#groupIdDropdown',
				        trigger: '#groupIdTrigger', 						         
				        hideOnClickOutSide: true,
				        hideOnEsc: true,
				   }).render();
	});
	var dropdownClick = function(val, name) {
		YUI().use('aui-dropdown', function(Y) {
					  Y.one('#groupid_selected_value').set('innerText', name);
					  dropdown.value = val;
		});
		dropdown.close();	
	}
</script> 
<label class="control-label">Instruction</label>
<div class="well">This portlet is simple utility for import articles from Joomla! CMS database.<br>
1. Articles in Joomla! CMS database stored in table with postfix "_content" - find this table. <br>
and enter table name in field "Content table name".<br>
2. Specify JDBC Connection parameters to Joomla! CMS database.<br>
3. Specify Liferay Group with that articles would be associated.<br>
4. Start process and see Catalina.out and "Results of last import" control.<br>
Good luck!</div>

<div id="params-div">	 
	<div class="form-group">
		<label class="control-label" for="liferay_user">Liferay user</label>
		<div class="controls">
			<input name="liferay_user" id="liferay_user" class="form-control" type="text">
		</div>
	</div>	
	<div id="groupIdDropdown" class="dropdown">
		<button id="groupIdTrigger" class="btn btn-default dropdown-toggle" type="button">
	    	<span id="groupid_selected_value">Group Id</span>
	    	<span class="caret"></span>
		</button>
		<ul class="dropdown-menu">
		<% Integer counter = 0; for(Group group : PortalUtil.getUser(request).getGroups()) { %>
			<li><a id="groupId_<%=group.getGroupId()%>" onClick="dropdownClick('<%=group.getGroupId()%>', '<%=group.getName()%>')"  value="<%=group.getGroupId()%>" tabindex="<%=counter++%>" href="#"><%=group.getName()%></a></li>
		<% } %> 
		</ul>
	</div>
	<div class="form-group">
		<label class="control-label" for="content_table_name">Content table name</label>
		<div class="controls">
			<input name="content_table_name" id="content_table_name" class="form-control" type="text">
		</div>
	</div>		
	<div class="form-group">
		<label class="control-label" for="user">JDBC user</label>
		<div class="controls">
			<input name="user" id="user" class="form-control" type="text">
		</div>
	</div>	
	<div class="form-group">
		<label class="control-label" for="password">JDBC password</label>
		<div class="controls">
			<input name="password" id="password" class="form-control" type="password">
		</div>
	</div>		   
	<div class="form-group">
		<label class="control-label" for="url">JDBC url</label>
		<div class="controls">
			<input name="url" id="url" class="form-control" type="text">
		</div>
	</div>
	<div class="form-group">
		<label class="control-label" for="driver">JDBC driver</label>
		<div class="controls">
	  		<input name="driver" id="driver" class="form-control" type="text">
		</div>
	</div>	
	<input class="btn btn-info" id="params-form-submit" type="submit" value="Start" />
	<label class="control-label">Results of last import</label>
	<div id="results" class="well">...</div>
</div>
