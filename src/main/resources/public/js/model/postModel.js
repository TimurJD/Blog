/**
 * @author Timur Berezhnoi
 */
define([
    "Backbone",
    "Underscore"
], function(Backbone, _) {
    var PostModel = Backbone.Model.extend({
        idAttribute: "_id"
    });

    return PostModel;
});