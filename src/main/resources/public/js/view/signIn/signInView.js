/**
 * @author TimurJD
 */
define([
    'Backbone',
    'Underscore',
    'text!template/signIn/signInTemplate.html'
], function(Backbone, _, SignInTemplate) {
    var View = Backbone.View.extend({

        el: "#contentHolder",
        template: _.template(SignInTemplate),

        events: {

        },

        initialize: function() {
            this.render();
        },

        render: function() {
            this.$el.html(this.template());
            return this;
        },

        select: function() {
            $('.active').removeClass('active');
            $('#signIn').addClass('active');
        }
    });

    return View;
});
