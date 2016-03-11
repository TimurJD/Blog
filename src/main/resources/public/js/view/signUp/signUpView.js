/**
 * @author TimurJD
 */
define([
    'Backbone',
    'Underscore',
    'model/userModel',
    '../alert/alertView',
    'text!template/signUp/signUpTemplate.html'
], function(Backbone, _, UserModel, AlertView, SignInTemplate) {
    var View = Backbone.View.extend({

        el: "#contentHolder",
        template: _.template(SignInTemplate),

        events: {
            'click #submitForm': 'submitForm'
        },

        initialize: function() {
            this.render();
        },

        validate: function(attrs) {
            var validationStatus = [];

            var emailPattern = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            var userNamePattern = /^[a-zA-Z0-9_-]{3,20}$/;
            var passwordPattern = /^.{6,15}$/;

            var firstName = attrs.firstName;
            var lastName = attrs.lastName;
            var email = attrs.email;
            var password = attrs.password;

            if(!firstName) {
                validationStatus.push({inputId: 'firstName', message: 'Please  fill first name.'});
            } else if(!userNamePattern.test(firstName)) {
                validationStatus.push({inputId: 'firstName', message: 'Invalid firs name. Avoid special characters.'});
            } else {
                this.hideErrors('firstName');
            }

            if(!lastName) {
                validationStatus.push({inputId: 'lastName', message: 'Please fill last name.'});
            } else if(!userNamePattern.test(lastName)) {
                validationStatus.push({inputId: 'lastName', message: 'Invalid last name. Avoid special characters.'});
            } else {
                this.hideErrors('lastName');
            }

            if (!email) {
                validationStatus.push({inputId: 'email', message: 'Please fill Email field.'});
            } else if(!emailPattern.test(email)) {
                validationStatus.push({inputId: 'email', message: 'Invalid email.'});
            } else {
                this.hideErrors('email');
            }

            if (!password) {
                validationStatus.push({inputId: 'password', message: 'Please fill Password field.'});
            } else if(!passwordPattern.test(password)) {
                validationStatus.push({inputId: 'password', message: 'Password must be from 6 to 15 characters.'});
            } else {
                this.hideErrors('password');
            }

            return (validationStatus.length) ? validationStatus : false;
        },

        hideErrors: function(tagName) {
            var div = this.$el.find('#' + tagName + 'Div');

            div.removeClass('has-error').addClass('has-success has-feedback');

            div.find('#' + tagName + 'Span')
                .removeClass('hide glyphicon-remove')
                .addClass('glyphicon glyphicon-ok form-control-feedback');

            div.find('#' + tagName + 'EM')
                .text('')
                .addClass('hide');
        },

        validationError: function (statuses) {
            var self = this;

            _.each(statuses, function (status) {
                var tagName = status.inputId;
                var div = self.$el.find('#' + tagName + 'Div');

                div.removeClass('has-success').addClass('has-error has-feedback');

                div.find('#' + tagName + 'Span')
                    .removeClass('hide glyphicon-ok')
                    .addClass('glyphicon-remove');

                div.find('#' + tagName + 'EM')
                    .text(status.message)
                    .removeClass('hide');
            }, this);
        },

        submitForm: function(event) {
            event.preventDefault();

            var self = this;
            var firstName = this.$el.find('#firstName').val();
            var lastName = this.$el.find('#lastName').val();
            var email = this.$el.find('#email').val();
            var password = this.$el.find('#password').val();
            var errors;

            var data = {
                firstName: firstName,
                lastName : lastName,
                email    : email,
                password : password
            };

            errors = this.validate(data);

            if (errors) {
                this.validationError(errors);
                return;
            }

            // Bootstrap allert if signUp successful

            var user = new UserModel();
            user.urlRoot = '/signup';

            user.save(data, {
                success: function(response, xhr) {
                    console.log(xhr.message);
                    new AlertView({
                        type    : 'Success!',
                        cssClass: 'alert-success',
                        message : xhr.message
                    });
                },
                error: function(error, xhr) {
                    console.log(xhr.responseJSON.message);
                    new AlertView({
                        type    : 'Error!',
                        cssClass: 'alert-danger',
                        message : xhr.responseJSON.message
                    });
                }
            });
        },

        render: function() {
            this.$el.html(this.template());
            return this;
        },

        select: function() {
            $('#signUp').addClass('active');
        }
    });

    return View;
});