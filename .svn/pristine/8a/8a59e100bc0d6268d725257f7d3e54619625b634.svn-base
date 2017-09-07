import React,{ Component } from 'react';
import "styles/common/toast.css"

export default class Toast extends Component {
	constructor(props) {
	   super(props);
	   this.state={
	   		show : true,
	   		info : "",
	   		type : "toast"
	   }
	}	
	show( info, type = "toast" ){
		this.setState( { show : true , info, type } );
		if( type !== "loading" )
			setTimeout( () => this.hide() , 1500 )
	}
	hide(){
		this.setState( { show : false } );
	}
    render() {
    	let {
	    		info,
	    		show,
	    		type,
    		} = this.state;
	    return (
			<div style = { { display : ( show ? "block" : "none" ) } } id="toast">
			    <div className="weui_mask_transparent"></div>
			    <div className="weui_toast">
			        <i className={`${ type === "loading" && "weui_loading" } weui_icon_toast`}></i>
			        <p className="weui_toast_content">{ info || '已完成' }</p>
			    </div>
			</div>
	    )
  }
}