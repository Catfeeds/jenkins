import React,{ Component } from 'react';
import { Toast } from 'react-weui'

export default class _Toast extends Component {
	constructor(props) {
	   super(props);
	   this.state={
	   		icon: "toast",
	   		iconSize : "",
	   		info : "",
	   		show : false,
	   }
	}	
	show( icon, info, iconSize ){
		this.setState( { show : true , icon, iconSize, info } );
		if( icon !== "loading" )
			setTimeout( () => this.hide() , 1500 )
	}
	hide(){
		this.setState( { show : false } );
	}
    render() {
    	let {
    		icon,
	   		iconSize,
	   		info,
	   		show,
    	} = this.state;
	    return (
			<Toast icon = { icon } iconSize = { iconSize } show = { show }>{ info }</Toast>
	    )
  }
}