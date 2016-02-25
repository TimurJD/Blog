define([
	'Backbone'
], function(Backbone) {
    var Router = Backbone.Router.extend({
        routes: {
            'signIn': 'signIn',
            '*any'  : 'default'
        },
        
        initialize: function() {
        	var self = this;
        	
        	require(['view/main/mainView'], function (MainView) {
                if (self.view) {
                    self.view.undelegateEvents();
                }
                self.view = new MainView();
            });
        },

        signIn: function() {
            var self = this;

            require(['view/signIn/signInView'], function (SignInView) {
                if (self.view) {
                    self.view.undelegateEvents();
                }
                self.view = new SignInView();
            });
        },

        default: function() {
            Backbone.history.navigate('/', {trigger: true});
        }
    });

    return Router;
});