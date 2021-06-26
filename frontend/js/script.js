   const apiUrl = "http://localhost:4563/gestionStock_war_exploded/api/articles";   
   
   const  fecthData = async()=>{
       const response = await fetch(apiUrl);
       const data = await response.json();
       return data;
    
   }


let tableArticles = document.getElementById("tableArticles");
let tableArticlesBody = document.getElementById("tableArticlesBody");

function appendToTable(data) {
  let tr = document.createElement("tr");

  let id = document.createElement("td");
  id.innerText = data.id;
  tr.appendChild(id);

  let ref = document.createElement("td");
  ref.innerText = data.ref;
  tr.appendChild(ref);

  let libelle = document.createElement("td");
  libelle.innerText = data.libelle;
  tr.appendChild(libelle);

  let date_exp = document.createElement("td");
  date_exp.innerText = new Date(data.date_exp).toLocaleDateString("en-US");
  tr.appendChild(date_exp);

  let actions = document.createElement("td");
  let updateAction = document.createElement("button");
  updateAction.classList.add("btn", "btn-primary",'mr-1');
  updateAction.value = "update";
  let deleteAction = document.createElement("button");
  deleteAction.classList.add("btn", "btn-danger");
  deleteAction.value = "delete";

  let updateIcon = document.createElement("i");
  updateIcon.classList.add("far", "fa-edit");
  let deleteIcon = document.createElement("i");
  deleteIcon.classList.add("fas", "fa-trash-alt");

  updateAction.appendChild(updateIcon);
  deleteAction.appendChild(deleteIcon);

  actions.appendChild(updateAction);
  actions.appendChild(deleteAction);
  tr.appendChild(actions);

  tableArticlesBody.appendChild(tr);
}

function buildTable() {
    fecthData().then(articles=>{
      console.log(articles)
      articles.forEach(article=>{
        var s = new Date(article.date_exp).toLocaleDateString("en-US")
       console.log(s)
        appendToTable(article);
      })
    })
  // personnes.forEach((person) => {
  //   addPerson(person);
  // });
}
function addNew() {
  let nom = document.getElementById("nom");
  let prenom = document.getElementById("prenom");
  let daten = document.getElementById("daten");
  let genre = document.getElementById("genre");
  let person = {
    prenom: prenom.value,
    nom: nom.value,
    genre: genre.value,
    dtNaiss: daten.value,
  };
  addPerson(person);
  nom.value = "";
}
buildTable();
