var url = "/cursos/";
var codCurso = '';
var codModulo = '';
var codAula = '';
var maxCodCurso = 0;
var maxCodModulo = 0;
var maxCodAula = 0;
var indexAula;

function limparCampos(){
    //Curso
    $("#txtCadNomeCurso").val("");
    $("#lstCadCategoriaCurso").val("Back-End");
    $("#txtCadDescricaoCurso").val("");
    $("#txtCadDuracaoCurso").val("");

    //Modulo
    $("#txtCadNomeModulo").val("");
    $("#txtCadCodCurso").val("");

    //Aula
    $("#txtCadNomeAula").val("");
    $("#txtCadCodModulo").val("");
    $("#txtCadConteudoAula").val("");
    $("#txtCadLinkAula").val("");
}

function limparModulo(){
    $("#txtCadNomeModulo").val("");
    $("#txtCadNomeAula").val("");
    $("#txtCadCodModulo").val("");
    $("#txtCadConteudoAula").val("");
    $("#txtCadLinkAula").val("");
}

function limparAula(){
    $("#txtCadNomeAula").val("");
    $("#txtCadConteudoAula").val("");
    $("#txtCadLinkAula").val("");
}

function buscarCursoCod(cod){
    //recebe parametros
    codCurso = cod;

    //limpa tabelas
    $("#tblModulo").empty();
    $("#tblAula").empty();

    //define rota
    var url = "/curso/" + codCurso;

    //chama a rota e trata o retorno
    $.getJSON(url, function(data) 
    {   
        var itens = [];
        var itens2 = [];

        //carrega formulario do curso   
        $("#txtCadNomeCurso").val(data.nome);
        $("#txtCadDuracaoCurso").val(data.duracao);
        $("#lstCadCategoriaCurso").val(data.categoria);
        $("#txtCadDescricaoCurso").val(data.descricao);
        $("#txtCadCodCurso").val(data.codigo);

        //carrega tabela dos modulos
        $.each(data.modulos, function(i) 
        {
            itens.push("<tr> <td>" + this.cod + "</td><td>" + this.nome + "</td><td class='text-center'><img id='"+this.cod+"' src='../img/edit.svg' alt='' width='20px' style='cursor:pointer' onClick='buscarModuloCod(this.id)'></td><td class='text-center'>" + "<img id='"+this.cod+"' src='../img/delete.svg' alt='' width='20px' style='cursor:pointer' onClick='excluirModulo(this.id)'>" + "</td></tr>");

            maxCodModulo = this.cod;
        });

        $("#tblModulo").append(itens);        

        $("#btnSalvarCurso").show();
        $("#btnCadCurso").hide();

        //limpa campos
        limparModulo();
    });
}

function buscarModuloCod(cod){
    //recebe parametros
    codModulo = cod;
    
    //limpa tabela
    $("#tblAula").empty();

    //define rota
    var url = "/curso/" + codCurso;

    //chama rota e trata o retorno
    $.getJSON(url, function(data) 
    {   
        var itens = [];                

        //percorre lista de modulos
        $.each(data.modulos, function(i) 
        {
            //carrega formulario do modulo
            if (this.cod == codModulo){
                $("#txtCadNomeModulo").val(this.nome);
                $("#txtCadCodCurso").val(this.cod_curso);
                $("#txtCadCodModulo").val(this.cod);

                //define o indice do vetor que se encontra o modulo especifico
                indexAula = data.modulos.indexOf(data.modulos[i]);
            }
        });

        itens.push("<tr> <td colspan='3'><strong>" + data.modulos[indexAula].nome + "</strong></td></tr>");

        //carrega tabela das aulas
        $.each(data.modulos[indexAula].aulas, function(i) 
        {
            itens.push("<tr> <td>" + this.cod + "</td><td>" + this.nome + "</td><td class='text-center'><img id='"+this.cod+"' src='../img/edit.svg' alt='' width='20px' style='cursor:pointer' onClick='buscarAulaCod(this.id)'></td><td class='text-center'>" + "<img id='"+this.cod+"' src='../img/delete.svg' alt='' width='20px' style='cursor:pointer' onClick='excluirAula(this.id)'>" + "</td></tr>");

            maxCodAula = this.cod;
        });  

        $("#tblAula").append(itens);

        
        $("#btnSalvarModulo").show();
        $("#btnCadModulo").hide();

        //limpa campos
        limparAula();
    });
}

function buscarAulaCod(cod){
    //recebe parametros
    codAula = cod;
    
    //define rota
    var url = "/curso/" + codCurso;

    //chama rota e trata o retorno
    $.getJSON(url, function(data) 
    {  
        //percorre lista de aulas
        $.each(data.modulos[indexAula].aulas, function(i) 
        {
            //carrega formulario da aula
            if (this.cod == codAula){
                $("#txtCadNomeAula").val(this.nome);
                $("#txtCadCodModulo").val(this.cod_modulo);
                $("#txtCadConteudoAula").val(this.conteudo);
                $("#txtCadLinkAula").val(this.video);
            }
        });

        $("#btnSalvarAula").show();
        $("#btnCadAula").hide();
    });
}

function excluirCurso(cod){
    //recebe parametros
    var codCurso = cod;

    if(confirm("Atenção! Você tem certeza que deseja excluir esse curso?\nEsta ação não poderá ser desfeita")){
        //limpa tabela
        $("#tblCurso").empty();

        //define rota
        var url = "/excluirCurso/"+codCurso+"";    

        //chama rota e trata o retorno
        $.getJSON(url, function(data) 
        {  
            var itens = [];

            //carrega tabela dos cursos
            $.each(data, function(i) 
            {
                itens.push("<tr> <td>" + this.codigo + "</td><td>Programação em " + this.nome + "</td><td class='text-center'><img id='"+this.codigo+"' src='../img/edit.svg' alt='' width='20px' style='cursor:pointer' onClick='buscarCursoCod(this.id)'></td><td class='text-center'>" + "<img id='"+this.codigo+"' src='../img/delete.svg' alt='' width='20px' style='cursor:pointer' onClick='excluirCurso(this.id)'>" + "</td></tr>");

                maxCodCurso = this.codigo;
            });
            
            $("#tblCurso").append(itens);
        });

        alert("Exclusão realizada com sucesso");

        //recarrega a tela
        location.reload();
    }
}

function excluirModulo(cod){
    //recebe parametros
    var codModulo = cod;

    if(confirm("Atenção! Você tem certeza que deseja excluir esse módulo?\nEsta ação não poderá ser desfeita")){
        //limpa tabela
        $("#tblModulo").empty();

        //define rota
        var url = "/excluirModulo/"+codModulo+"/"+codCurso+"";    

        //chama rota e trata o retorno
        $.getJSON(url, function(data) 
        {  
            var itens = [];

            //carrega tabela dos cursos
            $.each(data, function(i) 
            {
                itens.push("<tr> <td>" + this.cod + "</td><td>" + this.nome + "</td><td class='text-center'><img id='"+this.cod+"' src='../img/edit.svg' alt='' width='20px' style='cursor:pointer' onClick='buscarModuloCod(this.id)'></td><td class='text-center'>" + "<img id='"+this.cod+"' src='../img/delete.svg' alt='' width='20px' style='cursor:pointer' onClick='excluirModulo(this.id)'>" + "</td></tr>");

                maxCodModulo = this.cod;
            });
            
            $("#tblModulo").append(itens);
        });

        alert("Exclusão realizada com sucesso");

        //limpa campos
        $("#txtCadNomeModulo").val("");
    }
}

function excluirAula(cod){
    //recebe parametros
    var codAula = cod;

    if(confirm("Atenção! Você tem certeza que deseja excluir essa aula?\nEsta ação não poderá ser desfeita")){
        //limpa tabela
        $("#tblAula").empty();

        //define rota
        var url = "/excluirAula/"+codAula+"/"+codModulo+"/"+codCurso+"";    

        //chama rota e trata o retorno
        $.getJSON(url, function(data) 
        {  
            var itens = [];

            //carrega tabela dos cursos
            $.each(data, function(i) 
            {
                itens.push("<tr> <td>" + this.cod + "</td><td>" + this.nome + "</td><td class='text-center'><img id='"+this.cod+"' src='../img/edit.svg' alt='' width='20px' style='cursor:pointer' onClick='buscarAulaCod(this.id)'></td><td class='text-center'>" + "<img id='"+this.cod+"' src='../img/delete.svg' alt='' width='20px' style='cursor:pointer' onClick='excluirAula(this.id)'>" + "</td></tr>");

                maxCodAula = this.cod;
            });
            
            $("#tblAula").append(itens);
        });

        alert("Exclusão realizada com sucesso");

        //limpa campos
        limparAula();
    }
}

$(document).ready(function() {
    $.getJSON(url, function(data) 
    {   
        var itens = [];

        //carrega tabela dos cursos
        $.each(data, function(i) 
        {
            itens.push("<tr> <td>" + this.codigo + "</td><td>Programação em " + this.nome + "</td><td class='text-center'><img id='"+this.codigo+"' src='../img/edit.svg' alt='' width='20px' style='cursor:pointer' onClick='buscarCursoCod(this.id)'></td><td class='text-center'>" + "<img id='"+this.codigo+"' src='../img/delete.svg' alt='' width='20px' style='cursor:pointer' onClick='excluirCurso(this.id)'>" + "</td></tr>");

            maxCodCurso = this.codigo;
        });

        $("#tblCurso").append(itens);

        $("#btnCadCurso").click(function() {
            //atribui as variaveis o value dos campos
            var nome = $("#txtCadNomeCurso").val();
            var duracao = $("#txtCadDuracaoCurso").val();
            var categoria = $( "#lstCadCategoriaCurso option:selected" ).text();
            var descricao = $("#txtCadDescricaoCurso").val();
            var codigo = maxCodCurso + 1;
                
            //limpa tabela
            $("#tblCurso").empty();
                
            //define rota
            var url = "/cadastrarCurso/"+codigo+"/"+nome+"/"+categoria+"/"+descricao+"/"+duracao+"";

            //chama rota e trata retorno
            $.getJSON(url, function(data) {
                    
                var itens = [];
                
                //carrega tabela dos cursos
                $.each(data, function(i) 
                {
                    itens.push("<tr> <td>" + this.codigo + "</td><td>Programação em " + this.nome + "</td><td class='text-center'><img id='"+this.codigo+"' src='../img/edit.svg' alt='' width='20px' style='cursor:pointer' onClick='buscarCursoCod(this.id)'></td><td class='text-center'>" + "<img id='"+this.codigo+"' src='../img/delete.svg' alt='' width='20px' style='cursor:pointer' onClick='excluirCurso(this.id)'>" + "</td></tr>");

                    maxCodCurso = this.codigo;
                });

                $("#tblCurso").append(itens);
            });

            alert("Cadastro realizado com sucesso");

            //recarrega a tela
            location.reload();
        });

        $("#btnCadModulo").click(function() {
            //atribui as variaveis o value dos campos
            var nome = $("#txtCadNomeModulo").val();
            var codCurso = $("#txtCadCodCurso").val();
            var codigo = maxCodModulo + 1;

            //limpa tabela
            $("#tblModulo").empty();
                
            //define rota
            var url = "/cadastrarModulo/"+codigo+"/"+codCurso+"/"+nome+"";

            //chama rota e trata o retorno
            $.getJSON(url, function(data) {
                    
                var itens = [];
                
                //carrega tabela dos modulos
                $.each(data, function(i) 
                {
                    itens.push("<tr> <td>" + this.cod + "</td><td>" + this.nome + "</td><td class='text-center'><img id='"+this.cod+"' src='../img/edit.svg' alt='' width='20px' style='cursor:pointer' onClick='buscarModuloCod(this.id)'></td><td class='text-center'>" + "<img id='"+this.cod+"' src='../img/delete.svg' alt='' width='20px' style='cursor:pointer' onClick='excluirModulo(this.id)'>" + "</td></tr>");

                    maxCodModulo = this.cod;
                });

                $("#tblModulo").append(itens);
            });

            alert("Cadastro realizado com sucesso");

            $("#txtCadNomeModulo").val("");
        });

        $("#btnCadAula").click(function() {
            //atribui as variaveis o value dos campos
            var nome = $("#txtCadNomeAula").val();
            var codModulo = $("#txtCadCodModulo").val();
            var conteudo = $("#txtCadConteudoAula").val();
            var link = $("#txtCadLinkAula").val();
            var codigo = maxCodAula + 1;

            //limpa tabela
            $("#tblAula").empty();
                
            //define rota
            var url = "/cadastrarAula/"+codigo+"/"+codModulo+"/"+nome+"/"+link+"/"+conteudo+"/"+codCurso+"";

            //chama rota e trata os retornos
            $.getJSON(url, function(data) {
                    
                var itens = [];

                itens.push("<tr> <td colspan='3'><strong>" + $("#txtCadNomeModulo").val() + "</strong></td></tr>");
                
                //carrega tabela das aulas
                $.each(data, function(i) 
                {
                    itens.push("<tr> <td>" + this.cod + "</td><td>" + this.nome + "</td><td class='text-center'><img id='"+this.cod+"' src='../img/edit.svg' alt='' width='20px' style='cursor:pointer' onClick='buscarAulaCod(this.id)'></td><td class='text-center'>" + "<img id='"+this.cod+"' src='../img/delete.svg' alt='' width='20px' style='cursor:pointer' onClick='excluirAula(this.id)'>" + "</td></tr>");

                    maxCodAula = this.cod;
                });

                $("#tblAula").append(itens);
            });

            alert("Cadastro realizado com sucesso");

            limparAula();
        });

        $("#btnSalvarCurso").click(function() {
            //atribui as variaveis o value dos campos
            var nome = $("#txtCadNomeCurso").val();
            var duracao = $("#txtCadDuracaoCurso").val();
            var categoria = $( "#lstCadCategoriaCurso option:selected" ).text();
            var descricao = $("#txtCadDescricaoCurso").val();
                
            //limpa tabela
            $("#tblCurso").empty();
                
            //define rota
            var url = "/alterarCurso/"+codCurso+"/"+nome+"/"+categoria+"/"+descricao+"/"+duracao+"";

            //chama rota e trata retorno
            $.getJSON(url, function(data) {
                    
                var itens = [];
                
                //carrega tabela dos cursos
                $.each(data, function(i) 
                {
                    itens.push("<tr> <td>" + this.codigo + "</td><td>Programação em " + this.nome + "</td><td class='text-center'><img id='"+this.codigo+"' src='../img/edit.svg' alt='' width='20px' style='cursor:pointer' onClick='buscarCursoCod(this.id)'></td><td class='text-center'>" + "<img id='"+this.codigo+"' src='../img/delete.svg' alt='' width='20px' style='cursor:pointer' onClick='excluirCurso(this.id)'>" + "</td></tr>");

                    maxCodCurso = this.codigo;
                });

                $("#tblCurso").append(itens);

                $("#btnSalvarCurso").hide();
                $("#btnCadCurso").show();
            });

            alert("Alterações realizadas com sucesso");

            //recarrega a tela
            location.reload();
        });

        $("#btnSalvarModulo").click(function() {
            //atribui as variaveis o value dos campos
            var nome = $("#txtCadNomeModulo").val();
            var codCurso = $("#txtCadCodCurso").val();

            //limpa tabela
            $("#tblModulo").empty();
                
            //define rota
            var url = "/alterarModulo/"+codModulo+"/"+codCurso+"/"+nome+"";

            //chama rota e trata o retorno
            $.getJSON(url, function(data) {
                    
                var itens = [];
                
                //carrega tabela dos modulos
                $.each(data, function(i) 
                {
                    itens.push("<tr> <td>" + this.cod + "</td><td>" + this.nome + "</td><td class='text-center'><img id='"+this.cod+"' src='../img/edit.svg' alt='' width='20px' style='cursor:pointer' onClick='buscarModuloCod(this.id)'></td><td class='text-center'>" + "<img id='"+this.cod+"' src='../img/delete.svg' alt='' width='20px' style='cursor:pointer' onClick='excluirModulo(this.id)'>" + "</td></tr>");

                    maxCodModulo = this.cod;
                });

                $("#tblModulo").append(itens);

                $("#btnSalvarModulo").hide();
                $("#btnCadModulo").show();
            });

            alert("Alterações realizadas com sucesso");

            $("#txtCadNomeModulo").val("");
        });

        $("#btnSalvarAula").click(function() {
            //atribui as variaveis o value dos campos
            var nome = $("#txtCadNomeAula").val();
            var codModulo = $("#txtCadCodModulo").val();
            var conteudo = $("#txtCadConteudoAula").val();
            var link = $("#txtCadLinkAula").val();

            //limpa tabela
            $("#tblAula").empty();
                
            //define rota
            var url = "/alterarAula/"+codAula+"/"+codModulo+"/"+nome+"/"+link+"/"+conteudo+"/"+codCurso+"";

            //chama rota e trata os retornos
            $.getJSON(url, function(data) {
                    
                var itens = [];

                itens.push("<tr> <td colspan='3'><strong>" + $("#txtCadNomeModulo").val() + "</strong></td></tr>");
                
                //carrega tabela das aulas
                $.each(data, function(i) 
                {
                    itens.push("<tr> <td>" + this.cod + "</td><td>" + this.nome + "</td><td class='text-center'><img id='"+this.cod+"' src='../img/edit.svg' alt='' width='20px' style='cursor:pointer' onClick='buscarAulaCod(this.id)'></td><td class='text-center'>" + "<img id='"+this.cod+"' src='../img/delete.svg' alt='' width='20px' style='cursor:pointer' onClick='excluirAula(this.id)'>" + "</td></tr>");

                    maxCodAula = this.cod;
                });

                $("#tblAula").append(itens);

                $("#btnSalvarAula").hide();
                $("#btnCadAula").show();
            });

            alert("Alterações realizadas com sucesso");

            //limpa campos
            limparAula();
        });
    });            
});