/**
 * @author Timur Berezhnoi
 */
define([
    "Backbone",
    "model/postModel"
], function(Backbone, PostModel) {
    var PostCollection = Backbone.Collection.extend({
        model: PostModel
    });

    return PostCollection;
});