import React from 'react'
import ReactDOM from "react-dom"
import { BrowserRouter } from 'react-router-dom'
import routes from './route'
// import 'react-weui/lib/react-weui.min.css';
import 'weui'
import "styles/common.css"
import { Uploader } from 'react-weui'
import {Provider} from 'react-redux'
import 'whatwg-fetch'
import configureStore from './store' 
// CityPicker.prototype.handleChange = function(){
//     if(this.props.onChange) this.props.onChange(this.state.text, this.state.selected, this.state.groups );
// }

Uploader.prototype.handleChange = function(e) {
    const langs = this.props.lang;
    let _files = e.target.files;

    if(_files.length === 0) return;

    if(this.props.files.length >= this.props.maxCount) {
        this.props.onError(langs.maxError(this.props.maxCount));
        return;
    }

    for(let key in _files) {
        if (!_files.hasOwnProperty(key)) continue;
        let file = _files[key];

        this.handleFile(file, (_file,e)=>{
            if(this.props.onChange) this.props.onChange(_file, ReactDOM.findDOMNode(this.refs.uploader));
            ReactDOM.findDOMNode(this.refs.uploader).value='';
        },e);
    }
}

let store = configureStore();
ReactDOM.render(
    <Provider store={store}>
    	<BrowserRouter forceRefresh = { !( 'pushState' in window.history ) } basename = { '/efly' }>
    		{ routes }
    	</BrowserRouter>
    </Provider>,
	document.getElementById("container")
);