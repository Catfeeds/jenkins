import React from 'react'
import Tool from 'utils/tool'
import { Button } from 'react-weui'

export default class VCButton extends React.Component{
	constructor(props) {
		super(props);
		this.timer = undefined;
		this.state = {
			countdown : 0,
		};
	}
	getVeriCode( phone ){
		if( !/1\d{10}/.test( phone ) ){
			Tool.alert("请输入正确的手机号码");
			return;
		}
		Tool.fetchVeriCode( phone, this.props.signup ).then( data => {
			if( data.code === 0 ){
				this.clearTimer();
				this.setState( { countdown : 60 } )
				this.timer = setInterval( () => {
					this.setState( { countdown : --this.state.countdown } ,()=>{
						if( this.state.countdown === 0 )
							this.clearTimer();
					})
				}, 1000 )
			}
		})
	}
	clearTimer(){
		if( !!this.timer )
			clearInterval( this.timer )
	}
	componentWillUnmount(){
		this.clearTimer();
	}
	render(){
		let { countdown } = this.state,
			{ phone } = this.props;
		return !!countdown ?
			<Button style = {{ fontSize : "12px" , color : "#999", backgroundColor : "#f5f5f5" }} type = "vcode">{ `${ countdown }秒后重新发送` }</Button>
			:
			<Button style = {{ fontSize : "12px" }} onClick = { () => this.getVeriCode( phone ) } type = "vcode">获取验证码</Button>
	}
}