let todos = [
    { name: "Buy milk", completed: false },
    { name: "Go to the gym", completed: true },
    { name: "Read a book", completed: false },
    { name: "Write code", completed: true },
    { name: "Walk the dog", completed: false }
];

function render() {
    const ul = document.getElementById('todo-list');
    ul.innerHTML = '';

    todos.forEach((todo, idx) => {
        const li = document.createElement('li');
        li.classList.add('todo__container'); //klasa css zeby nadac styl
        if (todo.completed) li.classList.add('todo__container--completed');


        const nameDiv = document.createElement('div');
        nameDiv.className = 'todo-element todo-name';
        nameDiv.textContent = todo.name;
        li.appendChild(nameDiv); //zawartosc li

        const upBtn = document.createElement('button');
        upBtn.className = 'todo-element todo-button move-up';
        upBtn.textContent = '↑';
        upBtn.onclick = () => {
            if (idx > 0) {
                [todos[idx - 1], todos[idx]] = [todos[idx], todos[idx - 1]];
                render();
            }
        };
        li.appendChild(upBtn);

        const downBtn = document.createElement('button');
        downBtn.className = 'todo-element todo-button move-down';
        downBtn.textContent = '↓';
        downBtn.onclick = () => {
            if (idx < todos.length - 1) {
                [todos[idx + 1], todos[idx]] = [todos[idx], todos[idx + 1]];
                render();
            }
        };
        li.appendChild(downBtn);

        if (!todo.completed) { //jesli nie zrobione to dodajemy przycisk Done
            const doneBtn = document.createElement('button');
            doneBtn.className = 'todo-element todo-button';
            doneBtn.textContent = 'Done';
            doneBtn.onclick = () => {
                todos[idx].completed = true;
                render();
            };
            li.appendChild(doneBtn);
        } else {
            const revertBtn = document.createElement('button');
            revertBtn.className = 'todo-element todo-button';
            revertBtn.textContent = 'Revert';
            revertBtn.onclick = () => {
                todos[idx].completed = false;
                render();
            };
            li.appendChild(revertBtn);
        }

        const removeBtn = document.createElement('button');
        removeBtn.className = 'todo-element todo-button';
        removeBtn.textContent = 'Remove';
        removeBtn.onclick = () => {
            todos.splice(idx, 1);
            render();
        };
        li.appendChild(removeBtn);

        ul.appendChild(li);
    });

    let licznik = 0;
    for (let i = 0; i < todos.length; i++) {
        if (!todos[i].completed) {
            licznik++;
        }
    }
    document.getElementById('count').textContent = licznik;

}

document.getElementById('add-todo-form').onsubmit = function (event) {
    event.preventDefault();
    const input = this.elements['todo-name'];
    const value = input.value.trim();

    if (value) {
        todos.push({ name: value, completed: false });
        render();
        input.value = '';
    }
};

document.getElementById('todos-clear').onclick = function () {
    todos = [];
    render();
};

render();
