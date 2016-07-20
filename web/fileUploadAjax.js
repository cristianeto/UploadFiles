/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
            

    $('#insertar').submit( function(event){
        event.preventDefault();
    var form = $(this);
    var formdata = false;
    if (window.FormData){
        formdata = new FormData(form[0]);
        formdata.append('funcion', 'actualizar');//Aumentando valores a nuestro formdata
    }
        alert("oli. "+$("input[name='accion']",form).val());//$("input[name=accion]").val());

    var formAction = form.attr('action');//Almacenando en la variable formAction el valor de action del form
    $.ajax({
        url         : formAction+"?accion="+$("input[name=accion]").val(),
        data        : formdata,
        cache       : false,
        contentType : false,
        processData : false,
        type        : 'POST'
        
       }) .done(function(data){
            alert(" succes: "+data.mensaje);
            $("div#resultado").append("<a href=\"data:application/pdf;base64,"+data.rutaEname+"\" target=\"_blank\">Visualizar</a>");
            $("div#resultado").append("<a href=\"data:application/pdf;base64,"+data.rutaEresponsable+"\" target=\"_blank\">Visualizar</a>");
        
    });
});
    
//    
//    $('form#insertar').submit(function (event) {
//        alert("enombre");
//        var formDatos = new FormDatos("#insertar");
////        var formDatos = {
////            'accion': 'Guardar',
////            'nombre': $("input[name=descripcion]").val,
////            'enombre': $("input[name=enombre]").val(),
////            'eresponsable': $("input[name=eresponsable]").val()
////        };
//        
//        $.ajax({//ajax para insertar, llama al controlador
//            url: "./uploadFileServlet",
//            data: formDatos,
//            type: 'POST',
//            async: false,
//            dataType: 'json',
//            cache: false,
//            processData: false,
//            contentType: false,
//            encode: true
//        })
//                .done(function (response) {
//                    alert('aki' + response.resultado);
//                    if (response.resultado === true) {
//
//                    } else {
//
//                    }
//                })
//                .fail(function () {
//                    alert("ERROR: Algo salió mal en el controlador. ");
//                }).complete(function () {
//            $('#modNuevo').modal('hide');
//        });
//        event.preventDefault();
//    });

});