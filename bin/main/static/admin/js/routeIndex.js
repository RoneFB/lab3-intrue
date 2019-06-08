var url = "/cursos/";

function redirect(cod_curso){
    window.location.href = "course.html?" + cod_curso;
}

$(document).ready(function() {
    $.getJSON(url, function(data) 
    {   
        var itens = [];

        var cursos = "<div class='row text-center'>";

        //contador
        var cont = 1;

        //carrega tabela dos cursos
        $.each(data, function(i) 
        {
            if(cont < 7){
                itens.push("<div class='col-lg-2 col-md-3 col-sm-4'><div class='panel panel--courses"+cont+" panel-default text-center'><div class='panel-heading panel-heading--courses"+cont+"'>&nbsp;</div><div class='panel-body panel-body--courses'><img src='"+this.thumbnail+"' width='100%' onClick='redirect("+this.codigo+")'></div><div class='panel-footer panel-footer--courses"+cont+"'><h4>"+this.nome+"</h4></div></div></div>");
            }

            if((cont - 1) % 3 === 0 && (cont - 1) !== 0){
                cursos = cursos + "<div class='row text-center'>";
            }
            
            if(cont % 3 === 0){
                cursos = cursos + "<div class='col-sm-4'><div class='thumbnail' style='cursor: pointer' onclick='redirect("+this.codigo+")'><img src='"+this.thumbnail+"'><p><strong>"+this.categoria+"</strong></p><p>"+this.nome+"</p></div></div></div>";
            }else{
                cursos = cursos + "<div class='col-sm-4'><div class='thumbnail' style='cursor: pointer' onclick='redirect("+this.codigo+")'><img src='"+this.thumbnail+"'><p><strong>"+this.categoria+"</strong></p><p>"+this.nome+"</p></div></div>";
            }

            cont++;
        });

        var cursos = cursos + "</div>";

        $("#list-main-courses").append(itens);
        $("#list-courses").append(cursos);
    });
});

