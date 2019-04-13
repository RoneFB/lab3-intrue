$(document).ready(function(){
	$('form').submit(function(e){ 
		
		e.preventDefault();
		
		var user = $('#txtLogin').val().trim();		
		
		var password = $('#txtSenha').val().trim();	
		
		//var url = "/login/"+user+"/"+password;
		
		$.post("/login/usuario", JSON.stringify({'usuario': user, 'senha': password}), function(data)
		{
			if(data[0].tipo == "admin"){
				sessionStorage.setItem("usuarioLogado", data.nome);
				window.location.href = 'admin.html';
				//alert("login realizado");
			}else{
				window.location.href = 'login.html';
				alert("erro");
			}
		}, "json" );	
	});
	  
	$("#btnSair").click(function(){
		sessionStorage.removeItem('usuarioLogado');
		window.location.href = 'login.html';
	});
	
	$("#btnCadUsuario").click(function() {
		var login = $("#txtLogin").val();
		var senha = $("#txtSenha").val();
		var email = $("txtEmail").val();
		var nome = $("txtNome").val();
		   
		var url = "/cadastrarUsuario/"+login+"/"+senha+"/"+email+"/"+nome+"";
   
		$.getJSON(url, function(data) 
		{
			alert("Usuário cadastrado com sucesso");
			window.location.href = 'login.html';
		});
   	});
});