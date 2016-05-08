define([
	"Backbone",
    "view/nav/navView"
], function(Backbone, MainView) {
    var Router = Backbone.Router.extend({
        routes: {
            "signIn" : "signIn",
            "signUp" : "signUp",
            "posts"  : "posts",
            "about"  : "about",
            "contact": "contact",
            "newPost": "newPost",
            "*any"   : "default"
        },
        
        initialize: function() {
            new MainView();
        },

        signIn: function() {
            this.loadView("signIn");
        },

        signUp: function() {
            this.loadView("signUp");
        },

        about: function() {
            console.log("about");
        },

        contact: function() {
            console.log("contact");
        },

        posts: function() {
            this.loadView("posts");
        },

        newPost: function() {
            this.loadView("newPost");
        },

        default: function() {
            Backbone.history.navigate("/posts", {trigger: true});
        },

        loadView: function (argName, argParams, argRedirect, argType) {
            var self = this;
            var name = argName;
            var nameView = argType ? name + argType + "View" : name + "View";
            var params = argParams;
            //var redirect = argRedirect;
            //var newUrl;
            //var session = App.session;
            //var homeUrl = session.homeUrl();

            //if (redirect === REDIRECT.whenNOTAuthorized) {

            //if (!session.get('authorized')) {
            //    newUrl = 'login/success/' + window.location.hash.slice(1);
            //    return Backbone.history.navigate(newUrl, {trigger: true});
            //}
            //}

            //if (redirect === REDIRECT.whenAuthorized) {

            //if (session.get('authorized')) {
            //    return Backbone.history.navigate(homeUrl, {trigger: true});
            //}
            //}

            require(["view/" + name + "/" + nameView], function (View) {
                self[nameView] = new View(params);

                if (self.view) {
                    self.view.undelegateEvents();
                }

                self.view = self[nameView];

                if(self.view.select) {
                    self.view.select();
                }
            });
        }
    });

    return Router;
});