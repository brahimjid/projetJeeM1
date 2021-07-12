let ids =[];
let articles_ids =[];
let articles_pu =[];
let articles_pt =[];
let articles_qt =[];

const table = $("#stockEntreeTable");
let opData ={
    "montant":0,
    "type":1,
    "operationItems":[]
};
function  appendOperation(data){

    if ($.inArray(data.id,ids)>=0){
        alert(" in ");
        return;
    }
    else{
        ids.push(data.id);
        articles_ids.push(data.id);
        articles_pu.push(data.prix);
        articles_pt.push(data.prix);
        articles_qt.push(1);
    }


    table.children("tbody").append(` <tr data-id="${articles_ids.length}">
                                <td style="width: 30%">${data.libelle}</td>
                                <td style="width: 20%">

                                        <input type="number" id="qte_input" value="1" class="text-center form-control mx-1">

                                </td>
                                <td><input name="prix_unitaire[]" readonly="readonly" class="form-control" type="number" id="prix_unitaire_input" value="${data.prix}"></td>
                                <td >
                                   <div class="d-flex">
                                   <input readonly="readonly" class="form-control" type="number" id="prix_total_input"
                                value="${data.prix}">
                                <button id="deleteRowBtn" class="btn btn-sm ms-2 btn-danger"
                                style="height: 27px;margin-top: 5px;"><i class="fa fa-times"></i></button>
                                   </div>

                                </td>

                            </tr>`);

    $("#countItem").text(ids.length)
    total();

    console.log(opData)

    opData.operationItems = articles_ids.map((item, idx) => {
        return {
            "article": {
                "id": item
            },
            "quantite": articles_qt[idx],
            "prix_unitaire": articles_pu[idx],
            "prix_total": articles_pt[idx]
        }
    });
}



  function insert(){


          $.ajax({
              url:API_URL +"operations",
              type:"POST",
              data:JSON.stringify(opData),
              contentType: 'application/json; charset=utf-8',
              dataType: 'json',
              success:function (data){
                  showModal("successModal");
                  cleanup();

              },
              error:function (err){
                  console.log(err)
              }
          })
  }



// function total() {
//
//     let montant_Total = 0;
//
//     table.find('td #prix_total_input').each(function () {
//         let prixTotal = $(this).val() - 0;
//         montant_Total = montant_Total + prixTotal;
//     });
//     $('#totalOperation').text(montant_Total + ' UM');
//     $('#montant').val(montant_Total);
//     opData.montant = montant_Total;
// }

function onchange(){
    table.on('keyup','#qte_input',function(){
        let id = ($(this).closest('tr').data("id")),
            prix_input =$(this).closest('tr').find('#prix_unitaire_input').val(),
            qte_input = $(this).closest('tr').find('#qte_input').val(),
            prix_total = prix_input * qte_input;
        $(this).closest('tr').find('#prix_total_input').val(prix_total);
        total();
        opData.operationItems[id-1].prix_total = prix_total;
        opData.operationItems[id-1].quantite = qte_input;
        articles_pt[id-1] =prix_total;
        articles_qt[id-1] =qte_input;
        // console.log(articles_pt[id-1] =prix_total);
    });
}
// function removeRow() {
//     table.on('click', '#deleteRowBtn', function () {
//         let id = ($(this).closest('tr').data("id"));
//         console.log(id)
//         $(this).closest('tr').remove();
//         delete opData.operationItems.splice(id-1,1);
//         total();
//         // console.log(ids.length);
//         // console.log(opData);
//         $("#countItem").text(opData.operationItems.length);
//           if (opData.operationItems.length===0){
//              ids = [];
//           }
//         ids.splice(id-1,1);
//     });
//
// }
// removeRow();
onchange();
// function cleanup(){
//     table.children('tbody').html('');
//     $("#montant").val(0)
//     $("#totalOperation").text("0 UM");
//     $("#countItem").text("0");
//     ids=[];
//     articles_qt=[];
//     articles_ids=[];
//     articles_pt=[];
//     articles_pt=[];
//     opData.operationItems=[];
// }