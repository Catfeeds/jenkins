import React,{ Component } from 'react';
import { Dialog } from 'react-weui'

export default class _Toast extends Component {
	constructor(props) {
	   super(props);
	   this.state={
	   		buttons: [],
	   		title : "",
	   		info : "",
	   		show : false,
	   }
	}	
	show( info, title, buttons ){
		this.setState( { show : true , buttons, title, info } );
	}
	hide(){
		this.setState( { show : false } );
	}
    render() {
    	let {
    		buttons,
	   		title,
	   		info,
	   		show,
    	} = this.state;
	    return (
			<Dialog buttons = { buttons } title = { title } show = { show }>{ info }</Dialog>
	    )
  }
}