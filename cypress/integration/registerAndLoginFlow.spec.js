context('Todoist Tests - Register and Login Flow', () => {

    it('Signs Up', () => {
        var randomNumber = getRandomInt(0, 1000);
        var testUser = 'parcialmail' + randomNumber;
        var email = testUser + '@mail.com'
        cy.signUp("Parcial SignUp", email,"parcial123");
        cy.validateUserHome();
    });

    it('Logs out', () => {
        cy.logOut();
        cy.validateLandingPage();
    });
    
    it('Logs in successfully', () => {
        cy.login("parcialmail@mail.com","parcial123");
        cy.validateUserHome();
    });

    it('Logs in with invalid data', () => {
        cy.login("parcialmail@mail.com","parcial123BAD");
        cy.contains('Wrong email or password.').should('be.visible');
    });

    function getRandomInt(min, max) {
        min = Math.ceil(min);
        max = Math.floor(max);
        return Math.floor(Math.random() * (max - min)) + min;
    };
    
});

