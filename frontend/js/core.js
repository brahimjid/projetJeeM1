
  const API_URL ="http://localhost:4563/gestionStock_war_exploded/api/";





  $( window ).on('load',function() {
      if ($("#loginForm").length || $("#registerForm").length){
            console.log("----");
            return;
      }


       if (getUser()==null){
           window.location.href ="../auth/login.html";
           return;
       }

  });

$(document).on("submit", "form", function(e){
      e.preventDefault();
      return  false;
  });
const showModal= (modalId)=> {
      const myModal = new bootstrap.Modal(document.getElementById(modalId));
        myModal.show()
  }
  const hideModal= (modalId)=> {
        let instance =document.getElementById(modalId)
      const modal = bootstrap.Modal.getInstance(instance); modal.hide()
  }

  function dateFormat(date){
    return moment(date).format('YYYY-MM-DD')
  }

const fetchData =(url,success=null)=>{
      let  loadingContent = $("#loadingContent");
      console.log(" fetching ......")
      $.ajax({
           url:API_URL + url,
          headers: {
              "Authorization": "Bearer "+getUser().token
          },
          success:function (data){
               if (success)
               success(data);
          },
          error:function (err){
              console.log(err);
              showModal("errorModal");
          },
          complete:function (){
              loadingContent.addClass('d-none')
          }
      });

   }

  function validate(formId) {
     let msg = "champ est obligatoire"
         let count = 0;
      $.each($(formId+' input'), function (k, item) {
          let input = $(item);
          if ((input.attr('required')) && input.val()===''){
              input.addClass('is-invalid');
                      input.next('.invalid-feedback').remove();
                      $(`<p class='invalid-feedback'>${msg}</p>`).insertAfter(input);
                      count++;
              return false
          }
      });
      return count;

  }

  function addData(formId, done = null) {
        if (validate(formId)>0){
            return false;
        }
      $(formId +" .spinner-border").removeClass('d-none');
      $(formId +" button").attr('disabled','disabled');
       let data ={};
      $(formId).serializeArray().map(function(x){data[x.name] = x.value;});
          $.ajax({
              type:$(formId).attr("method"),
              url: API_URL  + $(formId).attr("action"),
              data: JSON.stringify(data),
              contentType: 'application/json; charset=utf-8',
              dataType: 'json',
              headers: {
                  "Authorization": "Bearer "+getUser().token
              },
              success: function (data) {
                  console.log("success")
                  showModal("successModal");

                  $(formId +" .spinner-border").addClass('d-none');
                  if (done) {
                      done(data);
                  }
                  setTimeout(function (){
                      console.log("timout")
                         $("#new").removeClass('new')
                  },4000)
                  $(formId +" button").removeAttr('disabled');
                  $(formId).trigger('reset');
              },
              error: function (data) {
                  showModal("errorModal");
                  console.log("error")
                  $(formId +" button").removeAttr('disabled');
                  $(formId +" .spinner-border").addClass('d-none');
              },
          });


  }

  function EditData(button,formId){
       $(formId).prev('form').hide();
       $(formId).removeClass('d-none');
         let info = $(button).data('info');
      $.each($(formId+' input'), function (k, item) {
          let input = $(item);
          if (input.attr('type')==='date'){
              const date = moment(info.date_exp).format('YYYY-MM-DD')
              input.val(date)
          }
          else
         input.val(info[input.attr('name')]);
      });
  }

  function submitData(formId,done=null){
      let data = {};
      $(formId).serializeArray().map(function(x){data[x.name] = x.value;});
      $(formId +" .spinner-border").removeClass('d-none');
      $(formId +" button").attr('disabled','disabled');
      let id = $(formId + " input[type=hidden]").val();
      $.ajax({
          url: API_URL  + $(formId).attr("action") + "/" +id ,
          type:"put",
          data: JSON.stringify(data),
          contentType: 'application/json; charset=utf-8',
          dataType: 'json',
          headers: {
              "Authorization": "Bearer "+getUser().token
          },
          success: function (data) {
              setTimeout(function (){
                  $("#new").removeClass('new')
              },4000);
              if (done){
                  done(data)
              }
                $(formId).trigger('reset');
                $(formId).hide();
              $(formId).prev('form').show();
              $(formId +" button").removeAttr('disabled');

          },
          error: function (data) {
              showModal("errorModal");
              console.log("error")

          },
          complete:function (){
              $(formId +" button").removeAttr('disabled');
              $(formId +" .spinner-border").addClass('d-none');
          }
      })
  }

  function deleteData(id,url){
    showModal("deleteModal");
    $("#confirmDelete").on('click',function (){

        $.ajax({
            url: API_URL  + url + "/" +id ,
            type:"delete",
            headers: {
                "Authorization": "Bearer "+getUser().token
            },
            success:function (){
                showModal("successModal");
                $("#deleteBtn-"+id).parent().parent().remove();
            },
            error:function (){
               // showModal("errorModal");
            },
            complete:function (){
                hideModal("deleteModal");
            }
        })
    })
  }

  const createTable = (container,classes,headers,idTable,done,headerHide=false)=>{

       let  tableHeader = `${idTable}-header`;
        $(`#${container}`).append(`
         <table id="${idTable}" class="table table-bordered table-striped ${classes!==null?classes.join(' '):''}">
                    <thead>
                    <tr class="bg-light" id="${tableHeader}"></tr>
                    </thead>
                    <tbody id="${idTable}-body">
                    </tbody>
                </table>
       `);
         headers.map(function (item,key){
             $("#"+tableHeader).append(`
                <th ${(key===0&&headerHide)?'class="d-none"':'d-jk'}>${item}</th>`
             )
         });
       done($("#"+idTable+'-body'));
      $.extend( true, $.fn.dataTable.defaults, {
          "autoWidth": false,
          language: {
              url: "../../fr.json"
          },
          ordering:false,
          select:false,
          bInfo:false,
          bLengthChange:false,
          "pageLength": 5

      } );


      $(document).ready(function() {
          setTimeout(function (){
              $('#'+idTable).DataTable();
          },2000)

      } );
   }

  function printTable(tableId,code_facture=null,date_facture=null){
      window.jsPDF = window.jspdf.jsPDF;
      let start =10;
      let doc = new jsPDF();
      if (code_facture !==null && date_facture!==null){
          doc.text('Facture : #'+code_facture, 10, 15);
          doc.text('Date : '+date_facture, 10, 28);
          doc.setFontSize(8);
          start=40
      }


      doc.autoTable({ html: '#'+tableId,showHead: 'firstPage',startY: start,  });
      doc.autoPrint();
      doc.output('dataurlnewwindow');
  }

  function downloadTable(tableId,code_facture=null,date_facture=null){

          window.jsPDF = window.jspdf.jsPDF;
          let start =10;
          let doc = new jsPDF();
          if (code_facture !==null && date_facture!==null){
              doc.text('Facture : #'+code_facture, 10, 15);
              doc.text('Date : '+date_facture, 10, 28);
              doc.setFontSize(8);
              start=40
          }
          doc.autoTable({ html: '#'+tableId,showHead: 'firstPage',startY: start,  });
           doc.save(`facture_${code_facture}`);
  }


    function storeUser(user) {
        localStorage.setItem('user', JSON.stringify(user));
    }
    function getUser() {

        return JSON.parse(localStorage.getItem('user'));
    }
  $("#logoutBtn").on("click",function (){
      logout();
  })
    function logout() {
       $.ajax({
           'url':API_URL +'auth/'+"logout/"+getUser().id,
           'type':"get",
           headers: {
               "Authorization": "Bearer "+getUser().token
           },
             success:function (){
                 localStorage.removeItem('user');
                 window.location.href = "../auth/login.html"
             },
           error:function (err){
               console.log(err);
               localStorage.removeItem('user');
               window.location.href = "../auth/login.html"
           }
       })

    }

