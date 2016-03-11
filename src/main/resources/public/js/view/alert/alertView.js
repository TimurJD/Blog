/**
 * @author TimurJD
 */
define([
    'Backbone',
    'Underscore',
    'text!template/alert/alertTemplate.html'
], function(Backbone, _, SignInTemplate) {
    var View = Backbone.View.extend({

        el: "#alertHolder",
        template: _.template(SignInTemplate),

        events: {
            'click #submitForm': 'submitForm'
        },

        initialize: function(options) {
            this.alertData = options;
            this.render();
        },

        render: function() {
            this.$el.html(this.template(this.alertData));
            return this;
        }
    });

    return View;
});