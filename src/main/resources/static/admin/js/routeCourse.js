var url = window.location.search;
var cod_curso = url.replace('?','');

$(document).ready(function() {
    //define rota
    var url = "/curso/"+cod_curso+"";   

    var lstModulos;

    //chama rota e trata o retorno
    $.getJSON(url, function(data) 
    {  
        $("#lblNomeCurso").append(data.nome);
        $("#lblCategoriaCurso").append(data.categoria);
        data.descricao = data.descricao.replace(/(?:\r\n|\r|\n)/g, '<br />');
        $("#lblSobreCurso").append(data.descricao);

        lstModulos = "<h2 id='lblTituloNomeCurso'>"+data.nome+"</h2>";

        $.each(data.modulos, function(i) 
        {
            lstModulos = lstModulos + "<div class='row module'><div class='col-sm-2 hidden-xs text-center'><h3 style='margin-top:10px'>Módulo "+this.cod+"</h3></div><div class='col-sm-7 col-xs-8 text-left'><h3 style='margin-top:10px'><strong>"+this.nome+"</strong></h3></div><div class='col-sm-3 col-xs-4 text-right'><button class='btn btn-default btn-lg' data-toggle='collapse' data-target='#class"+this.cod+"'>ver aulas</button></div></div>";

            lstModulos = lstModulos + "<div id='class"+this.cod+"' class='row collapse class'>";

            indexAula = data.modulos.indexOf(data.modulos[i]);

            $.each(data.modulos[indexAula].aulas, function(j) 
            {
                lstModulos = lstModulos + "<div class='row'><div class='col-sm-2 col-xs-4 text-center'><h4 style='margin:5px 0 0 0'>Aula "+this.cod+"</h4></div><div class='col-sm-7 col-xs-4 text-left'><h4 style='margin:5px 0 0 0'><strong>"+this.nome+"</strong></h4></div><div class='col-sm-3 col-xs-4 text-right'>&nbsp;</div></div><hr>";
            });

            lstModulos = lstModulos + "</div>";
        });

        $("#list-modules").append(lstModulos);
    });

    $("#btnInscrever").click(function() {
        //atribui as variaveis o value dos campos
        var url = window.location.search;
        var cod_curso = url.replace('?','');
        
        var cod_usuario = sessionStorage.getItem("usuarioLogado");

        if(!cod_usuario){
            window.location.href = 'admin/login.html';
        }else{
            
            //define rota
            var url = "/inscreverCurso/"+cod_usuario+"/"+cod_curso+"";

            //chama rota e trata os retornos
            $.getJSON(url, function(data) {
                    
                if(data){
                    alert("Você já é inscrito neste curso");
                    window.location.href = 'admin/classroom.html';
                }else{
                    alert("Sua inscrição foi realizada com sucesso");
                    window.location.href = 'admin/classroom.html';
                }
            });
        }
    });
});