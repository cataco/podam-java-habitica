context('Todoist Tests - Project Flow', () => {

    before(() => {
        cy.login("parcialmail@mail.com", "parcial123");
    })

    it('Creates a new favorite project', () => {
      cy.addProject("QA Project", true);
      cy.validateProjectInBoard("QA Project");
    });

    it('Adds task to project', () => {
        cy.addTaskToProject("QA Task1", "Tomorrow", "Priority 1");
        cy.validateTaskInList("QA Task1");
        
      });
   
    it('Edits project', () => {
        cy.editProject("QA Project-EDIT");
        cy.validateProjectInBoard("QA Project-EDIT");
    });

    it('Deletes project', () => {
       cy.deleteProject();
       cy.contains("QA Project-EDIT").should('not.exist');
    });

});

