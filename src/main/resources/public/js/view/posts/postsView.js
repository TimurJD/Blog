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
            var self = this;
            var postCollection = new PostCollection();
            postCollection.url = "/posts";
            postCollection.fetch({
                data: {
                    limit: 10,
                    pageNumber: 1
                },
                success: function(collection) {
                    self.render(collection.toJSON());
                },
                error: function() {
                    console.log("Error");
                }
            });
        },

        render: function(posts) {
            this.$el.html(this.template({posts: posts}));
            return this;
        },

        select: function() {
            $("#posts").addClass("active");
        }
    });

    return PostsView;
});