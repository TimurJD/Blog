/**
 * @author TimurJD
 */
define([
    "Backbone",
    "Underscore",
    "collection/postCollection",
    "text!template/posts/postsTemplate.html"
], function(Backbone, _, PostCollection, PostsTemplate) {
    var PostsView = Backbone.View.extend({

        el: "#contentHolder",
        template: _.template(PostsTemplate),

        events: {},

        initialize: function() {
            var postCollection = new PostCollection();
            postCollection.url = "/posts";
            postCollection.fetch({
                data: {
                    limit: 10,
                    pageNumber: 1
                },
                success: function(collection, response, options) {
                    console.log("Collection: ", collection);
                    console.log("Response: ", response);
                    console.log("Options: ", options);
                },
                error: function() {
                    console.log("Error");
                }
            });
            this.render();
        },

        render: function() {
            this.$el.html(this.template());
            return this;
        },

        select: function() {
            $("#posts").addClass("active");
        }
    });

    return PostsView;
});