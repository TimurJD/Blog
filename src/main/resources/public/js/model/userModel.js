define([
    'Backbone',
    'Underscore'
], function(Backbone, _) {
    var UserModel = Backbone.Model.extend({
        idAttribute: '_id'
    });

    return UserModel;
});