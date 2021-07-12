
function cleanup(){
    table.children('tbody').html('');
    $("#montant").val(0)
    $("#totalOperation").text("0 UM");
    $("#countItem").text("0");
    ids=[];
    articles_qt=[];
    articles_ids=[];
    articles_pt=[];
    articles_pu=[];
    opData.operationItems=[];
}

function removeRow() {
    table.on('click', '#deleteRowBtn', function () {
        let id = ($(this).closest('tr').data("id"));
        console.log(id)
        $(this).closest('tr').remove();
        delete opData.operationItems.splice(id-1,1);
        total();
        // console.log(ids.length);
        // console.log(opData);
        $("#countItem").text(opData.operationItems.length);
        if (opData.operationItems.length===0){
            ids = [];
        }
        ids.splice(id-1,1);
    });

}
removeRow();

function total() {

    let montant_Total = 0;

    table.find('td #prix_total_input').each(function () {
        let prixTotal = $(this).val() - 0;
        montant_Total = montant_Total + prixTotal;
    });
    $('#totalOperation').text(montant_Total + ' UM');
    $('#montant').val(montant_Total);
    opData.montant = montant_Total;
}