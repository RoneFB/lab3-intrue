$(document).ready(function(){
	$('#btnEntrar').click(function(){    		
		var user = $('#txtLogin').val().trim();			
		var password = $('#txtSenha').val().trim();		

		var url = "/login/"+user+"/"+password;

		$.getJSON(url, function(data) 
		{
			sessionStorage.setItem("usuarioLogado", data.codigo);
			window.location.href = 'admin.html';
		});
	});
	  
	$("#btnSair").click(function(){
		sessionStorage.removeItem('usuarioLogado');
		window.location.href = 'login.html';
	});
	
	$("#btnCadUsuario").click(function() {
		var login = $("#txtLogin").val();
		var senha = $("#txtSenha").val();
		var email = $("#txtEmail").val();
		var nome = $("#txtNome").val();
		//var foto = $("#txtFoto").val();

		if($("#chkTermos").is(":checked")){
			var url = "/cadastrarUsuario/"+login+"/"+senha+"/"+email+"/"+nome+"";
   
			$.getJSON(url, function(data) 
			{
				alert("Usuário cadastrado com sucesso");
				window.location.href = 'login.html';
			});
		}else{
			alert("Favor concordar com os termos de uso");
		}
   	});
});