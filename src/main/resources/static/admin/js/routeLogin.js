
$(document).ready(function(){
	$('#btnEntrar').click(function(){    
		
		var user = $('#txtLogin').val().trim();
		
		var password = $('#txtSenha').val().trim();
		
		var url = "/login/"+user+"/"+password;
		
		$.getJSON(url, function(data) {
			sessionStorage.setItem("usuarioLogado", data.nome);
			window.location.href = 'notifyLogin.html';	
			/*if(data.status == true)8
				sessionStorage.setItem("usuarioLogado", data.nome);
				window.location.href = 'admin.html';	
			}	*/	 
		}); 
		window.location.href = 'notifyLogin.html';	
		
	});
	
	  /*Foi Adicionado o logout*/
    $("#btnSair").click(function(){
    	sessionStorage.removeItem('usuarioLogado');
    	window.location.href = 'login.html';
    })
	
	$(document).ready(function(){
		
		$("#btnCadUsuario").click(function() {
			
		 var login = $("#txtLogin").val();
	     var senha = $("#txtSenha").val();
	     var email = $("txtEmail").val();
	     var nome = $("txtNome").val();
	            
		var url = "/cadastrarUsuario/"+login+"/"+senha+"/"+email+"/"+nome+"";
		
		
		    $.getJSON(url, function(data) 
		    {   
	            $.getJSON(url, function(data) {
	            	window.location.href = 'login.html';
	            });
		    });
	    
	    
	    });   
		
	})
	
});