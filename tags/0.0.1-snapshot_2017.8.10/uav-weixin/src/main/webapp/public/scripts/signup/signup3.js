import React from 'react'
import Tool from 'utils/tool'
import { Panel, PanelBody, MediaBox, Form, FormCell, CellHeader, CellBody, CellFooter, Label, Input, ButtonArea, Button } from 'react-weui'

export default class Signup2App extends React.Component{
	componentDidMount(){
		if( this.props.signupType === undefined )
			return Tool.to('/signup/1')
	}
	redirectNextStep( username, password1, password2 ){
		if( !/^[a-zA-Z0-9_-]{3,16}$/.test( username ) ) return Tool.alert("账号输入错误，账号由3-16位字符组成")
		if( !/^[a-zA-Z0-9_-]{6,18}$/.test( password1 ) ) return Tool.alert("密码输入错误，密码由6-18位字符组成")
		if( password1 !== password2 ) return Tool.alert("两次密码输入不一致")
		// return Tool.to("/signup/4");
		Tool.post(`${ config.ajaxPath }/auth/register/verify`,{ type : 2, data : username })
		.then( json => Tool.to("/signup/4"))
	}
	render(){
		let {
			changeHandle,
			username,
			password1,
			password2,
		} = this.props;
		return (
			<div id="sign3_app">
				<Form>
					<FormCell>
						<CellHeader><Label>账号</Label></CellHeader>
						<CellBody><Input value = { username } onChange = { e => changeHandle( "username", e ) } placeholder = "请设置登陆账号" type = "text"/></CellBody>
					</FormCell>
					<FormCell>
						<CellHeader><Label>密码</Label></CellHeader>
						<CellBody><Input type = "password" value = { password1 } onChange = { e => changeHandle( "password1", e ) } placeholder = "请设置登录密码"/></CellBody>
					</FormCell>
					<FormCell>
						<CellHeader><Label>确认密码</Label></CellHeader>
						<CellBody><Input type = "password" value = { password2 } onChange = { e => changeHandle( "password2", e ) } placeholder = "请再次输入登录密码"/></CellBody>
					</FormCell>
				</Form>
				<Panel>
					<PanelBody>
						<MediaBox style = {{ color : "#ff393a" }}>注意：您可使用此帐号登录E飞网页版</MediaBox>
					</PanelBody>
				</Panel>
				<ButtonArea>
					<Button onClick = { () => this.redirectNextStep( username, password1, password2 ) }>下一步 基本信息</Button>
				</ButtonArea>
			</div>
		)
	}
}