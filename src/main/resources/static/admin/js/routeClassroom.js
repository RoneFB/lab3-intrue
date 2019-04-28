function exibirAula(cod_curso){
    $("#lstCursos").hide();

    //atribui a variavel o valor da sessão
    var cod_usuario = sessionStorage.getItem("usuarioLogado");

    //define rota
    var url = "/usuarioCurso/"+cod_usuario+"/"+cod_curso+"";

    $.getJSON(url, function(data) 
    { 
        //define rota
        var url1 = "/curso/" + cod_curso;
       
        //carrega o curso
        $.getJSON(url1, function(data1) 
        {  
            //define variavel
            var aula;
            var menu = "";

            menu = menu + "<li class='header'>"+this.nome+"</li>";

            //carrega os modulos
            $.each(data1.modulos, function(i) 
            {
                menu = menu + "<li class='treeview'><a href='#'><i class='fa fa-link'></i><span>"+this.nome+"</span><span class='pull-right-container'><i class='fa fa-angle-left pull-right'></i></span></a><ul class='treeview-menu'>";

                //define o indice do vetor que se encontra o modulo especifico
                indexAula = data1.modulos.indexOf(data1.modulos[i]);

                //carrega as aulas
                $.each(data1.modulos[indexAula].aulas, function(i) 
                {
                    menu = menu + "<li><a id='"+this.cod+"' onClick='exibirAula(this.id)'>"+this.nome+"</a></li>";

                    if(this.cod === data.cod_aula){
                        aula = "<div class='box box-default'><div class='box-header with-border'><h3 class='box-title'>"+this.nome+"</h3></div><div class='box-body'><div class='row text-center'><br><iframe width='560' height='315' src='https://www.youtube.com/embed/kkOSweUhGZM' frameborder='0' allow='accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture' allowfullscreen></iframe></div><div class='box-body' style='padding:25px;'><h3>"+this.nome+"</h3><h4 class='text-justify'>"+this.conteudo+"</h4></div><div class='box-footer text-right' style='padding: 10px 0 0 0;'><input type='button' value='avançar' class='btn btn-lg btn-primary'></div></div></div>";
                    }
                });

                menu = menu + "</ul>";
            });

            menu = menu + "</li>";

            $("#contentClass").append(aula);
            $("#menuCourse").append(menu);
        });
    });
}

function redirect(){
    window.location.href="../index.html#portfolio";
}

$(document).ready(function() {
    //atribui a variavel o valor da sessão
    var cod_usuario = sessionStorage.getItem("usuarioLogado");

    //define rota
    var url = "/listarUsuarioCursos/"+cod_usuario+"";

    $.getJSON(url, function(data) 
    {   
        //define variaveis
        var itens = [];

        //carrega tabela dos cursos
        $.each(data, function(i) 
        {
            itens.push("<div class='box'><div class='box-header with-border'><h3 class='box-title'>"+this.categoria+"</h3><div class='box-tools pull-right'><button type='button' class='btn btn-box-tool' data-widget='collapse' data-toggle='tooltip' title='Minimizar'><i class='fa fa-minus'></i></button><button type='button' class='btn btn-box-tool' data-widget='remove' data-toggle='tooltip' title='Cancelar inscrição'><i class='fa fa-times'></i></button></div></div><div class='box-body' style='padding:25px;'><h2>"+this.nome+"</h2><h4 class='text-justify'>"+this.descricao+"</h4></div><div class='box-footer text-right'><input type='button' id='"+this.codigo+"' value='ir para o curso' class='btn btn-lg btn-primary' onClick='exibirAula(this.id)'></div></div>");
        });

        if(itens.length === 0){
            itens.push("<div class='text-center'><span class='glyphicon glyphicon-refresh' style='font-size:60px; color: #2cb6e8'></span><br><br><br><h1 style='color: #2cb6e8'>parece que você ainda não se inscreveu em nenhum curso</h1><br><br><br><button class='btn btn-primary' style='font-size: 24px;' onclick='redirect()'>ir para o catálogo de cursos</button></div>");
        }

        $("#lstCursos").append(itens);
    });  
});