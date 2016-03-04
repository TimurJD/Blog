var APP = APP || {};

require.config({
    paths: {
        jQuery     : './lib/jquery/dist/jquery',
        Underscore : './lib/underscore/underscore',
        Backbone   : './lib/backbone/backbone',
        Bootstrap  : './lib/bootstrap/dist/js/bootstrap.min',
        text       : './lib/text/text',
        models     : './model',
//        collections: './collections',
        view      : './view',
        template  : '../template',
//        moment     : './libs/moment/moment',
//        io         : '/socket.io/socket.io'
    },
    shim : {
        Underscore : {
            exports: '_'
        },
        jQuery     : {
            exports: '$'
        },
        'Bootstrap': ['jQuery'],
        'Backbone' : ['Underscore', 'jQuery'],
        'app'      : ['Backbone', 'Bootstrap']
    }
});

require(['app'], function(app) {
    app.init();
});