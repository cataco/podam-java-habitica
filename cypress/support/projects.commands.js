let projectsTabSelector = `[data-track="navigation|projects_panel"]`;
let addProjectSelector = `#projects_list_manager`;
let projectNameSelector = `#edit_project_modal_field_name`;
let favoritesButtonSelector = `[name="is_favorite"]`;
let saveProjectButtonSelector = `[type=submit].ist_button_red`;
let projectHeaderSelector = `[data-testid="view_header__h1"]`;
let moreOptionsSelector = `button.gear_icon`;
let editOptionSelector = `.icon_menu_item__content`;

Cypress.Commands.add("addProject", (projectName, isFavorite) => {
    cy.contains("Add Project").click({force:true});
    cy.get(projectNameSelector).type(projectName);
    if (isFavorite) {
        cy.get(favoritesButtonSelector).click({force:true});
    }
    cy.get(saveProjectButtonSelector).click();
});

Cypress.Commands.add("validateProjectInBoard", (projectName) => {
    cy.get(projectHeaderSelector).should('have.text', projectName);
});

Cypress.Commands.add("editProject", (newProjectName) => {
    cy.get(moreOptionsSelector).click();
    cy.get(editOptionSelector).first().click();
    cy.get(projectNameSelector).clear().type(newProjectName);
    cy.get(saveProjectButtonSelector).click();
});

Cypress.Commands.add("deleteProject", (newProjectName) => {
    cy.get(moreOptionsSelector).click();
    cy.get(editOptionSelector).contains("Delete project").click();
    cy.get(saveProjectButtonSelector).click();
});

Cypress.Commands.add("addTaskToProject", (taskName, dateOption, priority) => {
    cy.addTaskInProject(taskName, dateOption, priority);
    
});
