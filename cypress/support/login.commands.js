// ***********************************************
// This example commands.js shows you how to
// create various custom commands and overwrite
// existing commands.
//
// For more comprehensive examples of custom
// commands please read more here:
// https://on.cypress.io/custom-commands
// ***********************************************
//
//
// -- This is a parent command --
// Cypress.Commands.add("login", (email, password) => { ... })
//
//
// -- This is a child command --
// Cypress.Commands.add("drag", { prevSubject: 'element'}, (subject, options) => { ... })
//
//
// -- This is a dual command --
// Cypress.Commands.add("dismiss", { prevSubject: 'optional'}, (subject, options) => { ... })
//
//
// -- This will overwrite an existing command --
// Cypress.Commands.overwrite("visit", (originalFn, url, options) => { ... })
let loginHrefSelector = `[href="/users/showlogin"]`;
let signUpHrefSelector = `[href="/users/showregister"]`;
let emailInputSelector = `#email`;
let passwordInputSelector = `#password`;
let loginButtonSelector = `.sel_login`;
let signUpButtonSelector = `#step_one_submit`;
let settingsMenuSelector = `[aria-controls="setting_menu"]`;
let userNameSelector = `#personal_info`;
let userEmailSelector = `#login_info`;
let nameInputSelector = `#full_name`;
let passwordSignUpInputSelector = `#pwd`;
let submitSignUpButtonSelector = `#step_two_submit`;
let homePageSelector = `[href="/home"]`;

Cypress.Commands.add("login", (userEmail, userPassword) => {
   cy.visit("/");
   cy.get(loginHrefSelector).click();
   cy.get(emailInputSelector).type(userEmail);
   cy.get(passwordInputSelector).type(userPassword);
   cy.get(loginButtonSelector).click();
});

Cypress.Commands.add("validateUserHome", () => {
   cy.get(settingsMenuSelector).should('be.visible');
});

Cypress.Commands.add("signUp", (userName, userEmail, userPassword) => {
   cy.visit("/");
   cy.get(signUpHrefSelector).first().click();
   cy.get(emailInputSelector).type(userEmail);
   cy.get(signUpButtonSelector).click();
   cy.get(nameInputSelector).type(userName);
   cy.get(passwordSignUpInputSelector).type(userPassword);
   cy.get(submitSignUpButtonSelector).click();
   cy.contains("Let's go").click();
   cy.contains("Create my Todoist").click();
   cy.contains("Open my Todoist").click();
   cy.wait(2000);
});

Cypress.Commands.add("validateLandingPage", () => {
   cy.get(homePageSelector).should('be.visible');
});

Cypress.Commands.add("logOut", () => {
   cy.get(settingsMenuSelector).click();
   cy.contains('Log out').click();
});