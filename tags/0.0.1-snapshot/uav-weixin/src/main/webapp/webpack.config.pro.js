var path = require('path'),
    entry = require('./config/entry.js'),
    htmlPlugins = require('./config/html.js'),
    STYLES_PATH = path.resolve(__dirname, 'public/styles'),
    SRC_DIR = path.join(__dirname, 'public'),
    webpack = require("webpack"),
    ExtractTextPlugin = require("extract-text-webpack-plugin"),
    // HtmlWebpackPlugin = require('html-webpack-plugin'),
    CommonsChunkPlugin = webpack.optimize.CommonsChunkPlugin,
    IgnorePlugin = webpack.IgnorePlugin,
    UglifyJsPlugin = webpack.optimize.UglifyJsPlugin,
    LoaderOptionsPlugin = webpack.LoaderOptionsPlugin,
    DefinePlugin = webpack.DefinePlugin;

function getPlugins(){
    var plugins = [
        new CommonsChunkPlugin({
            names : ['shared'/*,'vendor'*/],
            minChunks: Infinity,
        }),
        new ExtractTextPlugin({
            filename : 'styles/[name].css',
            disable : false,
            allChunks: true,
        }),
        new IgnorePlugin(/.+\.js/, /\/public\/lib/),
        new webpack.ProvidePlugin({
            config: "config"
        }),
        new DefinePlugin({
            'process.env.NODE_ENV': JSON.stringify("production")
        }),
        new UglifyJsPlugin({
            output: {
                comments: false,
            },
            compress: {
                warnings: false
            }
        }),
    ];
    return plugins.concat( htmlPlugins || [] );
}

function getEntry(){
    // entry.vendor = [
    //     'react',
    //     'react-dom',
    //     'babel-polyfill',
    //     'promise',
    //     'lodash',
    //     'react-router',
    //     'react-router-dom',
    // ];
    return entry;
}

module.exports = {
    entry: getEntry(),
    output: {
        path: path.join(__dirname, "weixin/"), //文件输出目录
        publicPath: "/weixin/", //用于配置文件发布路径，如CDN或本地服务器
        chunkFilename: 'chunkes/[id].chunk.js',
        filename: "scripts/[name].js", //根据入口文件输出的对应多个文件名
    },
    module: {
        //各种加载器，即让各种文件格式可用require引用
        rules: [{
            test: /\.js?$/,
            use: ['babel-loader']
        }, {
            test: /\.css$/,
            use: ExtractTextPlugin.extract([
                'css',
                {
                    loader: 'postcss',
                    options: {
                        plugins: (loader) => [
                            require('autoprefixer')(),
                            require('cssnano')()
                        ]
                    }
                }
            ]),
            // include: STYLES_PATH
        }, {
            //使生效：需要在js中引入资源
            test: /\.less$/,
            use: ExtractTextPlugin.extract([ 
                'css', 
                {
                    loader: 'postcss',
                    options: {
                        plugins: (loader) => [
                            require('autoprefixer')(),
                            require('cssnano')()
                        ]
                    }
                }, 
                'less',
            ]),
            // include: STYLES_PATH
        }]
    },
    resolveLoader: {
        moduleExtensions: ["-loader"]
    },
    plugins: getPlugins(),
    resolve: {
        //配置别名，在项目中可缩减引用路径
        alias: {
            scripts: SRC_DIR + "/scripts",
            common: SRC_DIR + "/scripts/common",
            images: SRC_DIR + "/images",
            styles: SRC_DIR + "/styles",
            utils: SRC_DIR + "/scripts/utils",
            config: path.resolve(__dirname, "config/production.js"),
        }
    }
};
