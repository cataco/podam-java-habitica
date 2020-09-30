context('Todoist Tests - Project (CRUD)', () => {


    before(() => {
        cy.login("parcialmail@mail.com", "parcial123");
    })

    it('Create a new favorite project', () => {
      cy.addProject("QA Project", true);
      cy.validateProjectInBoard();
    });

   
    it('Edit project', () => {
        
    });


    it('Delete project', () => {
       
    });

});

