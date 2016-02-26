/**
 * @author TimurJD
 */
define([
    'Backbone',
    'Underscore',
    'text!template/main/mainTemplate.html'
], function(Backbone, _, IndexTemplate) {
    var View = Backbone.View.extend({

        el: "#generalHolder",
        template: _.template(IndexTemplate),

        events: {
            'click #navHolder li': 'addClassActive'
        },

        initialize: function() {
            this.render();
        },

        addClassActive: function(event) {
            var target = $(event.target);

            this.$el.find('.active').removeClass('active');
            target.parent().addClass('active');
        },

        render: function() {
            this.$el.html(this.template());
            return this;
        }
    });

    return View;
});
