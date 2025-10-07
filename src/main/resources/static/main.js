// Espera o HTML carregar
document.addEventListener("DOMContentLoaded", () => {
    // Captura todos os botões de dropdown
    document.querySelectorAll(".dropdown-trigger").forEach(button => {
        button.addEventListener("click", function (event) {
            event.stopPropagation(); // não fecha imediatamente
            const dropdown = this.nextElementSibling;
            dropdown.classList.toggle("active");
        });
    });

    // Fecha qualquer menu aberto se clicar fora
    window.addEventListener("click", function (event) {
        document.querySelectorAll(".dropdown-menu.active").forEach(menu => {
            if (!menu.contains(event.target)) {
                menu.classList.remove("active");
            }
        });
    });
});
