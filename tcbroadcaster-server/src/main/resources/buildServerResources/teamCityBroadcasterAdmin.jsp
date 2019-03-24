<html>
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script>jQuery.noConflict();</script>
    <script type="text/javascript">
    jQuery(function ($) {
    	$(document).ready(function(){
    		$('#makeCall').click(function(){
    			console.log("Element called");
   				var urla= $('#urla').val();
   				var usernamea= $('#usernamea').val();
   				var passworda= $('#passworda').val();
   				var project_persista= $('#project_persista').is(":checked");
   				var project_removeda= $('#project_removeda').is(":checked");
   				var build_finisheda= $('#build_finisheda').is(":checked");
   				var build_persisteda= $('#build_persisteda').is(":checked");
   				var vcs_persisteda = $('#vcs_persisteda').is(":checked");
   				
    			BS.ajaxRequest(base_uri + '/admin/initialise.html', {
    				parameters :'urla='+urla+'&usernamea='+usernamea+'&passworda='+passworda+'&project_persista='+project_persista+
    				'&project_removeda='+project_removeda+'&build_finisheda='+build_finisheda+'&build_persisteda='+build_persisteda
    				+'&vcs_persisteda='+vcs_persisteda,
    	            onComplete:function (data1, options) {
    	            	console.log("function complete");
    	            	$("#url").html(urla);
    	            	$("#username").html(usernamea);
    	            	$("#password").html(passworda);
    	             },

    	        });
              });
    	});
      });
    </script>
    
    <h1>Config</h1>
    
    <table class = "highlightable parametersTable" style>
    	<tbody>
        <tr>
          <th style="width: 30%;">Project Switches</th>
          <th colspan="2">Value</th>
        </tr>     
        <tr>
          <td>Project Persist</td>
          	<td id="project_persist">
          		<input type="checkbox" id="project_persista" <%=(Boolean)session.getAttribute("project_persist") ? "checked='checked'" : "" %>>
          		<span class="smallNote">
    				"Project config changes, creation and archiving"
    			</span>
    		</td>
        </tr>
        <tr>
          <td>Project Removed</td>
          	<td id="project_removed">
          		<input type="checkbox" id="project_removeda" <%=(Boolean)session.getAttribute("project_removed") ? "checked='checked'" : "" %>>
          		<span class="smallNote">
    				"Project removed"
    			</span>
    		</td>
        </tr>
      </tbody>
    </table>
    
    <table class = "highlightable parametersTable" style>
    	<tbody>
        <tr>
          <th style="width: 30%;">Build Switches</th>
          <th colspan="2">Value</th>
        </tr>     
        <tr>
          <td>Build Finished</td>
          	<td id="build_finished">
          		<input type="checkbox" id="build_finisheda" <%=(Boolean)session.getAttribute("build_finished") ? "checked='checked'" : "" %>>
          		<span class="smallNote">
    				"Listen for finished builds"
    			</span>
    		</td>
        </tr>
        <tr>
          <td>Build Persisted</td>
          	<td id="build_persisted">
          		<input type="checkbox" id="build_persisteda" <%=(Boolean)session.getAttribute("build_persisted") ? "checked='checked'" : "" %>>
          		<span class="smallNote">
    				"Listen for build config changes"
    			</span>
    		</td>
        </tr>
      </tbody>
    </table>
    <table class = "highlightable parametersTable" style>
    	<tbody>
    		<tr>
          		<th style="width: 30%;">VCS Switches</th>
         		<th colspan="2">Value</th>
        	</tr>
        	<tr>
        		<td>VCS Persisted</td>
        		<td id="vcs_persisted">
        			<input type="checkbox" id="vcs_persisteda" <%=(Boolean)session.getAttribute("vcs_persisted") ? "checked='checked'" : "" %>>
          			<span class="smallNote">
    					"Listen for vcs config changes changes"
    				</span>
        		</td>
        	</tr> 
    	</tbody>
    </table>
    
    	<table class="runnerFormTable">
    	<tbody>
    	<tr class="groupingTitle">
    		<td colspan="2">Configuration</td>
    	</tr>
    	<form>
		<tr>
			<th>URL</th> 
			<td>
				<input type="text" id="urla" size="50" value=<%= (String)session.getAttribute("url") %>>
				<span class="smallNote">
    				"Enter the url where your listener method is hosted. "
    			</span>
			</td>
		</tr>
		<tr>
			<th>username</th>
			<td><input type="text" id="usernamea" size="50" value=<%= (String)session.getAttribute("username") %>>
				<span class="smallNote">
    				"Username""
    			</span>
    		</td>
		</tr>
		<tr>
			<th>Password</th>
			<td><input type="password" id="passworda" size="50" value=<%= (String)session.getAttribute("password") %>>
				<span class="smallNote">
    				"password"
    			</span>
    		</td>
		</tr>
		<tr>
			<th><input type="button" id="makeCall" class="btn btn_primary" value ="Set endpoint parameters"></th>
			<td></td>
		</tr>
    	</form>
    	</tbody>
    	</table>
    	
    <div id="response"></div>
</html>