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

        validate: function(data) {
            var validationStatus = [];

            if(!data.title) {
                validationStatus.push({inputId: "postTitle", message: "Please  fill post title."});
            } else if(data.title.length < 5) {
                validationStatus.push({inputId: "postTitle", message: "Invalid post title. At least 5 characters."});
            } else {
                this.hideErrors("postTitle");
            }

            if(!data.body) {
                validationStatus.push({inputId: "postBody", message: "Please fill post content."});
            } else {
                this.hideErrors("postBody");
            }

            return (validationStatus.length) ? validationStatus : false;
        },

        hideErrors: function(tagName) {
            var div = this.$el.find('#' + tagName + 'Div');

            div.removeClass('has-error').addClass('has-success has-feedback');

            div.find('#' + tagName + 'Span')
                .removeClass('hide glyphicon-remove')
                .addClass('glyphicon glyphicon-ok form-control-feedback');

            div.find('#' + tagName + 'EM')
                .text('')
                .addClass('hide');
        },

        validationError: function (statuses) {
            var self = this;

            _.each(statuses, function (status) {
                var tagName = status.inputId;
                var div = self.$el.find('#' + tagName + 'Div');

                div.removeClass('has-success').addClass('has-error has-feedback');

                div.find('#' + tagName + 'Span')
                    .removeClass('hide glyphicon-ok')
                    .addClass('glyphicon-remove');

                div.find('#' + tagName + 'EM')
                    .text(status.message)
                    .removeClass('hide');
            }, this);
        },

        createNewPost: function(event) {
            event.preventDefault();

            var title = this.$el.find("#newPostTitle").val();
            var content = this.$el.find("#newPostBody").val();

            var data = {
                title: title,
                body : content
            };

            var errors = this.validate(data);

            if (errors) {
                this.validationError(errors);
                return;
            }

            var newPost = new PostModel();
            newPost.urlRoot = "/newPost";

            newPost.save(data, {
                success: function(response, xhr) {
                    console.log(xhr);
                },
                error: function(error, xhr) {
                    console.log(xhr.responseJSON.message);
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