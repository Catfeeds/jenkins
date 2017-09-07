var path = require('path'),
    entry = require('./config/entry.js'),
    htmlPlugins = require('./config/html.js'),
    STYLES_PATH = path.resolve(__dirname, 'public/styles'),
    SRC_DIR = path.join(__dirname, 'public'),
    webpack = require("webpack"),
    ExtractTextPlugin = require("extract-text-webpack-plugin"),
    CommonsChunkPlugin = webpack.optimize.CommonsChunkPlugin,
    AddAssetHtmlPlugin = require('add-asset-html-webpack-plugin'),
    IgnorePlugin = webpack.IgnorePlugin,
    LoaderOptionsPlugin = webpack.LoaderOptionsPlugin,
    DefinePlugin = webpack.DefinePlugin;
    
function getPlugins(){
    var plugins = [
        new CommonsChunkPlugin({
            names : ['shared'],
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
    devServer: {
        historyApiFallback: true,
        hot: true,
        inline: true,
        // lazy: true,
        noInfo: false,
        // 文件更新自动刷新页面
        // watchContentBase : true,
        // stats : true,
        // progress: true,
        overlay: {
            warnings: true,
            errors: true
        },
        compress : true,
        host : '159e33599s.iask.in',
        port : 80,
        // https: true,
        proxy : {
            // 'http://159e33599s.iask.in/*': {
            '/weixin': {
                // target:"http://192.168.0.25:8080",
                target:"http://192.168.0.146:8082",
                // target:"http://weixin.jeatcs.com",
                // target:"http://192.168.0.164:8080",
                // target: "http://192.168.0.146:8080",
                // target:"http://192.168.0.151:8mei080",
                // target:"http://localhost:8080",
                secure: false
            },
        },
    },
    entry: getEntry(),
    output: {
        path: path.join(__dirname, "weixin/"), //文件输出目录
        publicPath: "/weixin/", //用于配置文件发布路径，如CDN或本地服务器
        chunkFilename: 'chunkes/[id].chunk.js',
        filename: "scripts/[name].js", //根据入口文件输出的对应多个文件名
    },
    module: {
        //各种加载器，即让各种文件格式可用require引用
        rules: [
            {
                test: /\.js?$/,
                exclude:/node_modules/,
                use: ['babel-loader']
            }, 
            {
                test: /\.css$/,
                use: ExtractTextPlugin.extract([
                    'css',
                    {
                        loader: 'postcss',
                        options: {
                            plugins: (loader) => [
                                require('autoprefixer')(),
                                // require('cssnano')()
                            ]
                        }
                    }
                ]),
                // exclude:/node_modules/,
                // include: STYLES_PATH
            }, 
            {
                //使生效：需要在js中引入资源
                test: /\.less$/,
                use: ExtractTextPlugin.extract([ 
                    'css', 
                    {
                        loader: 'postcss',
                        options: {
                            plugins: (loader) => [
                                require('autoprefixer')(),
                                // require('cssnano')()
                            ]
                        }
                    }, 
                    'less',
                ]),
                // exclude:/node_modules/,
                // include: STYLES_PATH
            },
            {
                test: /\.woff2?(\?v=[0-9]\.[0-9]\.[0-9])?$/,
                use: "url?limit=10000"
            },
            {
                test: /\.(ttf|eot|svg)(\?[\s\S]+)?$/,
                use: 'file'
            }
        ]
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
            config: path.resolve(__dirname, "config/development.js"),
        }
    }
};
