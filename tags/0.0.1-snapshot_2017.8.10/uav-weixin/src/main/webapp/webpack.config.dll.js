var path = require('path'),
    webpack = require('webpack'),
    DllPlugin = webpack.DllPlugin;

module.exports = {
    entry: {
        vendor: [
            'react',
            'react-dom',
            'babel-polyfill',
            // 'qrjs',
            'promise',
            'lodash',
        ]
    },
    output: {
        path: path.join(__dirname, "build/dll"),
        //文件输出目录,
        filename:"vendor.bundle.js",
        library:"[name]_[hash]",
    },
    plugins: [
        new DllPlugin({
            path : path.join( __dirname, "build/dll", "[name]-manifest.json"),
            name : "[name]_[hash]"
        })
    ],

};
