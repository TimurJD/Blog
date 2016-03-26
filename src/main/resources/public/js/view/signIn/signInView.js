/**
 * @author TimurJD
 */
define([
    'Backbone',
    'Underscore',
    'model/userModel',
    '../alert/alertView',
    'text!template/signIn/signInTemplate.html'
], function(Backbone, _, UserModel, AlertView, SignInTemplate) {
    var View = Backbone.View.extend({

        el: "#contentHolder",
        template: _.template(SignInTemplate),

        events: {
            'click #donHaveAccount': 'redirectSignUp',
            'click #login': 'login'
        },

        initialize: function() {
            this.render();
        },

        validate: function(attrs) {
            var validationStatus = [];

            var emailPattern = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            var passwordPattern = /^.{6,15}$/;

            var email = attrs.email;
            var password = attrs.password;

            if(!email) {
                validationStatus.push({inputId: 'email', message: 'Please fill Email field.'});
            } else if(!emailPattern.test(email)) {
                validationStatus.push({inputId: 'email', message: 'Invalid email.'});
            } else {
                this.hideErrors('email');
            }

            if(!password) {
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

        validationError: function(statuses) {
            var self = this;

            _.each(statuses, function(status) {
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

        redirectSignUp: function(event) {
            event.preventDefault();

            $('.active').removeClass('active');
            Backbone.history.navigate('/signUp', {trigger: true});
        },

        login: function(event) {
            event.preventDefault();

            var email = this.$el.find('#email').val();
            var password = this.$el.find('#password').val();

            var data = {
                email: email,
                password: password
            };

            var errors = this.validate(data);

            if(errors) {
                this.validationError(errors);
                return;
            }

            $.ajax({
                url: "/login",
                type: "POST",
                data: JSON.stringify(data),
                dataType: "json",
                "Content-Type": "application/json",
                success: function(data) {
                    new AlertView({
                        type    : 'Success!',
                        cssClass: 'alert-success',
                        message : data.message
                    });
                    setTimeout(function() {
                        $('.active').removeClass('active');
                        Backbone.history.navigate('/signUp', {trigger: true});
                        Backbone.history.navigate('posts', {trigger: true});
                    }, 2000)
                },
                error: function(jqXHR) {
                    new AlertView({
                        type    : 'Error!',
                        cssClass: 'alert-danger',
                        message : jqXHR.responseJSON.message
                    });
                    console.log(jqXHR);
                }
            });
        },

        render: function() {
            this.$el.html(this.template());
            return this;
        },

        select: function() {
            $('#signIn').addClass('active');
        }
    });

    return View;
});
