define([
	'Backbone'
], function(Backbone) {
    var Router = Backbone.Router.extend({
        routes: {
            'signIn': 'signIn',
            'signUp': 'signUp',
            '*any'  : 'default'
        },
        
        initialize: function() {
        	require(['view/main/navView'], function (MainView) {
                new MainView();
            });
        },

        signIn: function() {
            require(['view/signIn/signInView'], function (SignInView) {
                if (this.view) {
                    this.view.undelegateEvents();
                }
                this.view = new SignInView();
                this.view.select();
            });
        },

        signUp: function() {
            require(['view/signUp/signUpView'], function (SignUpView) {
                if (this.view) {
                    this.view.undelegateEvents();
                }
                this.view = new SignUpView();
                this.view.select();
            });
        },

        default: function() {
            Backbone.history.navigate('/', {trigger: true});
        }
    });

    return Router;
});