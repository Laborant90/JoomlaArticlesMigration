
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<portlet:defineObjects />
<portlet:resourceURL var="ajaxResourceURL" /> 
<script src="<%=request.getContextPath()%>/js/jquery.min.js"></script> 
<script src="http://cdn.alloyui.com/3.0.1/aui/aui-min.js"></script>
<link href="<%=request.getContextPath()%>/css/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet"></link>
<script type="text/javascript">
	//#sourceURL=dynamicScript.js 
	 
	YUI().use('aui-tree-view',
			  'event-contextmenu',
			  'aui-io-request',
			  'overlay',
			  function(Y) {

				  Y.one('#params-form-submit').on('click', function() {
					   Y.io.request(
		            		      '<%=ajaxResourceURL.toString()%>',
		            		      {		            		    	  
		                                  data:  {							                                              
		                                             '<portlet:namespace/>user'     : Y.one('#user').get('value'), 
		                                             '<portlet:namespace/>password' : Y.one('#password').get('value'), 
		                                             '<portlet:namespace/>url'      : Y.one('#url').get('value'), 
		                                             '<portlet:namespace/>driver'   : Y.one('#driver').get('value'),
		                                         } 
		                                 ,
		                                 method: 'POST',
		                                 on: {
		                                     success: function() {
		                                    	 
		                                     }
		                                 }
	      						});
				   });
				  Y.one('#params-form-reset').on('click', function() {
					Y.one('#user').set('value', ""); 
                 	Y.one('#password').set('value', "");   
                 	Y.one('#url').set('value', "");  
                 	Y.one('#driver').set('value', "");   
				  });
	});
</script> 
 
<div id="params-div">
		<form id="params-form">
			  <div class="form-group">
			    <label class="control-label" for="user">user</label>
			    <div class="controls">
			      <input name="user" id="user" class="form-control" type="text">
			    </div>
			  </div>	
			   <div class="form-group">
			    <label class="control-label" for="password">password</label>
			    <div class="controls">
			      <input name="password" id="password" class="form-control" type="password">
			    </div>
			  </div>		   
			  <div class="form-group">
			    <label class="control-label" for="url">url</label>
			    <div class="controls">
			      <input name="url" id="url" class="form-control" type="text">
			    </div>
			  </div>
			   <div class="form-group">
			    <label class="control-label" for="driver">driver</label>
			    <div class="controls">
			      <input name="driver" id="driver" class="form-control" type="text">
			    </div>
			  </div>		
			  <input class="btn btn-info" id="params-form-submit" type="submit" value="Submit">
			  <input class="btn btn-primary" id="params-form-reset" type="reset" value="Reset">
		  </form> 	
</div>