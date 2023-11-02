function createNode(element) {
    return document.createElement(element); //Creat the type of element you pass in the parameters
}

function append(parent, el) {
    return parent.appendChild(el); // Append the second parameter (element) to the firs one
}

const ul = document.getElementById('persons')

fetch("api/persons")
    .then((resp) => resp.json()) // Transform the data into json
    .then(function (data) {
        data.map(function () {
        })// Get the results
        return data.map(function (person) { // Map through the results and  for each run the code below
            let li = createNode('li'), // Create the elements we need
                span = createNode('span');
            li.innerHTML = `${person.id} ${person.name}`; // Make the HTML of our span to be first and last name of our a
            append(ul, li);
            append(li, span);
        })
    })