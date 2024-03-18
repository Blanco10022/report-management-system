const allSideMenu = document.querySelectorAll('#sidebar .side-menu.top li a');

// Retrieve the active item ID from sessionStorage
const activeItemId = sessionStorage.getItem('activeItemId');

allSideMenu.forEach((item, index) => {
  const li = item.parentElement;
  
  // Check if the current item is the previously active item
  const isActiveItem = activeItemId && (item.id === activeItemId);

  // Add active class to the previously active item
  if (isActiveItem) {
    li.classList.add('active');
  }

  item.addEventListener('click', function() {
    // Remove active class from all other items
    allSideMenu.forEach((menuItem) => {
      menuItem.parentElement.classList.remove('active');
    });

    // Add active class to the clicked item
    li.classList.add('active');

    // Store the active item's ID in sessionStorage
    const newActiveItemId = item.id;
    sessionStorage.setItem('activeItemId', newActiveItemId);
  });
});




// TOGGLE SIDEBAR
const menuBar = document.querySelector('#content nav .bx.bx-menu');
const sidebar = document.getElementById('sidebar');

menuBar.addEventListener('click', function () {
	sidebar.classList.toggle('hide');
})







const searchButton = document.querySelector('#content nav form .form-input button');
const searchButtonIcon = document.querySelector('#content nav form .form-input button .bx');
const searchForm = document.querySelector('#content nav form');

searchButton.addEventListener('click', function (e) {
	if(window.innerWidth < 576) {
		e.preventDefault();
		searchForm.classList.toggle('show');
		if(searchForm.classList.contains('show')) {
			searchButtonIcon.classList.replace('bx-search', 'bx-x');
		} else {
			searchButtonIcon.classList.replace('bx-x', 'bx-search');
		}
	}
})





if(window.innerWidth < 768) {
	sidebar.classList.add('hide');
} else if(window.innerWidth > 576) {
	searchButtonIcon.classList.replace('bx-x', 'bx-search');
	searchForm.classList.remove('show');
}


window.addEventListener('resize', function () {
	if(this.innerWidth > 576) {
		searchButtonIcon.classList.replace('bx-x', 'bx-search');
		searchForm.classList.remove('show');
	}
})



const switchMode = document.getElementById('switch-mode');

switchMode.addEventListener('change', function () {
	if(this.checked) {
		document.body.classList.add('dark');
	} else {
		document.body.classList.remove('dark');
	}
})

// JavaScript  code for the todolist 
document.addEventListener("DOMContentLoaded", function() {
    const addTodoButton = document.getElementById("addTodoButton");
    const addTodoForm = document.getElementById("addTodoForm");
    const todoForm = document.getElementById("todoForm");
    const todoInput = document.getElementById("todoInput");
    const todoList = document.getElementById("todoList");

    // Retrieve the stored todo list from Local Storage
    let storedTodos = localStorage.getItem("todos");
    let todos = storedTodos ? JSON.parse(storedTodos) : [];

    // Function to save the todo list to Local Storage
    function saveTodos() {
        localStorage.setItem("todos", JSON.stringify(todos));
    }

    // Render the todo items from the stored list
    function renderTodos() {
        todoList.innerHTML = "";
        todos.forEach(function(todoText) {
            const todoItem = document.createElement("li");
            todoItem.innerHTML = `
                <p>${todoText}</p>
                <i class="bx bx-trash"></i>
            `;
            todoList.appendChild(todoItem);
        });
    }

    // Show/hide the form when the plus icon is clicked
    addTodoButton.addEventListener("click", function() {
        addTodoForm.style.display = "block";
    });

    // Validate and add a new todo item
    todoForm.addEventListener("submit", function(event) {
        event.preventDefault();
        const todoText = todoInput.value.trim();
        if (todoText !== "") {
            todos.push(todoText);
            saveTodos();
            renderTodos();
            todoInput.value = "";
            addTodoForm.style.display = "none";
        }
    });

    // Delete a todo item
    todoList.addEventListener("click", function(event) {
        if (event.target.classList.contains("bx-trash")) {
            const todoItem = event.target.parentElement;
            const todoText = todoItem.querySelector("p").textContent;
            todos = todos.filter(function(todo) {
                return todo !== todoText;
            });
            saveTodos();
            renderTodos();
        }
    });

    // Initial rendering of the stored todo list
    renderTodos();
})



;



