function addGenre() {

    let additionalGenresHolder = document.getElementById("additionalGenres");

    // Create the holder
    let genresHolder = document.createElement('div');
    genresHolder.classList.add("form-group", "row");

    // ====== Values =============
    let columnDefiner = document.createElement('div');
    columnDefiner.classList.add("col");

    let selectHolder = document.createElement('select');
    selectHolder.classList.add("form-control", "select-styled");
    selectHolder.id = "genres";
    selectHolder.name = "genres";

    let defaultOption = document.createElement('option');
    defaultOption.value = "";
    defaultOption.disabled = true;
    defaultOption.innerText = "Select a genre...";
    defaultOption.selected = true;

    selectHolder.appendChild(defaultOption);

    // === Add the other options

    fetch('/api/enums/movie-genres')
        .then(response => response.json())
        .then(data => {
            data.forEach(opt => {
                let option = document.createElement('option');
                option.value = opt;
                option.text = opt;

                selectHolder.appendChild(option);
            });
        })
        .catch(error => console.error('Error:', error));

    columnDefiner.appendChild(selectHolder);

    // ===== Button =====
    let colAutoDefiner = document.createElement('div');
    colAutoDefiner.classList.add("col-auto");

    let button = document.createElement('button');
    button.type = "button";
    button.classList.add("btn", "btn-secondary", "add-something-button");
    button.onclick = addGenre;
    button.innerText = "Add genre";

    colAutoDefiner.appendChild(button);

    genresHolder.appendChild(columnDefiner);
    genresHolder.appendChild(colAutoDefiner);

    additionalGenresHolder.appendChild(genresHolder);
}

function addActorID() {

    let additionalActorIdHolder = document.getElementById('additionalActorIDs');

    let additionalActorIdHolderBox = document.createElement('div');
    additionalActorIdHolderBox.classList.add("form-group", "row");

    let inputFieldHolder = document.createElement('div');
    inputFieldHolder.classList.add("col");

    let inputField = document.createElement('input');

    inputField.type = 'text';
    //inputField.oninput = checkFieldsAddMovie;
    inputField.placeholder = "Actor Role Id...";
    inputField.classList.add("form-control", "register-form-input", "id-input-field");
    inputField.id = "actorId";
    inputField.name = "actorId";

    inputFieldHolder.appendChild(inputField);

    let buttonHolder = document.createElement('div');
    buttonHolder.classList.add("col-auto");

    let button = document.createElement('button');
    button.type = 'button';
    button.classList.add("btn", "btn-secondary", "add-something-button");
    button.innerText = "Add Role ID";
    button.onclick = addActorID;

    buttonHolder.appendChild(button);


    additionalActorIdHolderBox.appendChild(inputFieldHolder);
    additionalActorIdHolderBox.appendChild(buttonHolder);

    additionalActorIdHolder.appendChild(additionalActorIdHolderBox);

}

function addDirectorID() {

    let additionalActorIdHolder = document.getElementById('additionalDirectorIDs');

    let additionalActorIdHolderBox = document.createElement('div');
    additionalActorIdHolderBox.classList.add("form-group", "row");

    let inputFieldHolder = document.createElement('div');
    inputFieldHolder.classList.add("col");

    let inputField = document.createElement('input');

    inputField.type = 'text';
    //inputField.oninput = checkFieldsAddMovie;
    inputField.placeholder = "Director Id...";
    inputField.classList.add("form-control", "register-form-input", "id-input-field");
    inputField.id = "directorId";
    inputField.name = "directorId";

    inputFieldHolder.appendChild(inputField);

    let buttonHolder = document.createElement('div');
    buttonHolder.classList.add("col-auto");

    let button = document.createElement('button');
    button.type = 'button';
    button.classList.add("btn", "btn-secondary", "add-something-button");
    button.innerText = "Add Director ID";
    button.onclick = addDirectorID;

    buttonHolder.appendChild(button);


    additionalActorIdHolderBox.appendChild(inputFieldHolder);
    additionalActorIdHolderBox.appendChild(buttonHolder);

    additionalActorIdHolder.appendChild(additionalActorIdHolderBox);

}

function addEpisodeId() {

    let additionalActorIdHolder = document.getElementById('additionalEpisodeIDs');

    let additionalActorIdHolderBox = document.createElement('div');
    additionalActorIdHolderBox.classList.add("form-group", "row");

    let inputFieldHolder = document.createElement('div');
    inputFieldHolder.classList.add("col");

    let inputField = document.createElement('input');

    inputField.type = 'text';
    //inputField.oninput = checkFieldsAddMovie;
    inputField.placeholder = "Episode Id...";
    inputField.classList.add("form-control", "register-form-input", "id-input-field");
    inputField.id = "directorId";
    inputField.name = "directorId";

    inputFieldHolder.appendChild(inputField);

    let buttonHolder = document.createElement('div');
    buttonHolder.classList.add("col-auto");

    let button = document.createElement('button');
    button.type = 'button';
    button.classList.add("btn", "btn-secondary", "add-something-button");
    button.innerText = "Add Episode ID";
    button.onclick = addEpisodeId;

    buttonHolder.appendChild(button);


    additionalActorIdHolderBox.appendChild(inputFieldHolder);
    additionalActorIdHolderBox.appendChild(buttonHolder);

    additionalActorIdHolder.appendChild(additionalActorIdHolderBox);

}