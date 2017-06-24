/**
 * @author Timur Berezhnoi
 */
define([
    "Backbone",
    "model/postModel"
], function(Backbone, PostModel) {
    return Backbone.Collection.extend({
        model: PostModel
    });
});