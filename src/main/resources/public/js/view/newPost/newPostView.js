/**
 * @author TimurJD
 */
define([
    "Backbone",
    "Underscore",
    "model/postModel",
    "text!template/newPost/newPostTemplate.html"
], function(Backbone, _, PostModel, NewPostTemplate) {
    var NewPostView = Backbone.View.extend({

        el: "#contentHolder",
        template: _.template(NewPostTemplate),

        events: {
            "click #createNewPost": "createNewPost"
        },

        initialize: function() {
            this.render();
        },

        createNewPost: function(event) {
            event.preventDefault();

            var title = this.$el.find("#newPostTitle").val();
            var content = this.$el.find("#newPostBody").val();

            var data = {
                titel: title,
                body : content
            };

            var newPost = new PostModel();
            newPost.urlRoot = '/newPost';

            newPost.save(data, {
                success: function(response, xhr) {
                    console.log(xhre);
                },
                error: function(error, xhr) {
                    console.log(xhr);
                }
            });

            console.log(title, content);
        },

        render: function() {
            this.$el.html(this.template());
            return this;
        }
    });

    return NewPostView;
});