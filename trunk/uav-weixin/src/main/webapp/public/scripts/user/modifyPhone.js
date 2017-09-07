import React from 'react'
import Tool from 'utils/tool'
import VCButton from 'common/vc-button'
import { Form, FormCell, CellHeader, CellBody, CellFooter, Label, Input, ButtonArea, Button } from 'react-weui'

const RegxOnlyNumber = /^\d{0,}$/;

export default class ModifyPhoneApp extends React.Component{
	constructor(props) {
		super(props);
		this.state = {
			mobile : "",
			vericode : "",
		};
	}
	componentDidMount(){
	}
	changeHandle( name, e, type ){
		let value,
			valid = true;
		if( !!e && !!e.target ){
			e.persist();
			value = e.target.value
		}else value = e
		switch( type ){
			case "number":
				valid = RegxOnlyNumber.test( value )
				valid && value && ( value = parseInt( value ) )
			break;
		}
		valid && this.setState( Object.assign( {}, this.state, { [name] : value } ) )
	}
	redirectNextStep( phone, code ){
		if( !/1\d{10}/.test( phone ) ) return Tool.alert("手机号码输入错误");
		if( !/\d{6}/.test( code ) ) return Tool.alert("验证码输入错误");
		Tool.fetch('put', `${ config.ajaxPath }/user/phone`,{ code })
		.then( () => history.back() )
	}
	render(){
		let {
			mobile,
			vericode,
		} = this.state;
		return (
			<div id="sign2_app">
				<Form style = {{ marginTop : 0 }}>
					<FormCell vcode>
						<CellHeader><Label>手机号</Label></CellHeader>
						<CellBody><Input maxLength = { 11 } value = { mobile } onChange = { e => this.changeHandle( "mobile", e, "number" ) } type = "text"/></CellBody>
						<CellFooter><VCButton phone = { mobile }/>{/*<Button onClick = { () => this.getVeriCode( mobile ) } type = "vcode">{ !!countdown ? `${ countdown }秒后重新发送` : "获取验证码" }</Button>*/}</CellFooter>
					</FormCell>
					<FormCell>
						<CellHeader><Label>验证码</Label></CellHeader>
						<CellBody><Input maxLength = { 6 } value = { vericode } onChange = { e => this.changeHandle( "vericode", e, "number" ) } type = "text"/></CellBody>
					</FormCell>
				</Form>
				<ButtonArea>
					<Button onClick = { () => this.redirectNextStep( mobile, vericode ) }>提交修改</Button>
				</ButtonArea>
			</div>
		)
	}
}