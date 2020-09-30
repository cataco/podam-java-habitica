let addTaskButtonSelector = `.task_actions .plus_add_button`;
let scheduleSelector = `.item_due_selector`;
let taskInputSelector = `.richtextinput`;
let priorityButtonSelector = `.item_actions_priority`;
let saveTaskButtonSelector = `[type=submit].ist_button_red`;
let taskWrapperSelector = `.task_list_item__content__content_wrapper`;
let iconCloseSelector = `.icon_close`;
let moreOptionsSelector = `[data-testid="more_menu"]`;
let checkBoxSelector = `.item_checkbox.priority_3`;
let taskNameSelector = `[data-action-hint="task-detail-view-edit"]`;
let addSubTask = `.plus_add_button`;
let subTaskWrapperSelector = `.sub_items_tab_container ${taskWrapperSelector}`;
let subTaskCheckBoxSelector = `.sub_items_tab_container ${checkBoxSelector}`;
let moreActionsSelector = `.item_actions_more`;

Cypress.Commands.add("addTask", (taskName, dateOption, priority) => {
    cy.get(`#filter_today`).click();
    cy.get(addTaskButtonSelector).click();
    cy.get(taskInputSelector).type(taskName);
    if (dateOption !== "Today") {
        cy.get(scheduleSelector).click();
        cy.get(`.scheduler-suggestions-item-label`).contains(dateOption).click();
    }
    cy.get(priorityButtonSelector).click();
    cy.get('.priority_picker_item_name').contains(priority).click();
    cy.get(saveTaskButtonSelector).click();
});

Cypress.Commands.add("validateTaskInList", (taskName) => {
    cy.get(`.item_content`).contains("Upcoming").click();
    cy.get(taskWrapperSelector).first().should('have.text', taskName);
});

Cypress.Commands.add("editTaskData", (newTaskName, newDateOption, newPriority) => {
    cy.get(`.item_content`).contains("Today").click();
    cy.get(taskWrapperSelector).first().click();
    cy.get(taskNameSelector).click();
    cy.get(taskInputSelector).type(newTaskName);
    if (newDateOption !== "Today") {
        cy.get(scheduleSelector).click();
        cy.get(`.scheduler-suggestions-item-label`).contains(newDateOption).click();
    }
    cy.get(priorityButtonSelector).click();
    cy.get('.priority_picker_item_name').contains(newPriority).click();
    cy.get(saveTaskButtonSelector).click();
    cy.get(iconCloseSelector).click();
});

Cypress.Commands.add("cloneTask", () => {
    //cy.get(`.item_content`).contains("Today").click();
    cy.wait(4000);
    cy.get(moreOptionsSelector).first().click({ force: true });
    cy.contains("Duplicate").click();
});

Cypress.Commands.add("deleteTask", () => {
    cy.wait(4000);
    cy.get(taskWrapperSelector).first().click({force: true});
    cy.get(moreActionsSelector).click({force: true});
    cy.contains("Delete task").click({force: true});
    cy.get(saveTaskButtonSelector).click();
});

Cypress.Commands.add("checkTaskPriority3", () => {
    cy.get(`.item_content`).contains("Upcoming").click();
    cy.wait(2000);
    cy.get(checkBoxSelector).click({ force: true });
});

Cypress.Commands.add("addSubTask", (subTaskName, subTaskPriority) => {
    cy.get(`.item_content`).contains("Today").click();
    cy.get(taskWrapperSelector).first().click();
    cy.get(addSubTask).contains("Add sub-task").click();
    cy.get(taskInputSelector).type(subTaskName);
    cy.get(`.sub_items_tab_container ${priorityButtonSelector}`).click();
    cy.get('.priority_picker_item_name').contains(subTaskPriority).click();
    cy.get(`.sub_items_tab_container ${saveTaskButtonSelector}`).click();
});

Cypress.Commands.add("validateSubTaskInList", (taskName) => {
    cy.get(subTaskWrapperSelector).first().should('have.text', taskName);
});

Cypress.Commands.add("checkSubTaskPriority3", () => {
    cy.get(subTaskCheckBoxSelector).click({ force: true });
});

Cypress.Commands.add("deleteSubTask", () => {
    cy.wait(1000);
    cy.get(`.sub_items_tab_container .task_list_item__body`).first().click({ force: true });
    cy.get(`[data-item-content='TestSubTask'] ${moreActionsSelector}`).click({force: true});
    cy.contains("Delete task").click({force: true});
    cy.get(saveTaskButtonSelector).contains("Delete").click();

});