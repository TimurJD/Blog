/**
 * @author Timur Berezhnoi
 */
define([
    "Backbone",
    "model/postModel"
], function(Backbone, PostModel) {
    var PostCollection = Backbone.collection.extend({
        model: PostModel
    });

    return PostCollection;
});