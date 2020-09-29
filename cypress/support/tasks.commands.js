let addTaskButtonSelector = `.plus_add_button`;
let scheduleSelector = `.item_due_selector`;
let taskInputSelector = `.richtextinput`;
let priorityButtonSelector = `.item_actions_priority`;
let addTaskButtonSelector = `.ist_button_red`;
let taskWrapperSelector =`.task_list_item__content__content_wrapper`;

Cypress.Commands.add("addTask", (taskName, dateOption, priority) => {
    cy.get(addTaskButtonSelector).click();
    cy.get(taskInputSelector).type(taskName);
    if (dateOption !== "Today") {
        cy.get(scheduleSelector).click().contains(dateOption).click();
    }
    cy.get(priorityButtonSelector).click().contains(priority).click();
    cy.get(addTaskButtonSelector);
});

Cypress.Commands.add("validateTaskInList", (taskName) => {
    cy.get(taskWrapperSelector).first().should('have.text', taskName);
});



