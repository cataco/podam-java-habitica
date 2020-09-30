context('Todoist Tests - Task and sub-tasks Flow (CRUD)', () => {


    before(() => {
        cy.login("parcialmail@mail.com", "parcial123");
    })

    it('Create new task for today with priority 2', () => {
        cy.addTask("My First Task-Tomorrow", "Tomorrow", "Priority 2");
        cy.validateTaskInList("My First Task-Tomorrow");
    });

    it('Create new task for tomorrow with priority 1', () => {
        cy.addTask("My Task-Today", "Today", "Priority 1");
        cy.validateTaskInList("My Task-Today");
    });

    it('Edit task data', () => {
        cy.editTaskData("-EDIT", "Today", "Priority 1");
        cy.validateTaskInList("My Task-Today-EDIT");
    });

    it.skip('Duplicate task', () => {
        cy.cloneTask("My Task-Today-EDIT");
        cy.get(`.item_checkbox.priority_4`).should('have.length', 2);
    });

    it('Add sub-tasks to task', () => {
        cy.addSubTask("TestSubTask", "Priority 2");
        cy.validateSubTaskInList("TestSubTask");
    });

    it('Check sub-tasks', () => {
        cy.checkSubTaskPriority3();
        cy.wait(2000);
        cy.get(`.task_list_item.task_list_item--completed`).should('be.visible')
    });

    it('Delete sub-tasks', () => {
        cy.deleteSubTask();
        cy.contains("TestSubTask").should('not.exist');
    });

    it('Check task', () => {
        cy.checkTaskPriority3();
        cy.contains("My First Task-Tomorrow").should('not.exist');
    });

    it('Delete tasks', () => {
        cy.deleteTask();
        cy.contains("My Task-Today-EDIT").should('not.exist');
    });

});

