import React from 'react'
import { render } from 'react-dom'
import Toast from 'common/toast'
import Alert from 'common/alert'
import Promise from 'promise'
import isEmpty from 'lodash/isEmpty'
import { getHistory } from 'scripts/wrap-component'

let resourceCache = [],
    alertButtons = [
        {
            type : "default",
            label : "确定",
            onClick : () => Tool.hideAlert()
        }
    ],
    /**
     * 外部资源列表，配合loadResource使用，可以在需要的时候加载外部资源
     * 可以有效的分离主JS的大小
     * 注：loadResource会把需要的外部资源通过link、script引入到页面中，会比较适合CDN资源
     * 本地资源建议使用Tool.qrcode的方式引用
     */
    resourceMap = {
        wx: "http://res.wx.qq.com/open/js/jweixin-1.0.0.js",
        map: {
            script: [
                "http://api.map.baidu.com/api?v=2.0&ak=YGl2BqurgynhzfZNFbpsRcP21yDPq9ee&callback=BMapInit", 
                "http://map.qq.com/api/js?v=2.exp&callback=qqMapInit"
            ],
            extra() {
                return new Promise(resolve => {
                    if (!!window.qq && !!qq.maps && !!qq.maps.LatLng && !!BMap)
                        resolve();
                    let timer = setInterval(() => {
                        if (!!qq && !!qq.maps && !!qq.maps.LatLng && !!BMap) {
                            clearInterval(timer);
                            resolve();
                        }
                    }, 1000)
                })
            }
        },
    },
    toastSg = singleton(() => {
        let ele = document.createElement('DIV');
        document.getElementById('util').appendChild(ele);
        return render( <Toast /> , ele);
    }),
    alertSg = singleton(() => {
        let ele = document.createElement('DIV');
        document.getElementById('util').appendChild(ele);
        return render( <Alert /> , ele);
    }),
    Tool = {
        catchHandle,
        singleton,
        updateIn( o, ns, v ){
            if( !ns ) return null;
            !Array.isArray( ns ) && ( ns = [ns] )
            function _updateIn( _o ){
                let tn = ns.shift();
                if( Array.isArray( _o ) ){
                    let __o = _o.slice();
                    // ns.shift
                    __o[tn] = ns.length ? _updateIn( __o[tn] ) : v
                    return __o
                }else{
                    // tn = ns.shift();
                    return Object.assign( {}, _o, { [tn] : ( ns.length ? _updateIn( _o[tn] ) : v ) } )
                }
            }
            return _updateIn( o );
        },
        RouteWithSubRoutes( route ){
            return <Route path={route.path} render={props => (
                    <route.component {...props} routes={route.routes}/>
                )}/>
        },
        fetchCodeTo( url ){
            window.location.href = `https://open.weixin.qq.com/connect/oauth2/authorize?appid=${ config.appid }&redirect_uri=${ config.serverPath }${ config.basePath }${ url }&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect`;
        },
        /**
         * 注册页面使用code
         * 登陆页面使用pcode(不要问为什么  我也不懂)
         * @param  {[type]} phone  [description]
         * @param  {[type]} signup [description]
         * @return {[type]}        [description]
         */
        fetchVeriCode( phone, signup ){
            return Tool.get(`${ config.ajaxPath }/auth/${ signup ? "pcode" : "code" }`, { phone }, 1 )
        },
        saveLocalData( key, data ){
            let _data = data;
            if( typeof data === 'object' )
                _data = JSON.stringify( data )
            window.localStorage.setItem( key, _data )
        },
        getLocalData( key ){
            let data = window.localStorage.getItem( key );
            if( /\"/.test( data ) )
                return JSON.parse( data )
            return data;
        },
        // setCookie(name, value, exdays = 30 ){
        //     let date = new Date();
        //     date.setTime( date.getTime() + ( exdays * 24 * 60 * 60 * 1000 ) );
        //     document.cookie = `${ name }=${ value }; expires=${ date.toUTCString() }`;
        // },
        // getCookie( name ){
        //     let arr, 
        //         reg = new RegExp(`(^| )${ name }=([^;]*)(;|$)`);
        //     if ( arr = document.cookie.match(reg) )
        //         return unescape(arr[2]);
        //     else
        //         return null;
        // },
        // delCookie( name ){
        //     let value = Tool.getCookie( name );
        //     if (value === null)
        //         return;
        //     let date = new Date();
        //     date.setTime( date.getTime() - 1 );
        //         document.cookie = `${ name }=${ value }; expires=${ date.toUTCString() }`;
        // },
        /**
         * 跳转页面
         * @param  {String} path basename 不需要加 直接复制route.js path值
         */
        to : function( path ){
            getHistory().push( path )
        },
        /**
         * 线性或并行加载外部js/css,加载完成以后返回已加载资源列表
         * 添加依赖列表时需更新Tool.resourceMap
         * @param  {String}  name resourceMap的key
         * @param  {Boolean} linearLoad [description]
         * @return {Promise}
         */
        loadResource: function(name, linearLoad = false) {
            let promise = [];

            if (existed(name))
                return Promise.resolve();
            if (!resourceMap[name])
                return Promise.reject();

            if (Array.isArray(resourceMap[name]))
                promise = loadScript(resourceMap[name], name, linearLoad)
            else if (typeof resourceMap[name] === "object") {
                if (!!resourceMap[name].script) {
                    promise = loadScript(resourceMap[name].script, name, linearLoad)
                }
                if (!!resourceMap[name].link) {
                    promise = promise.contact(loadStyle(resourceMap[name].link, name))
                }
            } else
                promise = loadScript(resourceMap[name], name, linearLoad)

            if (typeof resourceMap[name].extra === "function")
                promise.push(resourceMap[name].extra())

            return Promise.all(promise).then(() => {
                resourceCache.push(name)
            });
        },
        /**
         * 加载qrjs，返回QRcode
         * 生成二维码参数参考qrjs
         * 例：
         * qrcode()
         * .then( QRCode => QRCode.generatePNG(url, { format: "png", margin: "2"  } ) )
         * .then( qrcodeSrc => console.info( qrcodeSrc ) )
         * 
         * @return {Promise}
         */
        qrcode: function() {
            this.showLoadToast();
            return new Promise((resolve, reject) => {
                require.ensure([], () => {
                    let QRCode = require('qrjs');
                    this.hideToast();
                    resolve(QRCode)
                });
            });
        },
        /**
         * 弹出层
         * buttons : {
         *     label : "按钮名"
         *     type : "按钮类型",
         *     onClick : "回调函数"
         * }
         * @param  {String || ReactElement || ReactComponent } msg  理论上可以在页面显示的所有内容都可以显示
         * @param  {String} title  弹出层标题
         * @param  {Array[Object]} buttons 按钮组
         */
        alert( msg, title = "", buttons = alertButtons ){
             alertSg().show( msg, title, buttons );
        },
        /**
         * 隐藏弹出层
         */
        hideAlert(){
            alertSg().hide();
        },
        /**
         * 显示提示层
         * @param  {String} icon     显示层图标，可自定义扩展
         * @param  {String} info     提示内容
         * @param  {Number} iconSize 图标大小
         * @return {[type]}          [description]
         */
        showToast( icon, info, iconSize ){
            toastSg().show( icon, info, iconSize )
        },
        /**
         * 隐藏提示层
         * @return {[type]} [description]
         */
        hideToast(){
            toastSg().hide();
        },
        /**
         * 显示loading加载层
         * @param  {String} msg 默认loading...
         */
        showLoadToast: function( msg ) {
            this.showToast("loading", msg || "loading...")
        },
        /**
         * 获取URL参数
         * @param  {String} name url参数名
         */
        getQueryString: function(name) {
            let reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"),
                r = window.location.search.substr(1).match(reg);
            if (r != null)
                return unescape(r[2]);
            return null;
        },
        stringifyParam( params ){
            if( isEmpty( params ) )
                return null;
            let arr = [];
            if( Array.isArray( params ) ){
                for( let i in params ){}
            }else
                recursive( params, arr )
            function recursive( rawObj, outArr, prefix = '' ){
                for( let key in rawObj ){
                    if( Array.isArray( rawObj[key] ) ){
                        if( typeof rawObj[key][0] === 'object' )
                            rawObj[key].forEach( ( v, i ) => recursive( v, outArr, `${ prefix }${ key }[${i}].` ) )
                        else
                            rawObj[key].forEach( v => outArr.push( `${ prefix }${ key }=${ v }` ) )
                    }else
                        outArr.push( `${ prefix }${ key }=${ rawObj[key] }`)
                }
            }
            return arr.join("&");
        },
        /**
         * 发送POST请求
         * fetch默认使用 multipart/form-data（支持图片异步上传）  
         * 由于现有Java服务器无法接收到 multipart/form-data 文本，所以统一使用 application/x-www-form-urlencoded
         * @param  {String} url     post链接
         * @param  {Object} params  post参数
         * @param  {String} enctype 
         * 1 application/x-www-form-urlencoded
         * 2 text/plain
         * 默认multipart/form-data 
         */
        post( url, params, enctype ){
            let body,
                headers = new Headers();
            switch( enctype ){
                case 1:
                    headers.append('Content-Type','application/x-www-form-urlencoded; charset=UTF-8')
                    // body = this.stringifyParam( params );
                    break;
                case 2:
                    headers.append('Content-Type','text/plain; charset=UTF-8')
                    body = this.stringifyParam( params );
                    break;
                case 3:
                    headers.append('Content-Type', 'application/json;charset=utf-8')
                    body = JSON.stringify( params );
                    break;
                default:
                    body = new FormData();
                    if( !isEmpty( params ) ){
                        for( let key in params ){
                            body.append( key, params[ key ] )
                        }
                    }
                    break;
            }
            let request = new Request( url, { 
                method : 'POST',
                credentials: "include",
                body : body || {},
                headers : headers || {},
            } )
            return catchHandle( fetch( request ) );
        },
        /**
         * [get description]
         * @param  {[type]} url    [description]
         * @param  {[type]} params [description]
         * @return {[type]}        [description]
         */
        get( url, params ){
            if( !isEmpty( params ) ) url += `?${ this.stringifyParam( params ) }`;
            let request = new Request( url, {
                method : 'GET',
                credentials: "include",
            } )
            return catchHandle( fetch( request ) );
        },
        /**
         * fetch( type, url, params )
         * fetch( url, params )
         * @param  {[type]} type   [description]
         * @param  {[type]} url    [description]
         * @param  {[type]} params [description]
         * @return {[type]}        [description]
         */
        fetch( type, url, params = {}){
            let headers,
                body,
                regx = new RegExp(type,'i'),
                promise,
                _url,
                _params,
                _type;
            const types = 'GET,POST,PUT';

            if( !regx.test( types ) ){
                _type = "GET";
                _url = type;
                _params = url;
            }else{
                _type = type.toUpperCase();
                _url = url;
                _params = params;
            }

            switch( _type ){
                case "GET":
                case "HEAD":
                    promise = this.get( _url, _params );
                    break;
                case "POST":
                    promise = this.post( _url, _params, 1)
                    break;
                case "PUT":
                    body = new FormData();
                    if( !isEmpty( params ) ){
                        for( let key in params ){
                            body.append( key, params[ key ] )
                        }
                    }
                    promise = fetch(new Request( url, { 
                            method : 'PUT',
                            credentials: "include",
                            body : body || {},
                            headers : headers || {},
                        }));
                    break;
                default:
                    promise = this.post( _url, _params )
                    break;
            }
            return catchHandle( promise );
        }
    };

function loadScript(path, name, linear) {
    return linear ? linearLoadScript(path, name) : parallelLoadScript(path, name)
}

function existed(name) {
    return resourceCache.indexOf(name) != -1
}
/*
    线性加载Script脚本
*/
function linearLoadScript(path, name) {
    let _path = Array.isArray(path) ? path : [path],
        gen = (function*() {
            for (let __path of _path) {
                yield new Promise((resolve, reject) => {
                    let script = document.createElement("SCRIPT");
                    script.type = "text/javascript";
                    script.src = __path;
                    script.setAttribute("__rs", name)
                    script.onload = function() {
                        resolve();
                    }
                    document.body.appendChild(script)
                })
            }
        })();
    return [new Promise((resolve, reject) => {
        next();

        function next() {
            let _next = gen.next();
            if (_next.done)
                return resolve();
            _next.value.then(script => {
                next()
            })
        }
    })]
}
/**
 * 并行加载Script脚本
 * @param  {[type]} path [description]
 * @param  {[type]} name [description]
 * @return {Array[Promise]}      返回由Promise组成加载列表，可以通过Promise.resolve 一次性知道resolved或rejected
 */
function parallelLoadScript(path, name) {
    let _path = Array.isArray(path) ? path : [path];
    return _path.map(v => {
        return new Promise((resolve, reject) => {
            let script = document.createElement("SCRIPT");
            script.type = "text/javascript";
            script.src = v;
            script.setAttribute("__rs", name)
            script.onload = function() {
                resolve();
            }
            document.body.appendChild(script)
        })
    })
}
/**
 * 单例模式
 * @param  {Function} fn 回调函数
 * @return {Function} 
 */
function singleton(fn) {
    let result;
    return () => result || (result = fn.apply(null, arguments));
}
/**
 * 处理异步错误
 * @param  {Promise} promise 
 * @return {Promise}     
 */
function catchHandle( promise ){
    Tool.showLoadToast();
    return promise.then( response => response.json())
    .then( json => {
        Tool.hideToast();
        if( json.code == 0 ){
            return json
        }else{
            let e = new Error(json.message);
            e.number = json.code;
            throw e;
        }
    })
    .catch( e => {
        switch( e.number ){
            case 401:
                Tool.saveLocalData( "signinBeforeUrl", location.pathname.replace(config.basePath,"") )
                Tool.fetchCodeTo("/signin");
                // Tool.to("/signin")
                throw e;
            case 401001:
                // 跳转验证页面
                // Tool.to("/user/auth")
                throw e;
            default:
                Tool.alert(e.message);
                throw e;
        }
    })
}
export default Tool;
