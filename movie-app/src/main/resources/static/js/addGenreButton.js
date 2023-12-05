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

// <div class="form-group row">
//                     <div class="col">
//                         <select class="form-control select-styled" id="genres" name="genres">
//                             <option value="" disabled selected>Select a genre...</option>
//                             <option th:each="genre : ${T(bg.softuni.movieapp.model.enums.MovieGenreEnum).values()}" th:value="${genre}" th:text="${genre}"></option>
//                         </select>
//                     </div>
//                     <div class="col-auto">
//                         <button type="button" class="btn btn-secondary add-something-button" onclick="addGenre()">Add genre</button>
//                     </div>
//                 </div>