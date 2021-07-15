
let ids =[];
let articles_ids =[];
let articles_pu =[];
let articles_pt =[];
let articles_qt =[];
const table = $("#stockSortieTable");
let opData ={
    "montant":0,
    "type":2,
    "operationItems":[]
};


function  appendOperation(data){

    if ($.inArray(data.id,ids)>=0){
        alert(" in ");
        return;
    }
    else{
        ids.push(data.id);
        articles_ids.push(data.article.id);
        articles_pu.push(data.article.prix);
        articles_pt.push(data.article.prix);
        articles_qt.push(1);
    }


    table.children("tbody").append(` <tr data-id="${articles_ids.length}">
                                <td style="width: 30%">${data.article.libelle}</td>
                                <td style="width: 20%">

                                        <input type="number" data-qt="${data.quantite}" id="qte_input" value="1" class="text-center form-control mx-1">

                                </td>
                                <td><input name="prix_unitaire[]" readonly="readonly" class="form-control" type="number"
                                id="prix_unitaire_input" value="${data.article.prix}"></td>
                                <td >
                                   <div class="d-flex">
                                   <input readonly="readonly" class="form-control" type="number" id="prix_total_input"
                                value="${data.article.prix}">
                                <button id="deleteRowBtn" class="btn btn-sm ms-2 btn-danger"
                                style="height: 27px;margin-top: 5px;"><i class="fa fa-times"></i></button>
                                   </div>

                                </td>

                            </tr>`);

    $("#countItem").text(ids.length)
    total();



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
    console.log(opData)
}

  function insert(){
          $.ajax({
              url:API_URL +"operations",
              type:"POST",
              data:JSON.stringify(opData),
              contentType: 'application/json; charset=utf-8',
              headers: {
                  "Authorization": "Bearer cabbd464-9640-480f-8f2d-2474d1e523be22"
              },
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

function onchange(){
    table.on('keyup','#qte_input',function(){
        let id = ($(this).closest('tr').data("id")),
            prix_input =$(this).closest('tr').find('#prix_unitaire_input').val(),
            qte_real = $(this).closest('tr').find('#qte_input').data('qt'),
            qte_input = $(this).closest('tr').find('#qte_input').val();
        // console.log(qte_real);
          if (qte_input>qte_real){
              alert(" max ");
              return
          }
          let   prix_total = prix_input * qte_input;
        $(this).closest('tr').find('#prix_total_input').val(prix_total);
        total();
        opData.operationItems[id-1].prix_total = prix_total;
        opData.operationItems[id-1].quantite = qte_input;
        articles_pt[id-1] =prix_total;
        articles_qt[id-1] =qte_input;
        // console.log(articles_pt[id-1] =prix_total);
    });
}
onchange();
