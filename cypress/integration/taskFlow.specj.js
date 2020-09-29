context('Todoist Tests - Task and sub-tasks Flow (CRUD)', () => {


    before(() => {
        cy.login("parcialmail@mail.com","parcial123");
    })

    it('Create new task for today with priority 2', () => {
        cy.addTask("My First Task-Today", "Today", "Priority 2");
        cy.validateTaskInList("My First Task-Today");
    });

    it('Create new task for tomorrow with priority 1', () => {
        cy.addTask("My Task-Tomorrow", "Tomorrow", "Priority 1");
        cy.validateTaskInList("My Task-Tomorrow");
    });

    it('Edit task data', () => {
        //TODO
    });

    it('Duplicate task', () => {
        //TODO
    });

    it('Add sub-tasks to task', () => {
        //TODO
    });

    it('Edit sub-tasks', () => {
        //TODO
    });

    it('Check sub-tasks', () => {
        //TODO
    });

    it('Delete sub-tasks', () => {
        //TODO
    });

    it('Check task', () => {
        //TODO
    });

    it('Delete tasks', () => {
      //TODO
    });
    
});

