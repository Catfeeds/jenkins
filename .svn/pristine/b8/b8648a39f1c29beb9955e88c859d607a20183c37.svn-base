import React from 'react'
import Tool from 'utils/tool'
import VCButton from 'common/vc-button'
import { Form, FormCell, CellHeader, CellBody, CellFooter, Label, Input, ButtonArea, Button } from 'react-weui'

export default class Signup2App extends React.Component{
	constructor(props) {
		super(props);
		this.state = {
			countdown : 0,
			timer : undefined,
		};
	}
	componentDidMount(){
		if( this.props.signupType === undefined )
			return Tool.to('/signup/1')
	}
	redirectNextStep( phone, code ){
		if( !/1\d{10}/.test( phone ) ) return Tool.alert("手机号码输入错误");
		if( !/\d{6}/.test( code ) ) return Tool.alert("验证码输入错误");
		// return Tool.to("/signup/3");
		Tool.post(`${ config.ajaxPath }/auth/register/verify`,{ type : 1, data : phone })
		.then( json => Tool.post(`${ config.ajaxPath }/auth/code/vertify`,{ code }))
		.then( json => Tool.to("/signup/3"))
	}
	render(){
		let {
			changeHandle,
			mobile,
			vericode,
		} = this.props,
		{
			countdown,
		} = this.state;
		return (
			<div id="sign2_app">
				<Form>
					<FormCell vcode>
						<CellHeader><Label>手机号</Label></CellHeader>
						<CellBody><Input maxLength = { 11 } value = { mobile } onChange = { e => changeHandle( "mobile", e, "number" ) } type = "text"/></CellBody>
						<CellFooter><VCButton phone = { mobile }/>{/*<Button onClick = { () => this.getVeriCode( mobile ) } type = "vcode">{ !!countdown ? `${ countdown }秒后重新发送` : "获取验证码" }</Button>*/}</CellFooter>
					</FormCell>
					<FormCell>
						<CellHeader><Label>验证码</Label></CellHeader>
						<CellBody><Input maxLength = { 6 } value = { vericode } onChange = { e => changeHandle( "vericode", e, "number" ) } type = "text"/></CellBody>
					</FormCell>
				</Form>
				<ButtonArea>
					<Button onClick = { () => this.redirectNextStep( mobile, vericode ) }>下一步 设置账号密码</Button>
				</ButtonArea>
			</div>
		)
	}
}