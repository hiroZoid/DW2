/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function openEditForm(tupla, pkCol) {
    //.*^(?!(^0*1$|^0*2$)).*$
    for (var atributo in tupla) {
        $('#editEntityModalWindow [name=' + atributo + ']')
                .val(tupla[atributo])
                .attr('readonly', (atributo === pkCol ? true : false));
    }
    $('.modal-title').text('Editar');
    $('#editEntityModalWindow input[type=submit]').val('Alterar');
    $('#editEntityModalWindow').modal('show');
}

function openNewForm() {
    $('.modal-title').text('Novo');
    $('#editEntityModalWindow input').attr('readonly', false);
    $('#editEntityModalWindow input').val('');
    $('#editEntityModalWindow select').val('');
    $('#editEntityModalWindow input[type=submit]').val('Salvar');
    $('#editEntityModalWindow').modal('show');
}

function canParseDate() {
    var canParse = !isNaN(Date.parse($('input[type=date]').val()));
    if (!canParse) {
        alert("Data inválida!");
    }
    return canParse;
}