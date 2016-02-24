/**
 * @author TimurJD
 */
define([
    'Backbone',
    'Underscore',
    'text!template/indexTemplate.html'
], function (Backbone, _, IndexTemplate) {
    var View = Backbone.View.extend({
        el: "#generalHolder",
        template: _.template(IndexTemplate),

        events: {},

        initialize: function (options) {
            this.render();
        },

        render: function () {
            this.$el.html(this.template());
            return this;
        }
    });

    return View;
});
